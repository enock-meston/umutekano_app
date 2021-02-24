package com.nigoote.umutekano.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nigoote.umutekano.R;

public class HomeFragement extends Fragment {

//    this Fragement is Profile Activity is differ to HomeFragement




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

            }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.home_frament,container,false);


//        NAMES.setText(_names);
        return view;
    }
}
