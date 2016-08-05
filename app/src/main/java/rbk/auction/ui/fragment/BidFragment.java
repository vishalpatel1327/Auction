package rbk.auction.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rbk.auction.R;
import rbk.auction.ui.adapter.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BidFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BidFragment extends Fragment {


    private ViewPager viewPager;
    private View contentView;
    private TabLayout tabLayout;

    public BidFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BidFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BidFragment newInstance() {
        BidFragment fragment = new BidFragment();
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
        contentView = inflater.inflate(R.layout.fragment_bid, container, false);


        viewPager = (ViewPager) contentView.findViewById(R.id.viewpager_fragment_bid);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) contentView.findViewById(R.id.tabs_fragment_bid);
        tabLayout.setupWithViewPager(viewPager);

        return contentView;
    }


    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        adapter.addFragment(new BidLiveFragment(), "Live Bid");
        adapter.addFragment(new BidLiveFragment(), "Finished Bid");
        viewPager.setAdapter(adapter);
    }


}
