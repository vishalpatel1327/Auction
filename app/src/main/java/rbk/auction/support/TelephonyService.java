package rbk.auction.support;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by DELL-PC on 04-08-2016.
 */
public class TelephonyService {

    private static String TAG = TelephonyService.class.getSimpleName();

    static String getPhoneNumber(Context context) {
        String phoneNumber = null;

        TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        phoneNumber = tMgr.getLine1Number();

        if (phoneNumber != null && !phoneNumber.isEmpty()) return phoneNumber;

        String main_data[] = {"data1", "is_primary", "data3", "data2", "data1", "is_primary", "photo_uri", "mimetype"};
        Object object = context.getContentResolver().query(Uri.withAppendedPath(android.provider.ContactsContract.Profile.CONTENT_URI, "data"),
                main_data, "mimetype=?",
                new String[]{"vnd.android.cursor.item/phone_v2"},
                "is_primary DESC");
        if (object != null) {
            do {
                if (!((Cursor) (object)).moveToNext())
                    break;
                String s1 = ((Cursor) (object)).getString(4);
                Log.e(TAG, "Tel : " + s1);
            } while (true);
            ((Cursor) (object)).close();
        }

        return phoneNumber;
    }

}
