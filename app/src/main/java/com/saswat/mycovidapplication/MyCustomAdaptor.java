package com.saswat.mycovidapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdaptor extends ArrayAdapter<CountryModel> {
    private Context context;
    private static List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListfiltered;

    public MyCustomAdaptor(Context context, List<CountryModel> countryModelList) {
        super( context, R.layout.custom_list_item,countryModelList);

        this.context = context;
        this.countryModelList = countryModelList;
        this.countryModelListfiltered = countryModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);
        TextView tv_country_name = view.findViewById(R.id.tv_country);
        ImageView imgv_country_flag = view.findViewById(R.id.imageview_country);
        tv_country_name.setText(countryModelListfiltered.get(position).getCountry());
        Glide.with(context).load(countryModelListfiltered.get(position).getFlag()).into(imgv_country_flag);
        return view;
    }

    @Override
    public int getCount() {
        return countryModelListfiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListfiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence==null||charSequence.length()==0){
                    filterResults.count=countryModelList.size();
                    filterResults.values=countryModelList;
                }else{
                    List<CountryModel> countryModelsResult = new ArrayList<>();
                    String searchstr = charSequence.toString().toLowerCase();

                    for (CountryModel itemModel:countryModelList){
                        if(itemModel.getCountry().toLowerCase().contains(searchstr)){
                            countryModelsResult.add(itemModel);
                        }
                        filterResults.count = countryModelsResult.size();
                        filterResults.values = countryModelsResult;
                    }
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                countryModelListfiltered = (List<CountryModel>)filterResults.values;
                CountryFragment.countryModelList= (List<CountryModel>)filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
