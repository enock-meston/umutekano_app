package com.nigoote.umutekano;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailedProductsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private TextView mTitle, mRating, mPrice;
    Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_products);

        mToolbar = findViewById(R.id.toolbar);
        mPrice = findViewById(R.id.price);
        mRating = findViewById(R.id.rating);
        mTitle = findViewById(R.id.name);
        payButton = (Button) findViewById(R.id.ishyura);

        // Setting up action bar
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        // Catching incoming intent
        Intent intent = getIntent();
        String citizenId = intent.getStringExtra("citizenId");
        String dateofpayment = intent.getStringExtra("dateofpayment");
        String month = intent.getStringExtra("month");
        String fine = intent.getStringExtra("fine");
        String idCard = intent.getStringExtra("idcard");


        if (intent !=null){

            mActionBar.setTitle(idCard);
            mTitle.setText("Ukwezi "+month);
            mRating.setText("Umusanzu :"+fine+" frw");
            mPrice.setText(dateofpayment);
        }

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DetailedProductsActivity.this,PayWebView.class);
                startActivity(intent2);
            }
        });

    }
}
