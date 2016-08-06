package rbk.auction.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rbk.auction.R;
import rbk.auction.ui.adapter.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AuctionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuctionFragment extends Fragment {


    AuctionLiveFragment liveFragment = new AuctionLiveFragment();
    AuctionLiveFragment finishedFragment = new AuctionLiveFragment();
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private View contentView;

    public AuctionFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AuctionFragment newInstance() {
        AuctionFragment fragment = new AuctionFragment();
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

        liveFragment.setRetainInstance(true);
        finishedFragment.setRetainInstance(true);

        contentView = inflater.inflate(R.layout.fragment_auction, container, false);

        viewPager = (ViewPager) contentView.findViewById(R.id.viewpager_fragment_auction);
//        setupViewPager(viewPager);


        return contentView;
    }

    private void setupViewPager(ViewPager viewPager) {

        Log.e(getClass().getSimpleName(), "setupAdapter");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(liveFragment, "Live Auction");
        adapter.addFragment(finishedFragment, "Finished Auction");
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) contentView.findViewById(R.id.tabs_fragment_auction);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
        setupViewPager(viewPager);
    }
}
