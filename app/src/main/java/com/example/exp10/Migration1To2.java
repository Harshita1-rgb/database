package com.example.exp10;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration1To2 extends Migration {
    public Migration1To2() {
        super(1, 2);
    }

    @Override
    public void migrate(SupportSQLiteDatabase database) {
        // This is an example of adding a new column
        database.execSQL("ALTER TABLE task_table ADD COLUMN priority INTEGER DEFAULT 3"); // Assuming default priority of 3
    }
}
