package rbk.auction.temp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import rbk.auction.R;
import rbk.auction.ui.dialog.AddItemDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AddItemDialog addItemDialog = new AddItemDialog(this);

        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemDialog.show();
            }
        });

    }
}
