package com.example.safemed;

import android.widget.Filter;

import java.util.ArrayList;

public class FilterNumber extends Filter {


    private AdapterNumberCheck adapterNumberCheck;
    private ArrayList<ModelTweets> filteraddList;


    public FilterNumber(AdapterNumberCheck adapterNumberCheck, ArrayList<ModelTweets> modelTweets) {
        this.adapterNumberCheck = adapterNumberCheck;
        this.filteraddList = modelTweets;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();
            ArrayList<ModelTweets> filteredModels = new ArrayList<>();

            for (int i = 0; i < filteraddList.size(); i++) {

                if (filteraddList.get(i).getPhone().toUpperCase().contains(constraint) || filteraddList.get(i).getUpi().toUpperCase().contains(constraint)) {
                    filteredModels.add(filteraddList.get(i));
                }
            }

            filterResults.count = filteredModels.size();
            filterResults.values = filteredModels;

        } else {
            filterResults.count = filteraddList.size();
            filterResults.values = filteraddList;
        }
        return filterResults;
    }


    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapterNumberCheck.modelTweetsArrayList = (ArrayList<ModelTweets>) results.values;
        adapterNumberCheck.notifyDataSetChanged();
    }
}
