package com.ishaan.corify;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NewsFragment extends Fragment {

    private ArrayList<String> source = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> description = new ArrayList<>();
    private ArrayList<String> url = new ArrayList<>();
    private ArrayList<String> imageURL = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private ArrayList<String> source2 = new ArrayList<>();
    private ArrayList<String> title2 = new ArrayList<>();
    private ArrayList<String> description2 = new ArrayList<>();
    private ArrayList<String> url2 = new ArrayList<>();
    private ArrayList<String> imageURL2 = new ArrayList<>();
    private RecyclerView recyclerView2;
    private RecyclerViewAdapter recyclerViewAdapter2;

    private TabHost tabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        tabHost = view.findViewById(R.id.tabHost);
        tabHost.setup();

        final SharedPreferences[] sharedPreferences = {getActivity().getSharedPreferences("com.ishaanohri.corify", Context.MODE_PRIVATE)};
        final Gson[] gson = {new Gson()};
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        final String[] json = {sharedPreferences[0].getString("indiaTitle", null)};
        title = gson[0].fromJson(json[0],type);
        json[0] = sharedPreferences[0].getString("indiaDescription",null);
        description = gson[0].fromJson(json[0],type);
        json[0] = sharedPreferences[0].getString("indiaSource",null);
        source = gson[0].fromJson(json[0],type);
        json[0] = sharedPreferences[0].getString("indiaURL",null);
        url = gson[0].fromJson(json[0],type);
        json[0] = sharedPreferences[0].getString("indiaImageURL",null);
        imageURL = gson[0].fromJson(json[0],type);
        json[0] = sharedPreferences[0].getString("worldTitle",null);
        title2 = gson[0].fromJson(json[0],type);
        json[0] = sharedPreferences[0].getString("worldDescription",null);
        description2 = gson[0].fromJson(json[0],type);
        json[0] = sharedPreferences[0].getString("worldSource",null);
        source2 = gson[0].fromJson(json[0],type);
        json[0] = sharedPreferences[0].getString("worldURL",null);
        url2 = gson[0].fromJson(json[0],type);
        json[0] = sharedPreferences[0].getString("worldImageURL",null);
        imageURL2 = gson[0].fromJson(json[0],type);

        if (title == null)
        {
            title = new ArrayList<>();
        }
        if (description == null)
        {
            description = new ArrayList<>();
        }
        if (source == null)
        {
            source = new ArrayList<>();
        }
        if (url == null)
        {
            url = new ArrayList<>();
        }
        if (imageURL == null)
        {
            imageURL = new ArrayList<>();
        }
        if (title2 == null)
        {
            title2 = new ArrayList<>();
        }
        if (description2 == null)
        {
            description2 = new ArrayList<>();
        }
        if (source2 == null)
        {
            source2 = new ArrayList<>();
        }
        if (url2 == null)
        {
            url2 = new ArrayList<>();
        }
        if (imageURL2 == null)
        {
            imageURL2 = new ArrayList<>();
        }
//        Log.i("TAG",title.toString());
//        Log.i("TAG",description.toString());
//        Log.i("TAG",source.toString());
//        Log.i("TAG",url.toString());
//        Log.i("TAG",imageURL.toString());


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView2 = view.findViewById(R.id.recyclerView2);

        recyclerView.setHasFixedSize(true);
        recyclerViewAdapter = new RecyclerViewAdapter(title, description, source, url, imageURL, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView2.setHasFixedSize(true);
        recyclerViewAdapter2 = new RecyclerViewAdapter(title2, description2, source2, url2, imageURL2, getActivity());
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.setAdapter(recyclerViewAdapter2);

        TabHost.TabSpec spec = tabHost.newTabSpec("TAB ONE");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Indian News");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("TAB TWO");
        spec.setContent(R.id.tab2);
        spec.setIndicator("World News");
        tabHost.addTab(spec);

        int tab = tabHost.getCurrentTab();
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            // When tab is not selected
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#EEEEF0"));
            TextView tv = tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#616161"));
            tv.setTextSize(14);
        }
        // When tab is selected
        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFFFF"));
        TextView tv = tabHost.getTabWidget().getChildAt(tab).findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#0A141E"));
        tv.setTextSize(14);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                int tab = tabHost.getCurrentTab();
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    // When tab is not selected
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#EEEEF0"));
                    TextView tv = tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    tv.setTextColor(Color.parseColor("#616161"));
                    tv.setTextSize(14);
                }
                // When tab is selected
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFFFF"));
                TextView tv = tabHost.getTabWidget().getChildAt(tab).findViewById(android.R.id.title);
                tv.setTextColor(Color.parseColor("#0A141E"));
                tv.setTextSize(14);
            }
        });

        return view;
    }
}
