package com.dr.kode.tercodingsubmit2;

import android.os.Bundle;

import com.dr.kode.tercodingsubmit2.utils.IMDBApiCall;
import com.dr.kode.tercodingsubmit2.utils.IMDBCall;
import com.dr.kode.tercodingsubmit2.utils.IMDBDBCall;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private ViewPager pager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    pager.setCurrentItem(0);
                    return true;
                case R.id.navigation_fav:
                    pager.setCurrentItem(1);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        pager = findViewById(R.id.pager);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        pager.setAdapter(new HomeAdapter(getSupportFragmentManager()));
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    navView.setSelectedItemId(R.id.navigation_home);
                } else {
                    navView.setSelectedItemId(R.id.navigation_fav);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class HomeAdapter extends FragmentPagerAdapter {
        HomeAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            IMDBCall caller;
            if (position == 0) {
                caller = new IMDBApiCall();
            } else {
                caller = new IMDBDBCall();
            }
            return MainFragment.createInstance(caller);
        }
    }
}
