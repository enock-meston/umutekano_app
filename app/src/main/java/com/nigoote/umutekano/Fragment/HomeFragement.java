package com.nigoote.umutekano.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nigoote.umutekano.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeFragement extends Fragment {

    TextView NAMES;
    private String _names;

public HomeFragement(){
//    empty homeFragement constructor
}

public static HomeFragement NewInstance(String names){
    HomeFragement homeFragement = new HomeFragement();
    Bundle bundle =new Bundle();
    bundle.putString("names",names);
    homeFragement.setArguments(bundle);
    return homeFragement;
}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            if (getArguments() != null){
                _names = getArguments().getString("names");
            }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.home_frament,container,false);

        NAMES = (TextView) view.findViewById(R.id.namestxt);
        NAMES.setText(_names);
        return view;
    }
}
