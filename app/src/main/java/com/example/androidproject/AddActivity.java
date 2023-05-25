package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText date_input, title_input, PageNum_input, ChildCom_input, ParentCom_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        date_input = findViewById(R.id.date_input);
        PageNum_input = findViewById(R.id.PageNum_input);
        ChildCom_input = findViewById(R.id.ChildCom_input);
        ParentCom_input = findViewById(R.id.ParentCom_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(AddActivity.this);
                myDB.addBook(title_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        PageNum_input.getText().toString().trim(),
                        ChildCom_input.getText().toString().trim(),
                        ParentCom_input.getText().toString().trim());
                        //finish();
            }
        });
    }
}