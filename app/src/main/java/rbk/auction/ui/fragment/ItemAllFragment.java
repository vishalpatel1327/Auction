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
import rbk.auction.ui.adapter.ItemRvAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemAllFragment extends Fragment {

    Activity activity;
    private View contentView;
    private RecyclerView rv;

    public ItemAllFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ItemAllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemAllFragment newInstance() {
        ItemAllFragment fragment = new ItemAllFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.activity = getActivity();

        // Inflate the layout for this fragment
        contentView = inflater.inflate(R.layout.fragment_item_all, container, false);

        rv = (RecyclerView) contentView.findViewById(R.id.rv_item_all);
        rv.setLayoutManager(new LinearLayoutManager(activity));

        rv.setAdapter(new ItemRvAdapter(activity, new ArrayList<String>()));


        return contentView;
    }

}
