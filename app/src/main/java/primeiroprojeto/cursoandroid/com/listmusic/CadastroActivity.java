package primeiroprojeto.cursoandroid.com.listmusic;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {


    EditText nome;
    EditText email;
    EditText senha;

    Banco banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        banco = new Banco(this);

        nome = (EditText) findViewById(R.id.editNome);
        email= (EditText) findViewById(R.id.editEmail);
        senha = (EditText) findViewById(R.id.editSenha);


    }

    public void cadastro(View v) {
        String nomeUsuarioInf= nome.getText().toString();
        String emailInf= email.getText().toString();
        String senhaInf= senha.getText().toString();

        if(nomeUsuarioInf.isEmpty()|| emailInf.isEmpty()|| senhaInf.isEmpty()){

            Toast.makeText(getApplicationContext(), "Campo vazio!! Insira dados para cadastro!!",
                    Toast.LENGTH_LONG).show();

        }else{

            String strNomeUsuario= nome.getText().toString();
            String strEmail= email.getText().toString();
            String strSenha= senha.getText().toString();

            ContentValues values = new ContentValues();
            values.put("nomeUsuario",strNomeUsuario);
            values.put("email",strEmail);
            values.put("senha", strSenha);

            banco.getWritableDatabase().insert("usuarios1", null, values);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Dados Informados");
            builder.setMessage("Resgistro Inserido com Sucesso");
            builder.setPositiveButton("OK", null);
            builder.setNegativeButton("Cancelar", null);
            builder.show();

            Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
            startActivity(intent);

            nome.setText("");
            email.setText("");
            senha.setText("");

        }
    }


}

