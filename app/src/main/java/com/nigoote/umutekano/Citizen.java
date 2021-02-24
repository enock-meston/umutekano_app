package com.nigoote.umutekano;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Citizen extends AppCompatActivity {

    TextView NAMES;
    String _names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen);

//        NAMES = (TextView) findViewById(R.id.namestxt);
//
//        Bundle bundle = this.getIntent().getExtras();
//
//        if(bundle != null){
//             _names = bundle.getString("names");
////            _names = getArguments().getString("names");
//        }
//
//        NAMES.setText(_names);



    }
}