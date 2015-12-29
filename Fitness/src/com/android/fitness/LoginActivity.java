package com.android.fitness;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity implements OnClickListener {

	private Button saveButton;
	private Button registerButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		saveButton = (Button) findViewById(R.id.signin_btn);
		saveButton.setOnClickListener(this);
		registerButton = (Button) findViewById(R.id.register_btn);
		registerButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.signin_btn) {
			// 
			String userName = ((EditText) findViewById(R.id.user_name))
					.getText().toString();
			String userPassword = ((EditText) findViewById(R.id.user_password))
					.getText().toString();

			getUserDetails(userName, userPassword);

		} else if (v.getId() == R.id.register_btn) {
			Intent intent = new Intent(getApplicationContext(),
					SignUpActivity.class);
			startActivity(intent);
		}

	}

	public void getUserDetails(String userName, String userPassword) {

		AndroidOpenDbHelper androidOpenDbHelperObj = new AndroidOpenDbHelper(
				this);
		SQLiteDatabase sqliteDatabase = androidOpenDbHelperObj
				.getReadableDatabase();

		String getDetailsQuery = "select * from "
				+ AndroidOpenDbHelper.TABLE_NAME_USERS + " where "+AndroidOpenDbHelper.USER_NAME+"=\""
				+ userName + "\" and "+AndroidOpenDbHelper.USER_PASSWORD+"=\"" + userPassword + "\";";
		Cursor c = sqliteDatabase.rawQuery(getDetailsQuery, null);
		if (c.moveToFirst()) {
			System.out.println();

			Toast.makeText(this,
					"Sign In successful! Get ready to be fit",
					Toast.LENGTH_LONG).show();
			Intent intent = new Intent(getApplicationContext(),
					DashboardActivity.class);
			startActivity(intent);
		}


	}

	private boolean isTableExists(AndroidOpenDbHelper androidOpenDbHelperObj,
			SQLiteDatabase db, String tableName, boolean openDb) {
		if (openDb) {
			if (db == null || !db.isOpen()) {
				db = androidOpenDbHelperObj.getReadableDatabase();
			}

			if (!db.isReadOnly()) {
				db.close();
				db = androidOpenDbHelperObj.getReadableDatabase();
			}
		}

		Cursor cursor = db
				.rawQuery(
						"select * from "
				+ AndroidOpenDbHelper.TABLE_NAME_USERS + " where "+AndroidOpenDbHelper.USER_NAME+"=\"sathiyan\""
				 +";",
						null);
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				while(cursor.moveToNext())
				{
				String name = (cursor.getString(1));
				System.out.println();
				}
				cursor.close();
				return true;
			}
			cursor.close();
		}
		return false;
	}
	
	private boolean CheckIsDataAlreadyInDBorNot(AndroidOpenDbHelper androidOpenDbHelperObj,
			SQLiteDatabase db) {
	    SQLiteDatabase sqldb = db;
	    String Query = "Select * from " + AndroidOpenDbHelper.TABLE_NAME_USERS + " where " + AndroidOpenDbHelper.USER_NAME + " = " + ""+";";
	    Cursor cursor = sqldb.rawQuery(Query, null);
	        if(cursor.getCount() <= 0){
	            cursor.close();
	            return false;
	        }
	    cursor.close();
	    return true;
	}

}
