package com.nigoote.umutekano.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nigoote.umutekano.R;

public class HomeFragement extends Fragment {

//    this Fragement is Profile Activity is differ to HomeFragement
    TextView profileNames,profilePhone,profileIDNumber;
    private String _names,_phone,_id1;




public HomeFragement(){
//   Empty HomeFragement constructor

}

public static HomeFragement NewInstance(String names,String phone,String id){
//    come back1

    HomeFragement homeFragement = new HomeFragement();

    Bundle bundle =new Bundle();
    Log.d("eno22","nemase"+names);
    bundle.putString("names",names);
    bundle.putString("pho1",phone);
    bundle.putString("id1",id);

    homeFragement.setArguments(bundle);
    return homeFragement;
}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            if (getArguments() != null){
                Log.d("ee","data:"+getArguments().getString("names"));
                _names = getArguments().getString("names");
                _phone = getArguments().getString("pho1");
                _id1 = getArguments().getString("id1");

            }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.home_frament,container,false);

        profileNames = (TextView) view.findViewById(R.id.textViewAmazina);
        profilePhone = (TextView) view.findViewById(R.id.textViewPhone);
        profileIDNumber = (TextView) view.findViewById(R.id.textViewIDNumber);

        profileNames.setText(_names);
        profilePhone.setText(_phone);
        profileIDNumber.setText(_id1);
        return view;
    }
}
