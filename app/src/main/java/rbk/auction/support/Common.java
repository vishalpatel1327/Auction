package rbk.auction.support;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DELL-PC on 04-08-2016.
 */
public class Common {

    public static final String PREF_USER = "pref_user";

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


}
