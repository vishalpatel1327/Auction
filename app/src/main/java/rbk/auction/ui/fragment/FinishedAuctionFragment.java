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
 * Use the {@link FinishedAuctionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinishedAuctionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    public FinishedAuctionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FinishedAuctionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FinishedAuctionFragment newInstance(String param1, String param2) {
        FinishedAuctionFragment fragment = new FinishedAuctionFragment();

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
        return inflater.inflate(R.layout.fragment_finished_auction, container, false);
    }


}
