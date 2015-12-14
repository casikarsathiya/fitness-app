package com.android.fitness;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

// A helper class to manage database creation and version management. 
public class AndroidOpenDbHelper extends SQLiteOpenHelper {
	// Database attributes
	public static final String DB_NAME = "fitness_db";
	public static final int DB_VERSION = 1;

	// Table attributes
	public static final String TABLE_NAME_USERS = "fitness_users";

	public static final String USER_NAME = "user_name";
	public static final String USER_PASSWORD = "user_password";
	public static final String GENDER = "gender";
	public static final String AGE = "age";
	public static final String USER_EMAIL = "user_email";
	public static final String USER_PHONE = "user_phone";
	public static final String USER_ALT_PHONE = "user_alt_phone";
	public static final String USER_LIVES = "user_lives";
	public static final String USER_ZIP = "user_zip";
	public static final String USER_HEIGHT = "user_height";
	public static final String USER_WEIGHT = "user_weight";

	public AndroidOpenDbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	// Called when the database is created for the first time.
	// This is where the creation of tables and the initial population of the
	// tables should happen.
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sqlQueryToDeleteUndergraduateDetailsTable = "drop table if exists "+TABLE_NAME_USERS+";";
		db.execSQL(sqlQueryToDeleteUndergraduateDetailsTable);
		String sqlQueryToCreateUndergraduateDetailsTable = "create table if not exists "
				+ TABLE_NAME_USERS
				+ " ( "
				+ BaseColumns._ID
				+ " integer primary key autoincrement, "
				+ USER_NAME
				+ " text not null, "
				+ USER_PASSWORD
				+ " text not null, "
				+ USER_EMAIL
				+ " text not null, "
				+ USER_PHONE
				+ " real not null, "
				+ USER_ALT_PHONE
				+ " real not null, "
				+ USER_LIVES
				+ " text not null, "
				+ USER_ZIP
				+ " real not null, "
				+ USER_HEIGHT
				+ " real not null, " + USER_WEIGHT + " real not null);";
		// Execute a single SQL statement that is NOT a SELECT or any other SQL
		// statement that returns data.
		db.execSQL(sqlQueryToCreateUndergraduateDetailsTable);
	}

	public void createTable(SQLiteDatabase db) {
		String sqlQueryToDeleteUndergraduateDetailsTable = "drop table if exists "+TABLE_NAME_USERS+";";
		db.execSQL(sqlQueryToDeleteUndergraduateDetailsTable);
		String sqlQueryToCreateUndergraduateDetailsTable = "create table if not exists "
				+ TABLE_NAME_USERS
				+ " ( "				
				+ USER_NAME		
				+ " text not null, "
				+ USER_PASSWORD				
				+ " text not null, "
				+ GENDER
				+ " text not null, "
				+ AGE
				+ " integer not null, "
				+ USER_EMAIL
				+ " text not null);";
		// Execute a single SQL statement that is NOT a SELECT or any other SQL
		// statement that returns data.
		db.execSQL(sqlQueryToCreateUndergraduateDetailsTable);
	}

	// onUpgrade method is use when we need to upgrade the database in to a new
	// version
	// As an example, the first release of the app contains DB_VERSION = 1
	// Then with the second release of the same app contains DB_VERSION = 2
	// where you may have add some new tables or alter the existing ones
	// Then we need check and do relevant action to keep our pass data and move
	// with the next structure
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion == 1 && newVersion == 2) {
			// Upgrade the database
		}
	}
}