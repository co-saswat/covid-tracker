package com.saswat.mycovidapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdaptorState extends RecyclerView.Adapter<MyCustomAdaptorState.ViewHolder> {

    LayoutInflater layoutInflater;
    private static List<StatesModel> statesModelList ;
    private List<StatesModel> statesModelListFilter;



    public MyCustomAdaptorState(Context ctx ,List<StatesModel> statesModelList){
        this.layoutInflater = LayoutInflater.from(ctx);
        this.statesModelList = statesModelList;
        this.statesModelListFilter = statesModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_list_item_states,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.stateName.setText(statesModelListFilter.get(position).getStateName());
        holder.stateCases.setText(statesModelList.get(position).getStateCases());

    }

    @Override
    public int getItemCount() {
        return statesModelListFilter.size();
    }

    public Filter getFilter(){
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null || charSequence.length()==0){
                    filterResults.count = statesModelList.size();
                    filterResults.values = statesModelList;
                }else {
                    List<StatesModel> statesModelsResult = new ArrayList<>();
                    String searchStr = charSequence.toString().toLowerCase();

                    for(StatesModel statesItemModel : statesModelList){
                        if (statesItemModel.getStateName().toLowerCase().contains(searchStr)){
                            statesModelsResult.add(statesItemModel);
                        }
                        filterResults.count = statesModelsResult.size();
                        filterResults.values = statesModelsResult;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                statesModelListFilter = (List<StatesModel>)filterResults.values;
                StatesFragment.statesModelList = (List<StatesModel>)filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stateName,stateCases;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stateName = itemView.findViewById(R.id.tv_india_state_name);
            stateCases = itemView.findViewById(R.id.no_cases_states_india);

        }
    }




}
