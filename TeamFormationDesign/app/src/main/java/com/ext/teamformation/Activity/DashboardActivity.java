package com.ext.teamformation.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.core.view.GravityCompat;

import com.ext.teamformation.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DashboardActivity extends NavigationActivity {

    @BindView(R.id.imgMenu)
    ImageView imgMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_dashboard, frame_main);
        ButterKnife.bind(this);

        this.setListeners();
    }

    private void setListeners() {
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}
