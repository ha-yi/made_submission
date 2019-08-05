package com.dr.kode.tercodingsubmit2.fragments;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dr.kode.movielib.themoviedb.Tontonan;
import com.dr.kode.movielib.widget.OnFavorite;
import com.dr.kode.tercodingsubmit2.R;
import com.dr.kode.tercodingsubmit2.adapters.AnimeAdapter;
import com.dr.kode.movielib.themoviedb.TheMovieResponse;
import com.dr.kode.tercodingsubmit2.utils.IMDBCall;
import com.dr.kode.tercodingsubmit2.utils.IMDBDBCall;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class AnimeListFragment extends Fragment implements OnFavorite {
    private String category;
    private RecyclerView rvContent;
    private ProgressBar progressBar;

    private AnimeAdapter adapter;
    private int pvItem;
    private int visCount;
    private int total;
    private int totalData;
    private boolean loading = true;
    private int lastPage = 1;
    private IMDBCall caller;
    private SwipeRefreshLayout srlLayout;

    public static AnimeListFragment createFragment(String category, IMDBCall dataCall) {
        AnimeListFragment fr = new AnimeListFragment();
        fr.caller = dataCall;
        fr.category = category;
        fr.setRetainInstance(true);

        return fr;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_anime, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvContent = view.findViewById(R.id.rvContent);
        progressBar = view.findViewById(R.id.progressBar);
        srlLayout = view.findViewById(R.id.srlLayout);

        adapter = new AnimeAdapter(new ArrayList<>(), category, caller instanceof IMDBDBCall, this);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rvContent.setLayoutManager(lm);
        rvContent.setAdapter(adapter);

        getNewData();

        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visCount = lm.getChildCount();
                    total = lm.getItemCount();
                    pvItem = lm.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visCount + pvItem) >= total && lm.getItemCount() < totalData) {
                            loading = false;
                            getNewData();
                        }
                    }
                }
            }
        });

        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.addUpdateListener(animation -> {
            progressBar.setProgress(Integer.valueOf(animator.getAnimatedValue().toString()));
        });
        animator.setDuration(1000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.start();

        srlLayout.setOnRefreshListener(this::refresh);
    }

    private void toggleProgress(Boolean showProgress) {
        progressBar.setVisibility(showProgress ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (caller instanceof IMDBDBCall) {
            getNewData();
        }
    }

    private void refresh() {
        lastPage = 1;
        adapter.clear();
        getNewData();
    }

    private void getNewData() {
        srlLayout.setRefreshing(true);
        toggleProgress(true);
        caller.getListOf(this.getContext(), category, lastPage++, this::onData);
    }

    private void onData(TheMovieResponse data, String message) {
        srlLayout.setRefreshing(false);
        if (data == null) {
            Snackbar.make(rvContent, message, Snackbar.LENGTH_LONG).show();
            return;
        }
        List<Tontonan> tontonans = data.getResults();
        for (Tontonan d : tontonans) {
            d.setType(category);
        }

        adapter.addItems(tontonans);
        toggleProgress(false);
        totalData = data.getTotalResults();
        loading = true;
    }

    @Override
    public void onChanges() {
        if (caller instanceof IMDBDBCall) {
            refresh();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void add(Tontonan item, String type, String msg) {
        IMDBDBCall.add(item, type);
        Snackbar.make(
                srlLayout,
                getResources().getString(R.string.added_to_fav, msg),
                Snackbar.LENGTH_LONG
        ).show();
    }

    @Override
    public void remove(int id, String msg) {
        IMDBDBCall.remove(id);
        Snackbar.make(
                srlLayout,
                getResources().getString(R.string.remove_from_fav, msg),
                Snackbar.LENGTH_LONG
        ).show();
    }

    @Override
    public boolean isFavorite(int id) {
        return IMDBDBCall.isFavorite(id);
    }
}
