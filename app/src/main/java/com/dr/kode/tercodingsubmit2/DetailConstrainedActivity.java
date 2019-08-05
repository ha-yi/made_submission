package com.dr.kode.tercodingsubmit2;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dr.kode.movielib.themoviedb.Tontonan;

import org.parceler.Parcels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Constraints;

public class DetailConstrainedActivity extends AppCompatActivity {
    // header content
    private ImageView detailCover;
    private TextView txtTitle;
    private TextView txtSubtitle;
    private TextView txtCounter;
    private View chevUp;

    // detail content
    private TextView txtTitleDetail;
    private TextView txtTitleEng;
    private TextView txtTitleOri;
    private TextView txtSeries;
    private TextView txtStatus;
    private TextView txtPremiere;
    private TextView txtPopularityRank;
    private TextView txtUserVoters;
    private TextView txtRating;
    private TextView txtAgerating;
    private TextView txtAgeratingGuide;
    private TextView txtSynopsis;

    private ImageView imgCoover;

    private Tontonan data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_detail_cnstrained);

        // populate size of screen to make header in full screen at run
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        View layHeader = findViewById(R.id.layHeader);
        layHeader.setLayoutParams(new Constraints.LayoutParams(Constraints.LayoutParams.MATCH_PARENT, height));

        data = Parcels.unwrap(getIntent().getParcelableExtra("data"));

        detailCover = findViewById(R.id.detailCover);
        txtTitle = findViewById(R.id.txtTitle);
        txtSubtitle = findViewById(R.id.txtSubtitle);
        txtCounter = findViewById(R.id.txtCounter);
        chevUp = findViewById(R.id.chevUp);

        setHeaderData();

        // details
        txtTitleDetail = findViewById(R.id.txtTitleDetail);
        txtTitleEng= findViewById(R.id.txtTitleEng);
        txtTitleOri= findViewById(R.id.txtTitleOri);
        txtSeries= findViewById(R.id.txtSeries);
        txtStatus= findViewById(R.id.txtStatus);
        txtPremiere= findViewById(R.id.txtPremiere);
        txtPopularityRank= findViewById(R.id.txtPopularityRank);
        txtUserVoters= findViewById(R.id.txtUserVoters);
        txtRating= findViewById(R.id.txtRating);
        txtAgerating= findViewById(R.id.txtAgerating);
        txtAgeratingGuide= findViewById(R.id.txtAgeratingGuide);
        txtSynopsis= findViewById(R.id.txtSynopsis);
        imgCoover= findViewById(R.id.imgCoover);

        setDetailContent();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    private void setDetailContent() {
        txtTitleDetail.setText(data.getCanonicalName());
        txtTitleOri.setText(data.getCanonicalOriginalName());
        txtSeries.setText(data.getType().toUpperCase());
        txtPremiere.setText(data.getReleaseDate() != null ? data.getReleaseDate() : data.getFirstAirDate());
        txtPopularityRank.setText(String.valueOf(data.getPopularity()));
        if (data.getPopularity() < 10) {
            txtPopularityRank.setTextColor(Color.parseColor("#4489FE"));
        } else if (data.getPopularity() < 50) {
            txtPopularityRank.setTextColor(Color.parseColor("#44FF45"));
        } else {
            txtPopularityRank.setTextColor(Color.parseColor("#FF4040"));
        }
        txtUserVoters.setText(String.valueOf(data.getVoteCount()));
        txtRating.setText(String.valueOf(data.getVoteAverage()));

        if (data.getVoteAverage() >= 8) {
            txtRating.setTextColor(Color.parseColor("#4489FE"));
        } else if (data.getVoteAverage() >= 6) {
            txtRating.setTextColor(Color.parseColor("#44FF45"));
        } else {
            txtRating.setTextColor(Color.parseColor("#FF4040"));
        }

        txtSynopsis.setText(data.getOverview());
        if (data.getBackdropPath() != null) {
            imgCoover.setVisibility(View.VISIBLE);
            Glide.with(imgCoover).load(data.getBackdropPathOriginal()).into(imgCoover);
        } else {
            imgCoover.setVisibility(View.GONE);
        }
    }

    void setHeaderData() {
        txtTitle.setText(data.getCanonicalName());
        Glide.with(this)
                .load(data.getPosterPathOriginal())
                .error(R.drawable.img404)
                .placeholder(R.drawable.loading)
                .into(detailCover);

        txtSubtitle.setText(data.getCanonicalOriginalName());

        String counter = "" + data.getVoteAverage() + " (" +
                data.getVoteCount() + ")";

        txtCounter.setText(counter);


        final boolean[] up = {true};
        ObjectAnimator animation = ObjectAnimator.ofFloat(chevUp, "translationY", -10);
        animation.setDuration(500);
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (up[0]) {
                    ((ObjectAnimator) animation).setFloatValues(10);
                    ((ObjectAnimator) animation).setDuration(500);
                    up[0] = false;
                } else {
                    ((ObjectAnimator) animation).setFloatValues(-10);
                    ((ObjectAnimator) animation).setDuration(1000);
                    up[0] = true;
                }
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animation.start();

    }
}
