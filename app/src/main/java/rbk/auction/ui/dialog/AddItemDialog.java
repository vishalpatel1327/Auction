package rbk.auction.ui.dialog;

import android.app.Dialog;
import android.content.Context;

import rbk.auction.R;

/**
 * Created by Dharmesh on 8/6/2016.
 */
public class AddItemDialog extends Dialog {
    public AddItemDialog(Context context, int themeResId) {
        super(context, R.style.MyDialog);
        setContentView(R.layout.dialog_add_item);
    }
}