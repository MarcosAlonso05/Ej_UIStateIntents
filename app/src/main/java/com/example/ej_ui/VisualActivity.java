package com.example.ej_ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VisualActivity extends AppCompatActivity {

    private TextView name_info;
    private TextView surname_info;
    private TextView phone_info;
    private TextView skill_info;
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
        skill_info = findViewById(R.id.vSkill_info);

        name_info.setText(getIntent().getStringExtra("name"));
        surname_info.setText(getIntent().getStringExtra("surname"));
        phone_info.setText(getIntent().getStringExtra("phone"));
        skill_info.setText(getIntent().getStringExtra("skills"));

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisualActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phone_info.getText().toString().trim();
                Uri uri = Uri.parse("tel:" + phoneNumber);
                Intent it = new Intent(Intent.ACTION_DIAL, uri);

                startActivity(it);

            }
        });
    }
}