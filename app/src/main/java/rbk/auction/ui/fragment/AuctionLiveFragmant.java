package rbk.auction.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rbk.auction.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AuctionLiveFragmant#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuctionLiveFragmant extends Fragment {


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
        return inflater.inflate(R.layout.fragment_live_auction_fragmant, container, false);
    }


}
