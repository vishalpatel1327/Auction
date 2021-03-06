package rbk.auction.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rbk.auction.R;
import rbk.auction.database.DBHelper;
import rbk.auction.ui.adapter.AuctionRvAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AuctionLiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuctionLiveFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private String queryString;


    Activity activity;
    private RecyclerView rv;
    private View contentView;
    private SQLiteDatabase database;

    public AuctionLiveFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public AuctionLiveFragment(String queryLive) {
        queryString = queryLive;
    }


    // TODO: Rename and change types and number of parameters
    public static AuctionLiveFragment newInstance(String queryLive) {
        AuctionLiveFragment fragment = new AuctionLiveFragment(queryLive);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        this.activity = getActivity();
        contentView = inflater.inflate(R.layout.fragment_live_auction_fragmant, container, false);

        DBHelper helper = new DBHelper(activity);
        database = helper.getWritableDatabase();

        rv = (RecyclerView) contentView.findViewById(R.id.rv_auction);
        rv.setLayoutManager(new LinearLayoutManager(activity));

        Cursor cursor = database.rawQuery(queryString, null);

        Log.e("size of cursor", cursor.getCount() + "");


        rv.setAdapter(new AuctionRvAdapter(activity, cursor));


        return contentView;
    }


}
