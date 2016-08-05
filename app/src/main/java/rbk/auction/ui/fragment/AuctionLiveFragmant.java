package rbk.auction.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import rbk.auction.R;
import rbk.auction.ui.adapter.AuctionRvAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AuctionLiveFragmant#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuctionLiveFragmant extends Fragment {


    Activity activity;
    private RecyclerView rv;
    private View contentView;

    public AuctionLiveFragmant() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AuctionLiveFragmant newInstance() {
        AuctionLiveFragmant fragment = new AuctionLiveFragmant();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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


        rv = (RecyclerView) contentView.findViewById(R.id.rv_auction);
        rv.setLayoutManager(new LinearLayoutManager(activity));

        rv.setAdapter(new AuctionRvAdapter(activity, new ArrayList<String>()));

        return contentView;
    }


}
