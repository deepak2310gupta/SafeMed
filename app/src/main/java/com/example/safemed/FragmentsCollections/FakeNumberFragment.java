package com.example.safemed.FragmentsCollections;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.safemed.AdapterNumberCheck;
import com.example.safemed.ModelMemberClass;
import com.example.safemed.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class FakeNumberFragment extends Fragment {


    public FakeNumberFragment() {

    }

    AdapterNumberCheck adapterNumberCheck;
    RecyclerView listRecyclerFake;
    ArrayList<ModelMemberClass> modelMemberClassArrayList;
    EditText searchNumber;
    TextView txtSafeMed;
    ImageButton buttonGetResults;
    ProgressBar fetchingdata;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fake_number, container, false);
        listRecyclerFake = view.findViewById(R.id.listRecyclerFake);
        txtSafeMed = view.findViewById(R.id.txtSafeMed);
        buttonGetResults=view.findViewById(R.id.buttonGetResults);
         fetchingdata = view.findViewById(R.id.fetchingdata);
        searchNumber = view.findViewById(R.id.searchNumber);
        modelMemberClassArrayList = new ArrayList<>();
        modelMemberClassArrayList.clear();


        loadAllFakedDetails();



        searchNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {

                    adapterNumberCheck.getFilter().filter(s);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });


     return view;

    }

    private void loadAllFakedDetails() {

        fetchingdata.setVisibility(View.VISIBLE);
        fetchingdata.setIndeterminate(true);
        String url = "https://api.cov.social/v1/info/findFraud";
        StringRequest stringRequestNews = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < 25; i++) {

                        try {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String one = jsonObject1.getString("phone");
                            String two = jsonObject1.getString("_id");
                            String three = jsonObject1.getString("upi");
                            String four = jsonObject1.getString("comment");
                            String five = jsonObject1.getString("entity_name");
                            String six = jsonObject1.getString("date_added");

                            ModelMemberClass modelMemberClass = new ModelMemberClass(
                                    "" + one,
                                    "" + two,
                                    "" + three,
                                    "" + four,
                                    "" + five,
                                    "" + six);

                            modelMemberClassArrayList.add(modelMemberClass);
                        } catch (Exception e) {
                            Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    adapterNumberCheck = new AdapterNumberCheck(getContext(), modelMemberClassArrayList);
                    listRecyclerFake.setAdapter(adapterNumberCheck);
                    adapterNumberCheck.notifyDataSetChanged();
                    fetchingdata.setVisibility(View.GONE);
                    listRecyclerFake.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequestNews);

    }


}