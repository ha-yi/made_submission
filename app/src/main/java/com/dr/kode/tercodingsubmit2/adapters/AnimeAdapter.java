package com.dr.kode.tercodingsubmit2.adapters;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dr.kode.tercodingsubmit2.DetailConstrainedActivity;
import com.dr.kode.tercodingsubmit2.model.Tontonan;
import com.dr.kode.tercodingsubmit2.widget.MovieItem;

import org.parceler.Parcels;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeHolder> {
    private List<Tontonan> items;
    private String type;
    private boolean isFavoriteList;

    public AnimeAdapter(List dt, String type, boolean isFavList) {
        this.items = dt;
        this.type = type;
        this.isFavoriteList = isFavList;
    }

    public void addItems(List items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnimeHolder(new MovieItem(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeHolder holder, int position) {
        holder.bind(items.get(position), type, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class AnimeHolder extends RecyclerView.ViewHolder {
        MovieItem item;

        AnimeHolder(@NonNull View itemView) {
            super(itemView);
            item = (MovieItem) itemView;
        }

        void bind(Tontonan it, String type, int position) {
            item.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT)
            );
            item.bindData(
                    it.getCanonicalName(),
                    it.getPosterPath185(),
                    String.valueOf(it.getVoteAverage()),
                    String.valueOf(it.getVoteCount()),
                    it, type,
                    () -> {
                        if (isFavoriteList) {
                           items.remove(position);
                           notifyDataSetChanged();
                        }
                    }
            );
            item.setOnClickListener(v -> {
                Intent intent = new Intent(item.getContext(), DetailConstrainedActivity.class);
                intent.putExtra("data", Parcels.wrap(it));
                intent.putExtra("id", it.getId());
                item.getContext().startActivity(intent);
            });
        }
    }
}
