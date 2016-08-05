package rbk.auction.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;

import rbk.auction.R;
import rbk.auction.support.Common;
import rbk.auction.ui.dialog.AddItemDialog;
import rbk.auction.ui.fragment.AuctionFragment;
import rbk.auction.ui.fragment.BidFragment;
import rbk.auction.ui.fragment.ItemFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AuctionFragment auctionFragment = AuctionFragment.newInstance();
    ItemFragment itemFragment = ItemFragment.newInstance();
    BidFragment bidFragment = BidFragment.newInstance();
    private AddItemDialog addItemDialog;
    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activity = this;

        addItemDialog = new AddItemDialog(this);
        addItemDialog.setOwnerActivity(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemDialog.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view items_rv_item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_auctions) {
            // Handle the camera action
            replaceFragment(auctionFragment);
        } else if (id == R.id.nav_my_items) {
            replaceFragment(itemFragment);
        } else if (id == R.id.nav_my_bids) {
            replaceFragment(bidFragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_home, fragment);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(HomeActivity.class.getSimpleName(), "onActivityResult" + requestCode);

        if (requestCode == Common.REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");


                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(activity.getContentResolver(), photo, "Title", null);
                Uri fileUri = Uri.parse(path);
                addItemDialog.setItemImage(fileUri);
                Log.e(HomeActivity.class.getSimpleName(), fileUri.toString());


            }
        } else if (requestCode == Common.SELECT_FILE) {

            if (resultCode == RESULT_OK) {
                Uri fileUri = data.getData();


                addItemDialog.setItemImage(fileUri);
            }
        }

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (Common.MY_PERMISSIONS_REQUEST_GALLERY == requestCode) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                activity.startActivityForResult(
                        Intent.createChooser(intent, "Select File"),
                        Common.SELECT_FILE);
            } else {


            }
        } else if (Common.MY_PERMISSIONS_REQUEST_CAMERA == requestCode) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(activity.getExternalCacheDir(),
                        String.valueOf(System.currentTimeMillis()) + ".jpg");
                Uri fileUri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                activity.startActivityForResult(intent, Common.REQUEST_CAMERA);

            } else {


            }
        }
    }
}
