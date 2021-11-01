package com.yilmazgokhan.bauexample.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yilmazgokhan.bauexample.R;
import com.yilmazgokhan.bauexample.data.main_list.GameModel;
import com.yilmazgokhan.bauexample.databinding.ItemGameBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GamesViewHolders> {

    private List<GameModel> games;
    private Context context;
    private ItemClickListener itemClickListener;

    /**
     * Constructor for GameAdapter class
     *
     * @param context as Context for Glide
     */
    public GameAdapter(Context context) {
        this.context = context;
        this.games = new ArrayList<>();
    }

    /**
     * Setting the list data & notify the adapter for render list
     *
     * @param games as List<GameModel>
     */
    public void setAdapterList(List<GameModel> games) {
        this.games.addAll(games);
        this.notifyDataSetChanged();
    }

    /**
     * Default onCreate method.
     * Connect Adapter & layout
     */
    @NotNull
    @Override
    public GamesViewHolders onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemGameBinding binding = ItemGameBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GamesViewHolders(binding);
    }

    /**
     * Prepare row item with data
     */
    @Override
    public void onBindViewHolder(GamesViewHolders holder, int position) {
        GameModel gameModel = games.get(position);
        if (!TextUtils.isEmpty(gameModel.getThumbnail()))
            Glide.with(context)
                    .load(gameModel.getThumbnail())
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .into(holder.binding.ivThumbnail);
        if (!TextUtils.isEmpty(gameModel.getTitle()))
            holder.binding.tvTitle.setText(gameModel.getTitle());
        if (!TextUtils.isEmpty(gameModel.getShortDescription()))
            holder.binding.tvShortDescription.setText(gameModel.getShortDescription());
        if (!TextUtils.isEmpty(gameModel.getGenre()))
            holder.binding.tvGenre.setText(String.format("Genre: %s", gameModel.getGenre()));
        if (!TextUtils.isEmpty(gameModel.getPlatform()))
            holder.binding.tvPlatform.setText(String.format("Platform: %s", gameModel.getPlatform()));
        if (!TextUtils.isEmpty(gameModel.getPublisher()))
            holder.binding.tvPublisher.setText(String.format("Publisher: %s", gameModel.getPublisher()));
        if (!TextUtils.isEmpty(gameModel.getDeveloper()))
            holder.binding.tvDeveloper.setText(String.format("Developer: %s", gameModel.getDeveloper()));
        if (!TextUtils.isEmpty(gameModel.getReleaseDate()))
            holder.binding.tvReleaseDate.setText(gameModel.getReleaseDate());
    }

    private GameModel getItem(int pos) {
        return games.get(pos);
    }

    /**
     * @return the data list count
     */
    @Override
    public int getItemCount() {
        return this.games.size();
    }

    //region Click Listener

    /**
     * This method is used for te interface in the adapter to communicate with the View
     *
     * @param itemClickListener as ItemClickListener
     */
    public void setOnClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * This interface is used to catch the item click event
     */
    public interface ItemClickListener {
        void onClick(int pos, GameModel gameModel);
    }
    //endregion

    public class GamesViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemGameBinding binding;

        public GamesViewHolders(ItemGameBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(getAdapterPosition(), getItem(getAdapterPosition()));
        }
    }
}