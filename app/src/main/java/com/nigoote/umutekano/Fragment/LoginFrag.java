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

import com.android.volley.AuthFailureError;
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

public class LoginFrag extends Fragment {

    private static final String LgLink ="http://192.168.120.1/umutekano/android/cit_login.php";
    TextView txtHelp;
    EditText edtUser,edtPass;
    Button BtnLog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.login_frag,container,false);

        txtHelp = (TextView) view.findViewById(R.id.textView3);
        edtUser = (EditText) view.findViewById(R.id.edtUsername);
        edtPass =(EditText) view.findViewById(R.id.edtPassword);
        BtnLog = (Button) view.findViewById(R.id.btnlog);

        BtnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernames = edtUser.getText().toString().trim();
                String passwords = edtPass.getText().toString().trim();

               StringRequest stringRequest = new StringRequest(Request.Method.POST, LgLink, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       try {
                           JSONObject object = new JSONObject(response);
//                           checking if an object is success
                           if (!object.getBoolean("success")){

                           }else {
                               Toast.makeText(getContext(), "UserLogin UnSuccessful", Toast.LENGTH_SHORT).show();
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               }){
//overriding getParams

                   @Override
                   protected Map<String, String> getParams() {
                       Map<String,String>params = new HashMap<String,String>();
                       params.put("username",usernames);
                       params.put("password",passwords);
                       return params;
                   }
               };
// fRequestQueue
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(stringRequest);
            }
        });

        return view;
    }
}
