package com.nigoote.umutekano.Fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nigoote.umutekano.Citizen;
import com.nigoote.umutekano.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFrag extends Fragment {

    private static final String LgLink ="http://192.168.56.1/umutekano/android/login.php";
    TextView txtHelp;
    EditText edt_id_number;
    Button BtnLog;
    String ID_NO;
    private String stringOfNames;
//    private android.app.AlertDialog.Builder builder;
AlertDialog.Builder builder;
    private ProgressDialog myProgress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.login_frag,container,false);

        txtHelp = (TextView) view.findViewById(R.id.textView3);

        edt_id_number =(EditText) view.findViewById(R.id.edtidnumber);
        BtnLog = (Button) view.findViewById(R.id.btnlog);
        builder = new AlertDialog.Builder(getActivity());
        //        progressBar
        myProgress = new ProgressDialog(getActivity());
        myProgress.setTitle("Processing...");
        myProgress.setMessage("Please wait...");
        myProgress.setCancelable(false);
        myProgress.setIndeterminate(true);

//        /// button

        BtnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myProgress.show();
                ID_NO = edt_id_number.getText().toString();
//                check if id is valid
                if (ID_NO.equals("")){
                    builder.setTitle("Something went wrong");
                    displayAlert("INJIZA  INDANGAMUNTU NYAYO");
                }else{
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, LgLink, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            myProgress.dismiss();
                            try {
                                JSONArray jsonArray=new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");

                                if (code.equals("login_failed")){
                                    myProgress.dismiss();
                                    builder.setTitle("Login Error...");
                                    displayAlert(jsonObject.getString("message"));
                                    myProgress.dismiss();
                                }else{
                                    //Intent intent = new Intent(getActivity(), Citizen.class);
                                    Intent intent = new Intent(getActivity(), Citizen.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("names",jsonObject.getString("names"));

//                                    HomeFragement fragment = new HomeFragement();
//                                    bundle.putString("names",jsonObject.getString("names"));
//                                    fragment.setArguments(bundle);

//                                    Log.d("enoc",fragment.getArguments().getString("names"));
                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                    clear();
                                    getActivity().finish(); // finishing activity


                                }
                            } catch (JSONException e) {
                                myProgress.dismiss();
                                builder.setTitle("IKOSA");
                                displayAlert("INDANGAMUNTU NTAGO IZWI....");
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                                myProgress.dismiss();
                                e.printStackTrace();
                            }

                        }
                        // ends of onResponse

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                            myProgress.dismiss();
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("number",ID_NO);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);

                }//end of if and else condition


            } // ends of on click

        });// ends of button

        return view;
    }

//    alert dialog error messages
    public void displayAlert(String message){
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                edt_id_number.setText("");
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
//    end of error messages


//    clear method
public void clear(){
    edt_id_number.setText("");
}
}
