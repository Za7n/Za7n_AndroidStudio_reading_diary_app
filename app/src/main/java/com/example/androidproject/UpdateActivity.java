package com.example.androidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText date_input, title_input, pages_input, child_input, teacher_input;
    Button update_button, delete_button;
    Button email_button;

    String id, date, title, pages, child, teacher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        date_input = findViewById(R.id.date_input2);
        title_input = findViewById(R.id.title_input2);
        pages_input = findViewById(R.id.PageNum_input2);
        child_input = findViewById(R.id.ChildCom_input2);
        teacher_input = findViewById(R.id.ParentCom_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        email_button = findViewById(R.id.email_button);

        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                date = date_input.getText().toString().trim();
                title = title_input.getText().toString().trim();
                pages = pages_input.getText().toString().trim();
                child = child_input.getText().toString().trim();
                teacher = teacher_input.getText().toString().trim();
                myDB.updateData(id, date, title, pages, child, teacher);
                finish();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        //getAndSetIntentData();
        //DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
        //myDB.updateData(id, title, title, pages, child, teacher);

        //Email button
        //
        //email_button

        email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Intent.ACTION_SEND);
                //intent.setData(Uri.parse("mailto:"));
                //sendEmailtest(date, title, pages, child, teacher);
                sendEmailtest3(date, title, pages, child, teacher);


            }


        // the trick is to do it in a function, then call it on the onclicklistener
        // when called it crashes
        });


    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("date") && getIntent().hasExtra("pages")
                && getIntent().hasExtra("child") && getIntent().hasExtra("teacher")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            date = getIntent().getStringExtra("date");
            title = getIntent().getStringExtra("title");
            pages = getIntent().getStringExtra("pages");
            child = getIntent().getStringExtra("child");
            teacher = getIntent().getStringExtra("teacher");

            //Setting Intent Data

            date_input.setText(date);
            title_input.setText(title);
            pages_input.setText(pages);
            child_input.setText(child);
            teacher_input.setText(teacher);


        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to delete this?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                myDB.deleteData(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }


    void sendEmailtest(String date, String title, String pages, String child, String teacher) {
        //Intent intent = new Intent(Intent.ACTION_SEND);
         //   intent.setData(Uri.parse("mailto:"));


        Uri mailUri = Uri.parse("mailto:k1814048@kingston.ac.uk");
        String subject = "This is a test email";
        String body = "This is my wonderful test email.\n\n";

        body += "This is not spam at all, but very very useful.\n\n";
        body += "I could click the button many times and send lots of them.";
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO,mailUri);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, title);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        } else {
            Toast ehraw = Toast.makeText(this,"No email app installed!",Toast.LENGTH_LONG);
            ehraw.show();
        }


        //contains email logic
    }
    void sendEmailtest2() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","test@test.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SOME_SUBJECT");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "test");
        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
            //Log.v("MainActivity", "Sharing the order summary to email");
        }
        catch (android.content.ActivityNotFoundException ex) {
            //Log.d("MainActivity", "No email app installed!");
            Toast ehraw = Toast.makeText(this,"No email app installed!",Toast.LENGTH_LONG);
            ehraw.show();
        }
    }
    void sendEmailtest3(String date, String title, String pages, String child, String teacher){
        Intent selectorIntent = new Intent(Intent.ACTION_SENDTO);
        selectorIntent.setData(Uri.parse("mailto:"));
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "test@test.com");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Dear  " + "\n" + "Here is the reading diary entry");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Date: " + date +"\n" + "Title: " + title +"\n" + "Pages read of the book: " + pages + "\n" + "Student comments: " + child + "\n" + "Title: " + teacher + "\n");
        emailIntent.setSelector( selectorIntent );
        startActivity(Intent.createChooser(emailIntent, "Send feedback to XYZ"));
    }
}