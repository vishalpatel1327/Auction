package rbk.auction.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rbk.auction.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BidFinishedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BidFinishedFragment extends Fragment {

    public BidFinishedFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static BidFinishedFragment newInstance() {
        BidFinishedFragment fragment = new BidFinishedFragment();
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
        return inflater.inflate(R.layout.fragment_bid_finished, container, false);
    }

}
