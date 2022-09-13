package com.ibm.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        EditText etEmail = findViewById(R.id.editTextEmail);
        EditText etSenha = findViewById(R.id.editTextSenha);
        EditText etSenhaConfirma = findViewById(R.id.editTextSenhaConfirma);
        EditText etNome = findViewById(R.id.editTextNome);

        etEmail.setText(getIntent().getStringExtra("KeyEmail"));
        etSenha.setText(getIntent().getStringExtra("KeySenha"));
        etNome.setText(getIntent().getStringExtra("KeyNome"));


        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = etNome.getText().toString();
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                String senhaConfirma = etSenhaConfirma.getText().toString();


                if(nome.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Digite seu nome",
                            Toast.LENGTH_LONG).show();
                }else if ( senha.equals(senhaConfirma)){
                    //ir para a tela surpresa

                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

                    myEdit.putString("email", email);
                    myEdit.putString("senha", senha);
                    myEdit.putString("nome", nome);
                    myEdit.commit();

                    finish();

                }else{
                    //erro senhas divergentes

                    alerta("senhas divergentes, por favor digite sua senha");
                }

            }
        });
    }

    private void alerta(String msg){

        new android.app.AlertDialog.Builder(CadastroActivity.this)
                .setTitle("ATENÇÃO")
                .setMessage(msg)

                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int with) {

                    }
                })

                .setIcon(android.R.drawable.ic_delete)
                .show();
    }
}