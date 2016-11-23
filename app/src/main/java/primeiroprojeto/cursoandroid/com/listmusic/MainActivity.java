package primeiroprojeto.cursoandroid.com.listmusic;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    EditText login;
    EditText senha;

    Banco banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (EditText) findViewById(R.id.editUsuario);
        senha = (EditText) findViewById(R.id.editSenhaLogin);

        banco = new Banco(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
          Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
          startActivity(intent);
        }
        });
    }

    public void login(View v) {

        String nomeInf = login.getText().toString();
        String senhaInf = senha.getText().toString();

        String[] argumentos = new String[]{nomeInf, senhaInf};

        Cursor c = banco.getReadableDatabase().rawQuery("select * from usuarios1 where usuarios1.nomeUsuario = ? AND  " +
                "usuarios1.senha = ? ", argumentos);
        c.moveToFirst();

        if (c.getCount() > 0) {

            startActivity(new Intent(this, InicioMenuActivity.class));

            login.setText("");
            senha.setText("");


        } else if (nomeInf.isEmpty() || senhaInf.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Campo vazio!! informe seu Login ou Senha!!",
                    Toast.LENGTH_LONG).show();

            login.setText("");
            senha.setText("");

       } else {

            Toast.makeText(getApplicationContext(), "Usuario não Cadastrado",
                    Toast.LENGTH_LONG).show();

            login.setText("");
            senha.setText("");
        }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
   }
}
