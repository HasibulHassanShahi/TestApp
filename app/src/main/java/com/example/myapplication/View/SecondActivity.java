package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class SecondActivity extends AppCompatActivity {

    TextView cancleTxt, saveTxt;
    EditText firstName, lastName, emailtxt, phonetxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        cancleTxt = findViewById(R.id.cancle_button);
        saveTxt = findViewById(R.id.save_button);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        emailtxt = findViewById(R.id.email);
        phonetxt = findViewById(R.id.phone);

        Intent intent = getIntent();
        String firstname = intent.getStringExtra("firstName");
        String lastname = intent.getStringExtra("lastName");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        final int position;
        if (intent.getStringExtra("position") != null){
            position = Integer.parseInt(intent.getStringExtra("position"));
        }
        else
            position = 0;


        firstName.setText(firstname);
        lastName.setText(lastname);
        emailtxt.setText(email);
        phonetxt.setText(phone);

        cancleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentmain = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intentmain);
            }
        });

        saveTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSave = new Intent(SecondActivity.this, MainActivity.class);
                intentSave.putExtra("firstName",firstName.getText());
                intentSave.putExtra("lastName",lastName.getText());
                intentSave.putExtra("email",emailtxt.getText());
                intentSave.putExtra("phone",phonetxt.getText());
                intentSave.putExtra("position",position);
                startActivity(intentSave);
            }
        });

    }
}