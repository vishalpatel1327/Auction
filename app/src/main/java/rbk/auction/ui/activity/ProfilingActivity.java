package rbk.auction.ui.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.File;

import rbk.auction.R;
import rbk.auction.database.DBHelper;
import rbk.auction.database.UserModel;
import rbk.auction.support.Common;

public class ProfilingActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mName;
    ImageView ivProfile, ivAddProfilePic;

    String email, password, profileImage = "";
    boolean rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiling);

        Intent intent = getIntent();
        email = intent.getStringExtra(DBHelper.U_EMAIL);
        password = intent.getStringExtra(DBHelper.U_PASSWORD);
        rememberMe = intent.getBooleanExtra("remember_me", false);

        mEmailView = (EditText) findViewById(R.id.email);
        mEmailView.setText(email);
        mName = (EditText) findViewById(R.id.name);
        mName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.profiling || id == EditorInfo.IME_NULL) {
                    attemptSignUp();
                    return true;
                }
                return false;
            }
        });

        ivProfile = (ImageView) findViewById(R.id.profile);
        ivAddProfilePic = (ImageView) findViewById(R.id.add_profile_pic);
        ivAddProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.OpenDialogForImage(ProfilingActivity.this);
            }
        });

        Button mSignUpButton = (Button) findViewById(R.id.sign_up_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Common.REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                File file = new File(getExternalCacheDir(),
                        String.valueOf(System.currentTimeMillis()) + ".jpg");
                Uri fileUri = Uri.fromFile(file);

                profileImage = fileUri.toString();

                Glide
                        .with(ProfilingActivity.this)
                        .load(fileUri)
                        .centerCrop()
                        .placeholder(R.drawable.user)
                        .crossFade()
                        .into(ivProfile);
            }
        } else if (requestCode == Common.SELECT_FILE) {

            if (resultCode == RESULT_OK) {
                Uri fileUri = data.getData();

                profileImage = fileUri.toString();

                Glide
                        .with(ProfilingActivity.this)
                        .load(fileUri)
                        .centerCrop()
                        .placeholder(R.drawable.user)
                        .crossFade()
                        .into(ivProfile);
            }
        }

    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptSignUp() {
        // Reset errors.
        mName.setError(null);

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(mName.getText().toString())) {
            mName.setError(getString(R.string.error_field_required));
            focusView = mName;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            SQLiteDatabase database = DBHelper.getInstance(this).getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DBHelper.U_NAME, mName.getText().toString());
            values.put(DBHelper.U_EMAIL, email);
            values.put(DBHelper.U_IMAGE, profileImage);
            values.put(DBHelper.U_PASSWORD, password);
            database.insert(DBHelper.TUSER, null, values);
            database.close();

            UserModel userData = new UserModel();
            userData.setId(0);
            userData.setName(mName.getText().toString());
            userData.setImage(profileImage);
            userData.setEmail(email);
            userData.setPassword(password);
            userData.setRememberMe(rememberMe);
            String userDataStr = new Gson().toJson(userData);
            Common.saveStrPref(this, Common.PREF_USER, userDataStr);

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
