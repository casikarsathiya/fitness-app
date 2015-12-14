package com.android.fitness;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SignUpActivity extends Activity implements OnClickListener {

	public static final String TABLE_NAME_USERS = "fitness_users";
	AndroidOpenDbHelper androidOpenDbHelperObj = new AndroidOpenDbHelper(this);
	RadioGroup radio;
	SQLiteDatabase db;
	private Button signUpButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		db = androidOpenDbHelperObj.getWritableDatabase();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		signUpButton = (Button) findViewById(R.id.signup_btn);
		signUpButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.signup_btn) {

			registerUser();

		}

	}

	private void registerUser() {

		
		String userName = ((EditText) findViewById(R.id.signup_user_name))
				.getText().toString();
		String password = ((EditText) findViewById(R.id.signup_user_password))
				.getText().toString();
		String gender = "";
		
		String age = ((EditText) findViewById(R.id.signup_age)).getText()
				.toString();
		String email = ((EditText) findViewById(R.id.signup_email)).getText()
				.toString();
				
		androidOpenDbHelperObj.createTable(db);
		String sqlQueryToCreateUndergraduateDetailsTable = "insert into "
				+ TABLE_NAME_USERS + " values ( \"" + userName + "\" ,\""
				+ password + "\",\"" + gender + "\", "+ age + ",\""
				+ email + "\"" + ");";

		try {
			db.execSQL(sqlQueryToCreateUndergraduateDetailsTable);
			Intent intent = new Intent(getApplicationContext(),
					LoginActivity.class);
			startActivity(intent);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean isTableExists(String tableName, boolean openDb) {
		if (openDb) {
			if (db == null || !db.isOpen()) {
				db = androidOpenDbHelperObj.getReadableDatabase();
			}

			if (!db.isReadOnly()) {
				db.close();
				db = androidOpenDbHelperObj.getReadableDatabase();
			}
		}

		Cursor cursor = db.rawQuery(
				"select DISTINCT tbl_name from sqlite_master where tbl_name = '"
						+ tableName + "'", null);
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cursor.close();
				return true;
			}
			cursor.close();
		}
		return false;
	}

}
