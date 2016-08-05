package rbk.auction.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;

import rbk.auction.R;
import rbk.auction.database.UserModel;
import rbk.auction.support.Common;

public class Splash extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    private long splashDelay = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String user = Common.readStrPref(Splash.this, Common.PREF_USER);
                if (user != null) {
                    UserModel userModel = new Gson().fromJson(user, UserModel.class);
                    if (userModel.isRememberMe()) {
                        startActivity(new Intent(Splash.this, HomeActivity.class));
                    } else {
                        startActivity(new Intent(Splash.this, LoginActivity.class));
                    }

                } else {
                    startActivity(new Intent(Splash.this, LoginActivity.class));
                }
                finish();
            }
        }, splashDelay);

    }
}
