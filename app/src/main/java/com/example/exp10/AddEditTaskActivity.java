package com.example.exp10;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditTaskActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.todoapp.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.todoapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.todoapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.todoapp.EXTRA_PRIORITY";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner prioritySpinner; // Spinner for priority selection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        prioritySpinner = findViewById(R.id.spinner_priority); // Initialize spinner

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Task");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            prioritySpinner.setSelection(intent.getIntExtra(EXTRA_PRIORITY, 2)); // Set spinner to existing priority
        } else {
            setTitle("Add Task");
        }

        Button saveButton = findViewById(R.id.button_save_task);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        int priority = prioritySpinner.getSelectedItemPosition(); // Get selected priority

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Please fill out both fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority); // Pass the selected priority
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }
}
