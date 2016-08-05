package rbk.auction.ui.dialog;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import rbk.auction.R;
import rbk.auction.database.DBHelper;
import rbk.auction.database.UserModel;
import rbk.auction.support.Common;
import rbk.auction.ui.activity.ProfilingActivity;

public class AddItemDialog extends Dialog implements View.OnClickListener {
    ImageView itemImage;
    EditText etName, etDescription, etInitialPrice;
    AppCompatButton btAdd;
    private String itemImageString = "";


    public AddItemDialog(Context context) {
        super(context, R.style.MyDialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_add_item);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        findView();
    }

    private void findView() {
        itemImage = (ImageView) findViewById(R.id.iv_dialog_add_item_image);
        itemImage.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.et_dialog_add_item_name);
        etDescription = (EditText) findViewById(R.id.et_dialog_add_item_description);
        etInitialPrice = (EditText) findViewById(R.id.et_dialog_add_item_initial_price);

        btAdd = (AppCompatButton) findViewById(R.id.bt_dialog_add_item_add);
        btAdd.setOnClickListener(this);
    }

    private void addItem() {

        etName.setError(null);
        etDescription.setError(null);
        etInitialPrice.setError(null);

        String name = etName.getText().toString();
        String description = etDescription.getText().toString();
        String price = etInitialPrice.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(name)) {
            etName.setError(getContext().getString(R.string.error_field_required));
            focusView = etName;
            cancel = true;
        }

        if (TextUtils.isEmpty(description)) {
            etDescription.setError(getContext().getString(R.string.error_field_required));
            focusView = etDescription;
            cancel = true;
        }

        if (TextUtils.isEmpty(price)) {
            etInitialPrice.setError(getContext().getString(R.string.error_field_required));
            focusView = etInitialPrice;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            SQLiteDatabase database;

            database = DBHelper.getInstance(getContext()).getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put(DBHelper.I_TITLE, name);
            values.put(DBHelper.I_DESCRIPTION, description);
            values.put(DBHelper.I_INIT_PRICE, price);
            values.put(DBHelper.I_IMAGE, itemImageString);
            database.insert(DBHelper.TITEM, null, values);
            database.close();
            dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_dialog_add_item_image:
                Common.OpenDialogForImage(getOwnerActivity());
                break;
            case R.id.bt_dialog_add_item_add:
                addItem();
                break;
        }
    }

    public void setItemImage(Uri itemImageUri)

    {

        if (itemImage != null) {
            Glide
                    .with(getContext())
                    .load(itemImageUri)
                    .centerCrop()
                    .placeholder(R.drawable.user)
                    .crossFade()
                    .into(itemImage);
        }
        itemImageString = itemImageUri.toString();
    }
}