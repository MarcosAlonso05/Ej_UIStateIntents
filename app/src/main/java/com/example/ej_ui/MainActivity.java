package com.example.ej_ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private EditText editNameText;
    private EditText editSurnameText;
    private EditText editPhone;
    private Button skillsButton;
    private FloatingActionButton fabButton;

    private CheckBox checkBox;
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
        fabButton = findViewById(R.id.floatingActionButton);
        checkBox = findViewById(R.id.checkBox);
        fabButton.setEnabled(false);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                fabButton.setEnabled(isChecked);
            }
        });

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeData();
            }
        });

        skillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();
            }
        });

    }

    private void seeData(){
        String name = editNameText.getText().toString();
        String surname = editSurnameText.getText().toString();
        String phone = editPhone.getText().toString();

        int childCount = viewLayout.getChildCount();
        StringBuilder skillsBuilder = new StringBuilder();

        if(name.isEmpty() || surname.isEmpty() || phone.isEmpty()){
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }else {
            if(childCount != 0){
                for (int i = 0; i < childCount; i++) {
                    View child = viewLayout.getChildAt(i);

                    String skill = ((TextView) child).getText().toString().trim();
                    if (!skill.isEmpty()) {
                        if (skillsBuilder.length() > 0) {
                            skillsBuilder.append(", ");
                        }
                        skillsBuilder.append(skill);
                    }
                }
            }
            Intent intent = new Intent(MainActivity.this, VisualActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("surname", surname);
            intent.putExtra("phone", phone);
            intent.putExtra("skills", skillsBuilder.toString());
            startActivity(intent);
        }
    }
    private void addData(String skill) {

        TextView item = new TextView(this);
        if (skill != null) {
            item.setText(skill);

            item.setOnLongClickListener(v ->{
                deleteSkill(item);
                return true;
            });

            viewLayout.addView(item);
        }
    }

    private void alert(){
        int childCount = viewLayout.getChildCount();
        if(childCount < 5){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            EditText skill = new EditText(this);

            builder.setView(skill);
            builder.setTitle("Agrega Skill")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            addData(skill.getText().toString());
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        } else {
            Toast.makeText(this, "Maximo 5 skills", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteSkill(TextView item){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar skill");
        builder.setMessage("¿Deseas eliminar \"" + item.getText().toString() + "\"?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                viewLayout.removeView(item);
                dialog.dismiss();
            }
        })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }

    @Override
    protected  void onSaveInstanceState(Bundle outState){

        super.onSaveInstanceState(outState);

        int childCount = viewLayout.getChildCount();
        outState.putInt("skillsCount", childCount);

        for(int i = 0; i < childCount; i++){
            View child = viewLayout.getChildAt(i);
            outState.putString("skill_"+ i, ((TextView) child).getText().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int count = savedInstanceState.getInt("skillsCount", 0);

        for (int i = 0; i < count; i++) {
            TextView skill = new TextView(this);
            skill.setText(savedInstanceState.getString("skill_" + i, ""));
            skill.setOnLongClickListener(v ->{
                deleteSkill(skill);
                return true;
            });
            viewLayout.addView(skill);
        }
    }
}