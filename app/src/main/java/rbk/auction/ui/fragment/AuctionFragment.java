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
import rbk.auction.database.DBHelper;
import rbk.auction.ui.adapter.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AuctionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuctionFragment extends Fragment {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private View contentView;
    //    private String queryLive = "select I.* from " + DBHelper.TITEM + " I inner join " + DBHelper.TAUCTION + " A on (I." + DBHelper.I_ID + "= A." + DBHelper.A_ITEM_ID + " and A." + DBHelper.A_STATUS + " = 0)";
    private String queryLive = "SELECT i.* FROM " + DBHelper.TITEM + " i LEFT JOIN " + DBHelper.TAUCTION + " a ON (i." + DBHelper.I_ID + " = A." + DBHelper.A_ITEM_ID + ") WHERE A." + DBHelper.A_ITEM_ID + " IS NULL";
//    private String queryLive = "select * from users";

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

        contentView = inflater.inflate(R.layout.fragment_auction, container, false);

        viewPager = (ViewPager) contentView.findViewById(R.id.viewpager_fragment_auction);
//        setupViewPager(viewPager);


        return contentView;
    }

    private void setupViewPager(ViewPager viewPager) {

        Log.e(getClass().getSimpleName(), "setupAdapter");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(AuctionLiveFragment.newInstance(queryLive), "Live Auction");
        adapter.addFragment(AuctionLiveFragment.newInstance(queryLive), "Finished Auction");
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
