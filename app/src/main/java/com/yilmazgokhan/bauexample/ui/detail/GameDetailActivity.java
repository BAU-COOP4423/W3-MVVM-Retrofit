package com.yilmazgokhan.bauexample.ui.detail;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.yilmazgokhan.bauexample.R;
import com.yilmazgokhan.bauexample.data.detail.GameDetailModel;
import com.yilmazgokhan.bauexample.databinding.ActivityGameDetailBinding;

import static com.yilmazgokhan.bauexample.util.Constants.ARG_GAME_ID;
import static com.yilmazgokhan.bauexample.util.Constants.CUSTOM_TAG;

public class GameDetailActivity extends AppCompatActivity {

    private ActivityGameDetailBinding binding;
    private GameDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //region View Binding
        binding = ActivityGameDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //endregion

        //Prepare View Model
        mViewModel = ViewModelProviders.of(this).get(GameDetailViewModel.class);

        initObservers();
        getGameDetail();
    }


    /**
     * Initialize observers for getting data from the ViewModel
     */
    private void initObservers() {
        mViewModel.getGameDetail().observe(this, new Observer<GameDetailModel>() {
            @Override
            public void onChanged(GameDetailModel gameDetailModel) {
                Log.d(CUSTOM_TAG, "onChanged: ");
                prepareView(gameDetailModel);
            }
        });
    }

    /**
     * Observe the game data that stored in the ViewModel
     */
    private void getGameDetail() {
        int gameId = 0;
        //region get data from previous Activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            gameId = extras.getInt(ARG_GAME_ID, 0);
        }
        //endregion
        //region check the gameId
        if (gameId != 0)
            mViewModel.getGameDetail(gameId);
        else
            Toast.makeText(this, "Game not found.", Toast.LENGTH_SHORT).show();
        //endregion
    }

    /**
     * Prepare UI Components
     *
     * @param model as GameDetailModel
     */
    private void prepareView(GameDetailModel model) {
        //region iGame's components
        if (!TextUtils.isEmpty(model.getThumbnail()))
            Glide.with(this)
                    .load(model.getThumbnail())
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .into(binding.iGame.ivThumbnail);
        if (!TextUtils.isEmpty(model.getTitle()))
            binding.iGame.tvTitle.setText(model.getTitle());
        if (!TextUtils.isEmpty(model.getShortDescription()))
            binding.iGame.tvShortDescription.setText(model.getShortDescription());
        if (!TextUtils.isEmpty(model.getGenre()))
            binding.iGame.tvGenre.setText(String.format("Genre: %s", model.getGenre()));
        if (!TextUtils.isEmpty(model.getPlatform()))
            binding.iGame.tvPlatform.setText(String.format("Platform: %s", model.getPlatform()));
        if (!TextUtils.isEmpty(model.getPublisher()))
            binding.iGame.tvPublisher.setText(String.format("Publisher: %s", model.getPublisher()));
        if (!TextUtils.isEmpty(model.getDeveloper()))
            binding.iGame.tvDeveloper.setText(String.format("Developer: %s", model.getDeveloper()));
        if (!TextUtils.isEmpty(model.getReleaseDate()))
            binding.iGame.tvReleaseDate.setText(model.getReleaseDate());
        //endregion
        //region iSystem's components
        if (model.getMinimumSystemRequirements() != null) {
            if (!TextUtils.isEmpty(model.getMinimumSystemRequirements().getOs()))
                binding.iSystem.tvOs.setText(String.format("Os: %s", model.getMinimumSystemRequirements().getOs()));
            if (!TextUtils.isEmpty(model.getMinimumSystemRequirements().getProcessor()))
                binding.iSystem.tvProcessor.setText(String.format("Processor: %s", model.getMinimumSystemRequirements().getProcessor()));
            if (!TextUtils.isEmpty(model.getMinimumSystemRequirements().getMemory()))
                binding.iSystem.tvMemory.setText(String.format("Memory: %s", model.getMinimumSystemRequirements().getMemory()));
            if (!TextUtils.isEmpty(model.getMinimumSystemRequirements().getGraphics()))
                binding.iSystem.tvGraphics.setText(String.format("Graphics: %s", model.getMinimumSystemRequirements().getGraphics()));
            if (!TextUtils.isEmpty(model.getMinimumSystemRequirements().getStorage()))
                binding.iSystem.tvStorage.setText(String.format("Storage: %s", model.getMinimumSystemRequirements().getStorage()));
        }
        //endregion
        //region ImageViews
        /**
         * 3rd party ImageSlider library can be used here. But for this case, an adapter needs to be written.
         * I chose to show a single image in a single ImageView because I wanted the code to be as simple as possible.
         */
        if (model.getScreenshots().size() > 0) {
            try {
                if (!TextUtils.isEmpty(model.getScreenshots().get(0).getImage()))
                    Glide.with(this)
                            .load(model.getScreenshots().get(0).getImage())
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(binding.ivScreenshot1);
            } catch (Exception e) {
                Log.d(CUSTOM_TAG, "prepareView: " + e.toString());
            }
        }
        //endregion
    }
}