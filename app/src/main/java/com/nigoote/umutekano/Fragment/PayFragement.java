package com.nigoote.umutekano.Fragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nigoote.umutekano.CitizenPayment;
import com.nigoote.umutekano.R;
import com.nigoote.umutekano.RecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PayFragement extends Fragment {

    public PayFragement() {
//    empty homeFragement constructor
    }

    private String userEmail;
    private TextView textView;
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter mAdapter;
    private List<CitizenPayment> citizenPayments;
    private ProgressBar progressBar;
    private static final String BASE_URL = "http://192.168.56.1/umutekano/android/getPayments.php";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pay_frafment, container, false);

        progressBar = view.findViewById(R.id.progressbar);

        recyclerView = view.findViewById(R.id.paylistView);
        manager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(manager);
        citizenPayments = new ArrayList<>();

        getPayments();
        return view;
    }

    private void getPayments() {
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {

                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        String citizenId = object.getString("citizenId");
                        String date = object.getString("dateofpayment");
                        String month = object.getString("month");
                        String fine = object.getString("fine");  // N/A is like Amount in Database
                        String idCard = object.getString("idcard");


                        CitizenPayment payment = new CitizenPayment(citizenId, date, month, fine, idCard);
                        citizenPayments.add(payment);
                    }

                } catch (Exception e) {

                }

                mAdapter = new RecyclerAdapter(getActivity(), citizenPayments);
                recyclerView.setAdapter(mAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(getActivity()).add(stringRequest);

    }


}
