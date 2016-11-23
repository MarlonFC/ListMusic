package primeiroprojeto.cursoandroid.com.listmusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMusicaActivity extends AppCompatActivity {

    private Banco bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_musica);

        bd = new Banco(this);

        final EditText nome = (EditText) findViewById(R.id.editNome);
        final EditText genero = (EditText) findViewById(R.id.editGenero);

        Button adicionar = (Button) findViewById(R.id.btnAddMusica);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Musica musica = new Musica();
                musica.setNome(nome.getText().toString());
                musica.setGenero(genero.getText().toString());
                bd.addMusica(musica);

            Toast.makeText(getBaseContext(), "Musica Add com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
