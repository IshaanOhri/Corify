package com.ishaan.corify;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.richpath.RichPath;
import com.richpath.RichPathView;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class IndiaFragment extends Fragment {

    private RichPathView imageView;

    private ArrayList<StateData> stateData = new ArrayList<>();
    private ArrayList<String> stateName = new ArrayList<>();
    private StateDataRecyclerViewAdapter stateDataRecyclerViewAdapter;
    private CardView allStats;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_india, container, false);

        imageView = view.findViewById(R.id.indiaMap);
        allStats = view.findViewById(R.id.allStats);

        final SharedPreferences[] sharedPreferences = {getActivity().getSharedPreferences("com.ishaanohri.corify", Context.MODE_PRIVATE)};
        final Gson[] gson = {new Gson()};
        Type type = new TypeToken<ArrayList<StateData>>() {}.getType();
        final String[] json = {sharedPreferences[0].getString("stateData", null)};
        stateData = gson[0].fromJson(json[0],type);

        createBottomSheetDialog();

        float total = Float.parseFloat(stateData.get(0).getActive());
        for(int i = 0 ; i < stateData.size() ; i ++){
            stateName.add(stateData.get(i).getState());
        }

        RichPath andaman = imageView.findRichPathByName("Andaman and Nicobar Islands");
        RichPath andhra = imageView.findRichPathByName("Andhra Pradesh");
        RichPath arunachal = imageView.findRichPathByName("Arunachal Pradesh");
        RichPath assam = imageView.findRichPathByName("Assam");
        RichPath bihar = imageView.findRichPathByName("Bihar");
        RichPath chandigarh = imageView.findRichPathByName("Chandigarh");
        RichPath chattisgarh = imageView.findRichPathByName("Chhattisgarh");
        RichPath daman = imageView.findRichPathByName("Daman and Diu");
        RichPath delhi = imageView.findRichPathByName("Delhi");
        RichPath dadra = imageView.findRichPathByName("Dadra and Nagar Haveli");
        RichPath goa = imageView.findRichPathByName("Goa");
        RichPath gujarat = imageView.findRichPathByName("Gujarat");
        RichPath himachal = imageView.findRichPathByName("Himachal Pradesh");
        RichPath haryana = imageView.findRichPathByName("Haryana");
        RichPath jharkhand = imageView.findRichPathByName("Jharkhand");
        RichPath jammu = imageView.findRichPathByName("Jammu and Kashmir");
        RichPath karnataka = imageView.findRichPathByName("Karnataka");
        RichPath ladakh = imageView.findRichPathByName("Ladakh");
        RichPath lakshadweep = imageView.findRichPathByName("Lakshadweep");
        RichPath kerala = imageView.findRichPathByName("Kerala");
        RichPath maharashtra = imageView.findRichPathByName("Maharashtra");
        RichPath meghalaya = imageView.findRichPathByName("Meghalaya");
        RichPath manipur = imageView.findRichPathByName("Manipur");
        RichPath madhya = imageView.findRichPathByName("Madhya Pradesh");
        RichPath mizoram = imageView.findRichPathByName("Mizoram");
        RichPath nagaland = imageView.findRichPathByName("Nagaland");
        RichPath odisha = imageView.findRichPathByName("Odisha");
        RichPath punjab = imageView.findRichPathByName("Punjab");
        RichPath pondi = imageView.findRichPathByName("Puducherry");
        RichPath rajasthan = imageView.findRichPathByName("Rajasthan");
        RichPath sikkim = imageView.findRichPathByName("Sikkim");
        RichPath telangana = imageView.findRichPathByName("Telangana");
        RichPath tamil = imageView.findRichPathByName("Tamil Nadu");
        RichPath tripura = imageView.findRichPathByName("Tripura");
        RichPath uttar = imageView.findRichPathByName("Uttar Pradesh");
        RichPath uttrakhand = imageView.findRichPathByName("Uttarakhand");
        RichPath west = imageView.findRichPathByName("West Bengal");

        andaman.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Andaman and Nicobar Islands")).getActive())/total)));
        andhra.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Andhra Pradesh")).getActive())/total)));
        arunachal.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Arunachal Pradesh")).getActive())/total)));
        assam.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Assam")).getActive())/total)));
        bihar.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Bihar")).getActive())/total)));
        chandigarh.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Chandigarh")).getActive())/total)));
        chattisgarh.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Chhattisgarh")).getActive())/total)));
        daman.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Dadra and Nagar Haveli and Daman and Diu")).getActive())/total)));
        delhi.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Delhi")).getActive())/total)));
        dadra.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Dadra and Nagar Haveli and Daman and Diu")).getActive())/total)));
        goa.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Goa")).getActive())/total)));
        gujarat.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Gujarat")).getActive())/total)));
        himachal.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Himachal Pradesh")).getActive())/total)));
        haryana.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Haryana")).getActive())/total)));
        jharkhand.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Jharkhand")).getActive())/total)));
        jammu.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Jammu and Kashmir")).getActive())/total)));
        karnataka.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Karnataka")).getActive())/total)));
        kerala.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Kerala")).getActive())/total)));
        ladakh.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Ladakh")).getActive())/total)));
        lakshadweep.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Lakshadweep")).getActive())/total)));
        maharashtra.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Maharashtra")).getActive())/total)));
        meghalaya.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Meghalaya")).getActive())/total)));
        manipur.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Manipur")).getActive())/total)));
        mizoram.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Mizoram")).getActive())/total)));
        madhya.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Madhya Pradesh")).getActive())/total)));
        nagaland.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Nagaland")).getActive())/total)));
        odisha.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Odisha")).getActive())/total)));
        punjab.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Punjab")).getActive())/total)));
        pondi.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Puducherry")).getActive())/total)));
        rajasthan.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Rajasthan")).getActive())/total)));
        sikkim.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Sikkim")).getActive())/total)));
        telangana.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Telangana")).getActive())/total)));
        tamil.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Tamil Nadu")).getActive())/total)));
        tripura.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Tripura")).getActive())/total)));
        uttar.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Uttar Pradesh")).getActive())/total)));
        uttrakhand.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("Uttarakhand")).getActive())/total)));
        west.setFillColor(Color.parseColor(findColor(Float.parseFloat(stateData.get(stateName.indexOf("West Bengal")).getActive())/total)));

        andaman.setStrokeColor(Color.parseColor("#0A141E"));
        andhra.setStrokeColor(Color.parseColor("#0A141E"));
        arunachal.setStrokeColor(Color.parseColor("#0A141E"));
        assam.setStrokeColor(Color.parseColor("#0A141E"));
        bihar.setStrokeColor(Color.parseColor("#0A141E"));
        chandigarh.setStrokeColor(Color.parseColor("#0A141E"));
        chattisgarh.setStrokeColor(Color.parseColor("#0A141E"));
        daman.setStrokeColor(Color.parseColor("#0A141E"));
        delhi.setStrokeColor(Color.parseColor("#0A141E"));
        dadra.setStrokeColor(Color.parseColor("#0A141E"));
        goa.setStrokeColor(Color.parseColor("#0A141E"));
        gujarat.setStrokeColor(Color.parseColor("#0A141E"));
        himachal.setStrokeColor(Color.parseColor("#0A141E"));
        haryana.setStrokeColor(Color.parseColor("#0A141E"));
        jharkhand.setStrokeColor(Color.parseColor("#0A141E"));
        jammu.setStrokeColor(Color.parseColor("#0A141E"));
        karnataka.setStrokeColor(Color.parseColor("#0A141E"));
        kerala.setStrokeColor(Color.parseColor("#0A141E"));
        ladakh.setStrokeColor(Color.parseColor("#0A141E"));
        lakshadweep.setStrokeColor(Color.parseColor("#0A141E"));
        maharashtra.setStrokeColor(Color.parseColor("#0A141E"));
        meghalaya.setStrokeColor(Color.parseColor("#0A141E"));
        manipur.setStrokeColor(Color.parseColor("#0A141E"));
        mizoram.setStrokeColor(Color.parseColor("#0A141E"));
        madhya.setStrokeColor(Color.parseColor("#0A141E"));
        nagaland.setStrokeColor(Color.parseColor("#0A141E"));
        odisha.setStrokeColor(Color.parseColor("#0A141E"));
        punjab.setStrokeColor(Color.parseColor("#0A141E"));
        pondi.setStrokeColor(Color.parseColor("#0A141E"));
        rajasthan.setStrokeColor(Color.parseColor("#0A141E"));
        sikkim.setStrokeColor(Color.parseColor("#0A141E"));
        telangana.setStrokeColor(Color.parseColor("#0A141E"));
        tamil.setStrokeColor(Color.parseColor("#0A141E"));
        tripura.setStrokeColor(Color.parseColor("#0A141E"));
        uttar.setStrokeColor(Color.parseColor("#0A141E"));
        uttrakhand.setStrokeColor(Color.parseColor("#0A141E"));
        west.setStrokeColor(Color.parseColor("#0A141E"));

        imageView.setOnPathClickListener(new RichPath.OnPathClickListener() {
            @Override
            public void onClick(RichPath richPath) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.info_card);
                dialog.show();

                TextView state = dialog.findViewById(R.id.stateName);
                TextView totalCases = dialog.findViewById(R.id.totalCases);
                TextView activeCases = dialog.findViewById(R.id.activeCases);
                TextView deaths = dialog.findViewById(R.id.deaths);
                TextView recovered = dialog.findViewById(R.id.recovered);
                TextView newCases = dialog.findViewById(R.id.newCases);
                TextView newDeaths = dialog.findViewById(R.id.newDeaths);
                TextView newRecovered = dialog.findViewById(R.id.newRecovered);
                TextView today = dialog.findViewById(R.id.today);
                ProgressBar progress_horizontal = dialog.findViewById(R.id.progress_horizontal);
                CardView back = dialog.findViewById(R.id.back);


                state.setText(richPath.getName().toUpperCase());
                int index = stateName.indexOf(richPath.getName());
                totalCases.setText(String.format("%s total cases",stateData.get(index).getConfirmed()));
                activeCases.setText(String.format("%s active",stateData.get(index).getActive()));
                deaths.setText(String.format("%s deceased",stateData.get(index).getDeaths()));
                recovered.setText(String.format("%s recovered",stateData.get(index).getRecovered()));

                int deltaCases = Integer.parseInt(stateData.get(index).getDeltaconfirmed());
                int deltaRecovered = Integer.parseInt(stateData.get(index).getDeltarecovered());
                int deltaDeaths = Integer.parseInt(stateData.get(index).getDeltadeaths());

                newCases.setText(String.format("+%s cases",deltaCases));
                newDeaths.setText(String.format("+%s deceased",deltaDeaths));
                newRecovered.setText(String.format("+%s recovered",deltaRecovered));

                if(deltaCases == 0 && deltaDeaths == 0 && deltaRecovered == 0){
                    newCases.setVisibility(View.GONE);
                    newDeaths.setVisibility(View.GONE);
                    newRecovered.setVisibility(View.GONE);
                    today.setVisibility(View.GONE);
                    progress_horizontal.setVisibility(View.GONE);
                }
                if(deltaCases == 0){
                    newCases.setVisibility(View.GONE);
                }
                if(deltaDeaths == 0){
                    newDeaths.setVisibility(View.GONE);
                }
                if(deltaRecovered == 0){
                    newRecovered.setVisibility(View.GONE);
                }

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        allStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog.show();

//                final Dialog dialog = new Dialog(getActivity());
//                dialog.setContentView(R.layout.all_stats);
//                dialog.show();
//
//                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
//                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.70);
//
//                dialog.getWindow().setLayout(width, height);
//
//                RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView);
//
//                stateDataRecyclerViewAdapter = new StateDataRecyclerViewAdapter(stateData, getActivity());
//                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//                recyclerView.setAdapter(stateDataRecyclerViewAdapter);
            }
        });

        return view;
    }

    private void createBottomSheetDialog()
    {
        if(bottomSheetDialog == null)
        {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.all_stats,null);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

            stateDataRecyclerViewAdapter = new StateDataRecyclerViewAdapter(stateData, getActivity());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(stateDataRecyclerViewAdapter);

            bottomSheetDialog = new BottomSheetDialog(getContext());
            bottomSheetDialog.setContentView(view);

            BottomSheetBehavior behavior = BottomSheetBehavior.from((View) view.getParent());
            behavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        }
    }

    public String findColor(Float cases){
        if(cases >= 0 && cases < 0.0005){
            return "#A2B9D5";
        }else if(cases >= 0.0005 && cases < 0.001){
            return "#8BA8CB";
        }else if(cases >= 0.001 && cases < 0.005){
            return "#7396C1";
        }else if(cases >= 0.005 && cases < 0.01){
            return "#5C85B7";
        }else if(cases >= 0.01 && cases < 0.05){
            return "#4574AC";
        }else if(cases >= 0.05 && cases < 0.1){
            return "#2E62A2";
        }else if(cases >= 0.1 && cases < 0.2){
            return "#175198";
        }else if(cases >= 0.2 && cases < 0.3){
            return "#00408E";
        }else if(cases >= 0.3 && cases < 0.4){
            return "#003B82";
        }else if(cases >= 0.4 && cases < 0.6){
            return "#003575";
        }else if(cases >= 0.6 && cases < 0.8){
            return "#002F68";
        }else if(cases >= 0.8 && cases < 1.0){
            return "#001227";
        }else{
            return "#000C1A";
        }
    }
}
