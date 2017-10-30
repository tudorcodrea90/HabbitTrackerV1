package com.example.android.habbittrackerv1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import data.HabitContract;
import data.HabitDbHelper;


public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Insert dummy records */
        HabitDbHelper mDbHelper = new HabitDbHelper(this);

        mDbHelper.insertRecord(
                "Physical Excercises",
                "2017-13-07",
                80,
                HabitContract.HabitsEntry.DURATION_MINUTE,
                "Continuing the exercise regime",
                HabitContract.HabitsEntry.BADGE_GOOD);

        mDbHelper.insertRecord(
                "Practise French Language",
                "2017-13-07",
                40,
                HabitContract.HabitsEntry.DURATION_MINUTE,
                "I want to do more of it",
                HabitContract.HabitsEntry.BADGE_BELOW_AVERAGE);


        /** Read the table records and write to log for test */
        Cursor readCursor = mDbHelper.queryAllRecords();

        try {
            while (readCursor.moveToNext()) {
                Log.i(LOG_TAG,
                        "Habit >> " + readCursor.getInt(0) + " / "
                                + readCursor.getString(1) + " / "
                                + readCursor.getString(2) + " / "
                                + readCursor.getInt(3) + " " + readCursor.getString(4) + " / "
                                + readCursor.getString(5) + " / "
                                + readCursor.getInt(6));
            }
        } finally {
            readCursor.close();
        }
    }
}