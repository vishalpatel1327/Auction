package rbk.auction.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rbk.auction.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AuctionFinishedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuctionFinishedFragment extends Fragment {
    private View contentView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    // TODO: Rename parameter arguments, choose names that match


    public AuctionFinishedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment AuctionFinishedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AuctionFinishedFragment newInstance() {
        AuctionFinishedFragment fragment = new AuctionFinishedFragment();

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

        contentView = inflater.inflate(R.layout.fragment_finished_auction, container, false);





        return contentView;
    }




}
