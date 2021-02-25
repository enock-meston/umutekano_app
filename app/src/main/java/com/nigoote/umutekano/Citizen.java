package com.nigoote.umutekano;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.nigoote.umutekano.Fragment.HomeFragement;
import com.nigoote.umutekano.Fragment.PayFragement;
import com.nigoote.umutekano.Fragment.StartFrag;

import java.io.File;

public class Citizen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    TextView names,phone,id1;
    View hview;
    private String stringOfNames,stringOfId,stringOfPhone;

//    TextView NAMES;
//    String _names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
//        access header
        hview = navigationView.getHeaderView(0);
        names = (TextView) hview.findViewById(R.id.txtNames);

        Bundle bundle = getIntent().getExtras();
       if (bundle !=null){
           stringOfNames = bundle.getString("names");
           stringOfPhone = bundle.getString("pho1");
           stringOfId = bundle.getString("id1");
       }else{
           stringOfNames = "bunble is null";
           stringOfPhone = "bunble is null number phone";
           stringOfId = "bundle is null id";
       }



        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        rotation
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new StartFrag()).commit();
            navigationView.setCheckedItem(R.id.nav_start);
        }

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_start:
//                here has changes 01
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StartFrag()).commit();
                break;
            case R.id.nav_profile:
//                here has changes 01
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        HomeFragement.NewInstance(stringOfNames,stringOfPhone,stringOfId)).commit();
                break;


            case R.id.nav_share:
                ApplicationInfo api = getApplicationContext().getApplicationInfo();
                String apkpath = api.sourceDir;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("cards.inkuge.com.ulkstudent");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkpath)));
                startActivity(Intent.createChooser(intent,"ShareVia"));
//                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}