package me.cafetorres.cp_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Carlos on 10/12/2016.
 */

public class Details_cp extends AppCompatActivity {
    @Bind(R.id.placename)
    TextView placename;
    @Bind(R.id.postalcode)
    TextView postalcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();





        placename.setText("Place Name: " + intent.getStringExtra("placename"));
        postalcode.setText("Postal Code: " + intent.getStringExtra("postalcode"));

    }
}
