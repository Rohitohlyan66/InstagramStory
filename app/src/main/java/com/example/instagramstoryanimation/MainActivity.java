package com.example.instagramstoryanimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    ViewPager2 pager2;
    CustomAdapter adapter;
    int a[] = new int[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pager2 = findViewById(R.id.view_pager2);
        a[0] = R.drawable.ic_launcher_foreground;
        a[1] = R.drawable.ic_launcher_foreground;
        a[2] = R.drawable.ic_launcher_foreground;
        a[3] = R.drawable.ic_launcher_foreground;
        a[4] = R.drawable.ic_launcher_foreground;

        adapter = new CustomAdapter(a);
        pager2.setAdapter(adapter);

        pager2.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

                //Helps us in animation view
                page.setCameraDistance(20000);

                    //Page is too left to be seen [-infinity,-1]
                if (position < -1) {
                    page.setAlpha(0);

                    //Page is in between [-1,0]
                } else if (position <= 0) {
                    page.setAlpha(1);
                    page.setPivotX(page.getWidth());
                    page.setRotationY(-90 * Math.abs(position));

                    //Page is between (0,1]
                } else if (position <= 1) {
                    page.setAlpha(1);
                    page.setPivotX(0);
                    page.setRotationY(90 * Math.abs(position));

                    //Page is between (1,infinity]
                } else {
                    page.setAlpha(0);
                }
            }
        });
    }
}