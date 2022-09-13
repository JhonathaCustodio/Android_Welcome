package com.ibm.myfirstapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvNome = findViewById(R.id.tvNome);

        SharedPreferences pref = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String emailShared = pref.getString("email",null);
        String senhaShared = pref.getString("senha",null);
        String nomeShared = pref.getString("nome",null);


        //Typeface textOla = Typeface.createFromAsset(getAssets(), "fonts/aclonica.ttf");
        //Typeface textOla = getResources().getFont(R.font.aclonica);
        //textOla.setTypeface( getResources().getFont( R.font.aclonica ) );

        tvNome.setText(nomeShared);
    }

}