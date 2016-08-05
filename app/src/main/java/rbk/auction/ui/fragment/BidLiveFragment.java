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
import rbk.auction.ui.adapter.BidRvAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BidLiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BidLiveFragment extends Fragment {
    private View contentView;
    private RecyclerView rv;
    private Activity activity
            ;


    public BidLiveFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment BidLiveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BidLiveFragment newInstance() {
        BidLiveFragment fragment = new BidLiveFragment();
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
        contentView = inflater.inflate(R.layout.fragment_bid_live, container, false);


        rv = (RecyclerView) contentView.findViewById(R.id.rv_auction);
        rv.setLayoutManager(new LinearLayoutManager(activity));

        rv.setAdapter(new BidRvAdapter(activity, new ArrayList<String>()));

        return contentView;
    }

}
