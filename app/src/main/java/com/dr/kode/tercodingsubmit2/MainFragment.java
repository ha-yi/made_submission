package com.dr.kode.tercodingsubmit2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dr.kode.tercodingsubmit2.adapters.PagerCategoryAdapter;
import com.dr.kode.tercodingsubmit2.utils.IMDBApiCall;
import com.dr.kode.tercodingsubmit2.utils.IMDBCall;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private ViewPager pager;
    private ProgressBar progressBar;
    private IMDBCall caller;
    private TextView appSubTitle;

    static MainFragment createInstance(IMDBCall dataCaller) {
        MainFragment instance = new MainFragment();
        instance.caller = dataCaller;
        instance.setRetainInstance(true);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager = view.findViewById(R.id.pager);
        TabLayout tabStrip = view.findViewById(R.id.tabLayout);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        appSubTitle = view.findViewById(R.id.appSubTitle);

        if (caller instanceof IMDBApiCall) {
            appSubTitle.setText(R.string.by_all);
        } else {
            appSubTitle.setText(R.string.by_favorites);
        }

        View cfg = view.findViewById(R.id.imgConfig);
        cfg.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS)));

        processCategory();
        tabStrip.setupWithViewPager(pager);
    }

    void processCategory() {
        progressBar.setVisibility(View.GONE);
        List<String> data = new ArrayList<>();
        data.add("tv");
        data.add("movie");

        PagerCategoryAdapter adapter = new PagerCategoryAdapter(getChildFragmentManager(), data, this.caller);
        pager.setAdapter(adapter);
    }
}
