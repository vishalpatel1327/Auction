package rbk.auction.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import rbk.auction.R;

public class Splash extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    private long splashDelay = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, LoginActivity.class));
                finish();
            }
        }, splashDelay);

    }
}
