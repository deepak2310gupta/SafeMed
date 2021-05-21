package com.example.safemed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterNumberCheck extends RecyclerView.Adapter<AdapterNumberCheck.HolderNumber> implements Filterable {

    Context context;
    ArrayList<ModelTweets> modelTweetsArrayList, filterList;
    private FilterNumber filterNumber;

    public AdapterNumberCheck(Context context, ArrayList<ModelTweets> modelTweetsArrayList) {
        this.context = context;
        this.modelTweetsArrayList = modelTweetsArrayList;
        this.filterList = modelTweetsArrayList;
    }

    @NonNull
    @Override
    public HolderNumber onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tweetsrow, parent, false);
        return new HolderNumber(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNumber holder, int position) {

        ModelTweets modelTweets = modelTweetsArrayList.get(position);

        String one = modelTweets.getPhone();
        String two = modelTweets.getComment();
        String three = modelTweets.getDate_added();
        String four = modelTweets.getEntity_name();
        // String five=modelTweets.getName();
        String six = modelTweets.getUpi();

        String getDateNew = three;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = "";
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy K:mm a");
        try {
            Date date = dateFormat.parse(getDateNew);
            formattedDate = dateFormat2.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (four.equals("")) {
            holder.entityname.setText("Name: Not Available");
        } else {
            holder.entityname.setText("Name: " + four);
        }

        // holder.locname.setText("Location Name: "+five);
        holder.phone.setText("Mobile No : " + one);
        holder.comment.setText("Comment: " + two);
        holder.dateadded.setText("Date Reported: " + formattedDate);
        holder.upikey.setText("UPI Id: " + six);


    }

    @Override
    public int getItemCount() {
        return modelTweetsArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filterNumber == null)
            filterNumber = new FilterNumber(this, filterList);

        return filterNumber;
    }

    public class HolderNumber extends RecyclerView.ViewHolder {
        TextView phone, entityname, comment, dateadded, upikey;

        public HolderNumber(@NonNull View itemView) {
            super(itemView);
            phone = itemView.findViewById(R.id.title);
            entityname = itemView.findViewById(R.id.content);
            comment = itemView.findViewById(R.id.username);
            dateadded = itemView.findViewById(R.id.desc);
            //locname=itemView.findViewById(R.id.locname);
            upikey = itemView.findViewById(R.id.upikey);
        }
    }
}
