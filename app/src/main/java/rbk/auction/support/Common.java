package rbk.auction.support;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import java.io.File;

/**
 * Created by DELL-PC on 04-08-2016.
 */
public class Common {

    public static final String PREF_USER = "pref_user";
    public static final String BOT_USER = "bot_user";
    public static final int SELECT_FILE = 11;
    public static final int REQUEST_CAMERA = 22;
    public static final int MY_PERMISSIONS_REQUEST_GALLERY = 23;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 24;

    public static void saveStrPref(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("AuctionPref", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void saveIntPref(Context context, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("AuctionPref", Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static String readStrPref(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("AuctionPref", Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }

    public static int readIntPref(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences("AuctionPref", Context.MODE_PRIVATE);
        return pref.getInt(key, -1);
    }

    public static void OpenDialogForImage(final Activity activity) {

        final CharSequence[] items = {
                "Gallery", "Camera", "Cancel"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                Intent intent;

                switch (item) {
                    case 0:

                        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                                ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                            intent = new Intent(
                                    Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            activity.startActivityForResult(
                                    Intent.createChooser(intent, "Select File"),
                                    SELECT_FILE);
                        } else {
                            ActivityCompat.requestPermissions(activity,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    Common.MY_PERMISSIONS_REQUEST_GALLERY);
                        }


                        break;
                    case 1:

                        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                                && ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            Intent i1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            activity.startActivityForResult(i1, REQUEST_CAMERA);
                        } else {
                            ActivityCompat.requestPermissions(activity,
                                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    Common.MY_PERMISSIONS_REQUEST_CAMERA);
                        }

                        break;
                    case 2:

                        break;
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
