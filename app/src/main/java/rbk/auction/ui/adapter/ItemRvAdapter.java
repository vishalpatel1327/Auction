package rbk.auction.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import rbk.auction.R;

/**
 * Created by DELL-PC on 06-08-2016.
 */

public class ItemRvAdapter extends RecyclerView.Adapter<ItemRvAdapter.ViewHolder> {
    private LayoutInflater mInflater;

    public ItemRvAdapter(Activity context, List<String> a) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //        private final View ll;
        TextView tvGridPart;
        ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
//            tvGridPart = (TextView) itemView.findViewById(R.id.tv_itermediate_item);
//            iv = (ImageView) itemView.findViewById(R.id.iv_intermediate_item);
//            ll = itemView.findViewById(R.id.ll_intermediate_item);
        }
    }
}
