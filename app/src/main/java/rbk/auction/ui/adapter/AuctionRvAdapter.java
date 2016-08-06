package rbk.auction.ui.adapter;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import rbk.auction.R;
import rbk.auction.database.DBHelper;

public class AuctionRvAdapter extends CursorRecyclerViewAdapter<AuctionRvAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    Cursor c;

    public AuctionRvAdapter(Activity context, Cursor c) {
        super(context, c);
        this.mInflater = LayoutInflater.from(context);
        this.c = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.auction_rv_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public int getItemCount() {
        return c.getCount();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        viewHolder.tvTitle.setText(cursor.getString(cursor.getColumnIndex(DBHelper.I_TITLE)));
        viewHolder.tvDescription.setText(cursor.getString(cursor.getColumnIndex(DBHelper.I_DESCRIPTION)));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //        private final View ll;
        TextView tvTitle;
        TextView tvDescription;
        ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.item_title);
            tvDescription = (TextView) itemView.findViewById(R.id.item_description);
//            iv = (ImageView) itemView.findViewById(R.id.iv_intermediate_item);
//            ll = itemView.findViewById(R.id.ll_intermediate_item);
        }
    }
}
