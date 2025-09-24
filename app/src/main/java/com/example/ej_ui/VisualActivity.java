package com.example.ej_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VisualActivity extends AppCompatActivity {

    private TextView name_info;
    private TextView surname_info;
    private TextView phone_info;
    private Button call_button;
    private Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_visual);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name_info = findViewById(R.id.vName_info);
        surname_info = findViewById(R.id.vSurname_info);
        phone_info = findViewById(R.id.vPhone_info);
        call_button = findViewById(R.id.call_button);
        back_button = findViewById(R.id.back_button);

        name_info.setText(getIntent().getStringExtra("name"));
        surname_info.setText(getIntent().getStringExtra("surname"));
        phone_info.setText(getIntent().getStringExtra("phone"));

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisualActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}