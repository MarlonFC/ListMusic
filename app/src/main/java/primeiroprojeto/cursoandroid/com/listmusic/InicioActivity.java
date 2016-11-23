package primeiroprojeto.cursoandroid.com.listmusic;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InicioActivity extends AppCompatActivity {
    Banco banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        banco = new Banco(this);
    }

    public void Listar(View v) {

        Cursor c = banco.getReadableDatabase().rawQuery("select * from usuarios1;", null);
        while(c.moveToNext()) {


            String id = c.getString(c.getColumnIndex(Banco.KEY_ID_USU));
            String nome = c.getString(c.getColumnIndex(Banco.KEY_NOMEUSUARIO));
            String email = c.getString(c.getColumnIndex(Banco.KEY_EMAIL));
            String senha = c.getString(c.getColumnIndex(Banco.KEY_SENHA));



            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Dados Consultados");
            builder.setMessage(" _id_Usuario: " + id + "\n " +
                    "nomeUsuario: " + nome + "\n " +
                    "Senha: " + senha);
            builder.setPositiveButton("OK", null);
            builder.setNegativeButton("Cancelar", null);
            builder.show();

        }

    }
}
