package com.example.e_examapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class results extends AppCompatActivity {
    TextView degree,total;
    Button donebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        degree=findViewById(R.id.degree);
        total=findViewById(R.id.total);
        donebtn=findViewById(R.id.donebtn);

        degree.setText(String.valueOf(getIntent().getIntExtra("degree", Integer.parseInt("0"))));
        total.setText(String.valueOf(getIntent().getIntExtra("total", Integer.parseInt("0"))));
    donebtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
    }
}
