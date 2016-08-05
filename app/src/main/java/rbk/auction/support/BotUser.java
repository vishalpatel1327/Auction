package rbk.auction.support;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import rbk.auction.database.DBHelper;
import rbk.auction.database.UserModel;

/**
 * Created by HP on 05-08-2016.
 */
public class BotUser {

    UserModel userData;

    public BotUser(Context context) {
        String botUser = Common.readStrPref(context, Common.BOT_USER);

        if (botUser != null) {

        } else {

            userData = new UserModel();
            userData.setId(0);
            userData.setEmail("bot@rbk.com");
            userData.setPassword("test@123");

            SQLiteDatabase database = DBHelper.getInstance(context).getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DBHelper.U_EMAIL, userData.getEmail());
            values.put(DBHelper.U_PASSWORD, userData.getPassword());
            database.insert(DBHelper.TUSER, null, values);
            database.close();

            String userDataStr = new Gson().toJson(userData);
            Common.saveStrPref(context, Common.BOT_USER, userDataStr);
        }
    }

    public static BotUser getInstance(Context context) {
        return new BotUser(context);
    }
}
