package com.nigoote.umutekano.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nigoote.umutekano.R;

public class PayFragement extends Fragment {


public PayFragement(){
//    empty homeFragement constructor
}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.pay_frafment,container,false);

        return view;
    }
}
