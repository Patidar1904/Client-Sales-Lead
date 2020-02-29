package com.ext.teamformation.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ext.teamformation.R;
import com.google.android.material.navigation.NavigationView;

public class NavigationActivity extends AppCompatActivity {

    public FrameLayout frame_main;
    public DrawerLayout drawer_layout;
    public NavigationView nav_view;
    public LinearLayout lnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        frame_main = findViewById(R.id.frame_main);
        drawer_layout = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);
        lnLogout = findViewById(R.id.linear_logout);

        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.75);
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) nav_view.getLayoutParams();
        params.width = width;
        nav_view.setLayoutParams(params);

        lnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(NavigationActivity.this,StartUpActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });
    }
}
