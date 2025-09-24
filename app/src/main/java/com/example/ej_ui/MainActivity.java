package com.example.ej_ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editNameText;
    private EditText editSurnameText;
    private EditText editPhone;
    private Button skillsButton;
    private Button visualButton;
    private LinearLayout viewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editNameText = findViewById(R.id.nameImput);
        editSurnameText = findViewById(R.id.surnameImput);
        editPhone = findViewById(R.id.phoneImput);
        skillsButton = findViewById(R.id.skill_button);
        viewLayout = findViewById(R.id.skillLayout);
        visualButton = findViewById(R.id.visualitation_button);

        visualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeData();
            }
        });

    }

    private void seeData(){
        String name = editNameText.getText().toString();
        String surname = editSurnameText.getText().toString();
        String phone = editPhone.getText().toString();

        if(name.isEmpty() || surname.isEmpty() || phone.isEmpty()){
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(MainActivity.this, VisualActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("surname", surname);
            intent.putExtra("phone", phone);
            startActivity(intent);
        }
    }
    private void addData(){

    }
}