package com.example.watchudrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//
        setUIPropaties();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister =  new Intent(MainActivity.this,RegisterActivity.class);
                 MainActivity.this.startActivity(intentRegister);
            }
        });
    }

    public void setUIPropaties()
    {
        btnRegister = (Button)findViewById(R.id.btnRegister);
    }

}
