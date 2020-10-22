package com.android.emrannik.dashboarddesign.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.emrannik.dashboarddesign.R;
import com.android.emrannik.dashboarddesign.adapter.YoutubeVideoAdapter;


import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {

    private View view;
    private ArrayList<String> youtubeVideoArrayList;
    private ArrayList<String> youtubeVideoTitleArrayList;

    private ArrayList<String> youtubeVideoArrayList1;
    private ArrayList<String> youtubeVideoTitleArrayList1;

    private FragmentActivity myContext;
    private static final String TAG = FoodFragment.class.getSimpleName();

    public static final String PAGE_TAB1 = "BANGLA";

    public FoodFragment() {
        // Required empty public constructor
    }


    public static FoodFragment newInstance() {
        FoodFragment fragment = new FoodFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_food, container, false);
        generateDummyVideoList();

        //
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvPopularFood);

        final RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.rvMoreFood);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(myContext,LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setHasFixedSize(true);

        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(linearLayoutManager);

        // 3. create an adapter

        //get the video id array from strings.xml
        final YoutubeVideoAdapter mAdapter = new YoutubeVideoAdapter(myContext,youtubeVideoArrayList,youtubeVideoTitleArrayList);

        final YoutubeVideoAdapter mAdapter1 = new YoutubeVideoAdapter(myContext,youtubeVideoArrayList1,youtubeVideoTitleArrayList1);


        // display GlidLayout with 3 column//
        recyclerView2.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //end
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        recyclerView2.setAdapter(mAdapter1);
        //set click event
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }



    public void generateDummyVideoList() {
        youtubeVideoArrayList = new ArrayList<>();
        youtubeVideoTitleArrayList = new ArrayList<>();

        youtubeVideoArrayList1 = new ArrayList<>();
        youtubeVideoTitleArrayList1 = new ArrayList<>();

        //get the video id array from strings.xml
        String[] videoIDArray = getResources().getStringArray(R.array.video_bangla);
        String[] videoIDArrayHindi = getResources().getStringArray(R.array.video_hindi);
        // String[] videoIDArray1 = getResources().getStringArray(R.array.video_title_array);

        //add all videos to array list
        Collections.addAll(youtubeVideoArrayList, videoIDArray);
        Collections.addAll(youtubeVideoArrayList1, videoIDArrayHindi);

        //  Collections.addAll(youtubeVideoTitleArrayList, videoIDArray1);
    }

}
