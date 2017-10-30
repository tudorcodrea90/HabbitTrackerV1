package data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tudor Cristian on 13.07.2017.
 */


/**
 * Database helper for Habitsracker. Manages database creation and version management.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "habits.db";

    /** Database version */
    private static final int DATABASE_VERSION = 1;


    /**
     * Default Constructor
     * @param context
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method is called when the database is created for the first time
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a String that contains the SQL statement to create the table
        String SQL_CREATE_TABLE =  "CREATE TABLE " + HabitContract.HabitsEntry.TABLE_NAME + " ("
                + HabitContract.HabitsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitContract.HabitsEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitContract.HabitsEntry.COLUMN_HABIT_DATE + " TEXT NOT NULL, "
                + HabitContract.HabitsEntry.COLUMN_HABIT_DURATION + " INTEGER NOT NULL, "
                + HabitContract.HabitsEntry.COLUMN_HABIT_DURATION_UNIT + " TEXT NOT NULL, "
                + HabitContract.HabitsEntry.COLUMN_HABIT_FACTS + " TEXT, "
                + HabitContract.HabitsEntry.COLUMN_HABIT_BADGE + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    /**
     * This method inserts records in the Habits table
     * @param name - Name of the habit (e.g. Went for a run)
     * @param date - Date when it has happened (e.g. 2017-07-07)
     * @param duration - Duration of the habit (e.g. 50)
     * @param durationUnit - Duration unit (e.g. min)
     * @param facts - (Things observed during practice)
     * @param badge - Assign an achievement badge (e.g. 2, which means "good")
     */
    public void insertRecord(String name, String date, int duration, String durationUnit, String facts, int badge) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues habitValues = new ContentValues();
        habitValues.put(HabitContract.HabitsEntry.COLUMN_HABIT_NAME, name);
        habitValues.put(HabitContract.HabitsEntry.COLUMN_HABIT_DATE, date);
        habitValues.put(HabitContract.HabitsEntry.COLUMN_HABIT_DURATION, duration);
        habitValues.put(HabitContract.HabitsEntry.COLUMN_HABIT_DURATION_UNIT, durationUnit);
        habitValues.put(HabitContract.HabitsEntry.COLUMN_HABIT_FACTS, facts);
        habitValues.put(HabitContract.HabitsEntry.COLUMN_HABIT_BADGE, badge);

        sqLiteDatabase.insert(HabitContract.HabitsEntry.TABLE_NAME, null, habitValues);
    }


    public Cursor queryAllRecords() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] projection = {
                HabitContract.HabitsEntry._ID,
                HabitContract.HabitsEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitsEntry.COLUMN_HABIT_DATE,
                HabitContract.HabitsEntry.COLUMN_HABIT_DURATION,
                HabitContract.HabitsEntry.COLUMN_HABIT_DURATION_UNIT,
                HabitContract.HabitsEntry.COLUMN_HABIT_FACTS,
                HabitContract.HabitsEntry.COLUMN_HABIT_BADGE
        };

        return sqLiteDatabase.query(
                HabitContract.HabitsEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }

    /**
     * This method is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // The database is still at version 1, so no action needed here
    }
}