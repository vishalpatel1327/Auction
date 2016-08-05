package rbk.auction.ui.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import rbk.auction.R;
import rbk.auction.database.DBHelper;
import rbk.auction.database.UserModel;
import rbk.auction.support.Common;

public class ProfilingActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiling);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);

        mName = (EditText) findViewById(R.id.name);
        mName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mName.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mName.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mName.setError(getString(R.string.error_invalid_password));
            focusView = mName;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            SQLiteDatabase database = DBHelper.getInstance(this).getReadableDatabase();
            Cursor c = database.query(DBHelper.TUSER, null, DBHelper.U_EMAIL + " = ?", new String[]{email}, null, null, null);

            if (c != null && c.getCount() == 1) {
                UserModel userData = new UserModel();
                c.moveToFirst();
                userData.setId(c.getInt(c.getColumnIndex(DBHelper.U_ID)));
                userData.setEmail(c.getString(c.getColumnIndex(DBHelper.U_EMAIL)));
                userData.setPassword(c.getString(c.getColumnIndex(DBHelper.U_PASSWORD)));
                String userDataStr = new Gson().toJson(userData);
                Common.saveStrPref(this, Common.PREF_USER, userDataStr);
                database.close();
            } else {
                database.close();
                database = DBHelper.getInstance(this).getWritableDatabase();


                ContentValues values = new ContentValues();
                values.put(DBHelper.U_EMAIL, email);
                values.put(DBHelper.U_PASSWORD, password);
                database.insert(DBHelper.TUSER, null, values);


                UserModel userData = new UserModel();
                userData.setId(-1);
                userData.setEmail(email);
                userData.setPassword(password);
                String userDataStr = new Gson().toJson(userData);
                Common.saveStrPref(this, Common.PREF_USER, userDataStr);
                database.close();
            }

            if (c != null)
                c.close();

            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
