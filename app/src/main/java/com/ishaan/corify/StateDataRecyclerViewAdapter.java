package com.ishaan.corify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StateDataRecyclerViewAdapter extends RecyclerView.Adapter<StateDataRecyclerViewAdapter.ViewHolder>{

    private ArrayList<StateData> stateDataArrayList = new ArrayList<>();
    private Context context;

    public StateDataRecyclerViewAdapter(ArrayList<StateData> stateDataArrayList, Context context) {
        this.stateDataArrayList = stateDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public StateDataRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.state_individual_stats,viewGroup,false);
        StateDataRecyclerViewAdapter.ViewHolder viewHolder = new StateDataRecyclerViewAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final StateDataRecyclerViewAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.stateName.setText(stateDataArrayList.get(i).getState());
        viewHolder.confirmedCases.setText(String.format("%s",stateDataArrayList.get(i).getConfirmed()));
        viewHolder.activeCases.setText(String.format("%s",stateDataArrayList.get(i).getActive()));
        viewHolder.recoveredCases.setText(String.format(" %s",stateDataArrayList.get(i).getRecovered()));
        viewHolder.deathCases.setText(String.format("%s",stateDataArrayList.get(i).getDeaths()));
        viewHolder.newCases.setText(String.format("+ %s",stateDataArrayList.get(i).getDeltaconfirmed()));
        viewHolder.newRecoveredCases.setText(String.format("+ %s",stateDataArrayList.get(i).getDeltarecovered()));
        viewHolder.newDeathsCases.setText(String.format("+ %s",stateDataArrayList.get(i).getDeltadeaths()));
//        viewHolder.update.setText(String.format("Last updated at %s ",stateDataArrayList.get(i).getLastupdatedtime()));

//        if(stateDataArrayList.get(i).getDeltaconfirmed().equals("0")){
//            viewHolder.newCases.setVisibility(View.GONE);
//        }
//
//        if(stateDataArrayList.get(i).getDeltarecovered().equals("0")){
//            viewHolder.newRecoveredCases.setVisibility(View.GONE);
//        }
//
//        if(stateDataArrayList.get(i).getDeltadeaths().equals("0")){
//            viewHolder.newDeathsCases.setVisibility(View.GONE);
//        }

//        if(stateDataArrayList.get(i).getExpanded()){
//            viewHolder.expandedMenu.setVisibility(View.VISIBLE);
//        }else{
//            viewHolder.expandedMenu.setVisibility(View.GONE);
//        }

//        viewHolder.card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(stateDataArrayList.get(i).getExpanded()) {
//                    stateDataArrayList.get(i).setExpanded(false);
//                }else {
//                    viewHolder.expandedMenu.setVisibility(View.VISIBLE);
//                    for(int index = 0 ; index < stateDataArrayList.size() ; index++){
//                        if(index == i){
//                            stateDataArrayList.get(i).setExpanded(true);
//                        }else{
//                            stateDataArrayList.get(index).setExpanded(false);
//                        }
//                    }
//                }
//                notifyDataSetChanged();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return stateDataArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
//        private LinearLayout expandedMenu;
        private TextView stateName, confirmedCases, activeCases, recoveredCases, deathCases, newCases, newRecoveredCases, newDeathsCases;
        private ConstraintLayout card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.card);
//            expandedMenu = itemView.findViewById(R.id.expandedMenu);
            stateName = itemView.findViewById(R.id.stateName);
            confirmedCases = itemView.findViewById(R.id.confirmedCases);
            activeCases = itemView.findViewById(R.id.activeCases);
            recoveredCases = itemView.findViewById(R.id.recoveredCases);
            deathCases = itemView.findViewById(R.id.deathCases);
            newCases = itemView.findViewById(R.id.newCases);
            newRecoveredCases = itemView.findViewById(R.id.newRecoveredCases);
            newDeathsCases = itemView.findViewById(R.id.newDeathsCases);
//            update = itemView.findViewById(R.id.update);
        }
    }
}
