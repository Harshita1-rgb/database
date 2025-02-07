package com.example.exp10;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class TaskMigration extends Migration {
    public TaskMigration() {
        super(1, 2); // From version 1 to version 2
    }

    @Override
    public void migrate(SupportSQLiteDatabase database) {
        // Create a new table with the updated schema
        database.execSQL("CREATE TABLE new_task_table ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "title TEXT, "
                + "description TEXT, "
                + "priority INTEGER NOT NULL DEFAULT 3)");

        // Copy the data from the old table to the new table
        database.execSQL("INSERT INTO new_task_table (id, title, description) "
                + "SELECT id, title, description FROM task_table");

        // Drop the old table
        database.execSQL("DROP TABLE task_table");

        // Rename the new table to the old table's name
        database.execSQL("ALTER TABLE new_task_table RENAME TO task_table");
    }
}
