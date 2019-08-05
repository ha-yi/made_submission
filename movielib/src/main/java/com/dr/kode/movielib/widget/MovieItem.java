package com.dr.kode.movielib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.dr.kode.movielib.R;
import com.dr.kode.movielib.themoviedb.Tontonan;
//import com.dr.kode.tercodingsubmit2.utils.IMDBDBCall;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MovieItem extends FrameLayout {

    private ImageView imgCover;
    private ImageView favIcon;
    private TextView txtTitle;
    private TextView txtCount;
    private TextView txtRating;
    private View layLove;
    private OnFavorite onFavorite;


    public MovieItem(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MovieItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MovieItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.movie_item, this, true);
        imgCover = findViewById(R.id.imgCover);
        txtCount = findViewById(R.id.txtCount);
        txtTitle = findViewById(R.id.txtTitle);
        txtRating = findViewById(R.id.txtRating);
        layLove = findViewById(R.id.layLove);
        favIcon = findViewById(R.id.favIcon);
    }

    /**
     * Bind view to its content.
     * @param title Title of the movie
     * @param imgUrl image URL of movie cover
     * @param rating rating to show in big tex
     * @param count the number of peoples who's vote up this movie.
     */
    public void bindData(
            String title,
            String imgUrl,
            String rating,
            String count,
            Tontonan data,
            String type,
            OnFavorite onFavorite
    ) {
        this.onFavorite = onFavorite;
        Glide.with(this)
                .load(imgUrl)
                .placeholder(R.drawable.loading)
                .error(R.drawable.img404)
                .transform(new CenterCrop(), new RoundedCorners(10))
                .into(imgCover);
        txtTitle.setText(title);
        txtRating.setText(rating);
        txtCount.setText(count);
        layLove.setOnClickListener(v -> {
            if (onFavorite.isFavorite(data.getId())) {
                onFavorite.remove(data.getId(), title);
            } else {
                onFavorite.add(data, type, title);
            }
            onFavorite.onChanges();
        });
        checkFav(data.getId());
    }

    private void checkFav(int id) {
        if (onFavorite.isFavorite(id)) {
            favIcon.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            favIcon.setImageResource(R.drawable.ic_favorite_grey_24);
        }
    }

    /**
     * Unbind to clear up widget
     */
    public void unbind() {
        txtTitle.setText("");
        txtRating.setText("");
        txtCount.setText("");
        imgCover.setImageDrawable(null);
    }
}
