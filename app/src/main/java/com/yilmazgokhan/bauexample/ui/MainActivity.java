package com.yilmazgokhan.bauexample.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yilmazgokhan.bauexample.R;
import com.yilmazgokhan.bauexample.adapter.GameAdapter;
import com.yilmazgokhan.bauexample.data.GameModel;
import com.yilmazgokhan.bauexample.databinding.ActivityMainBinding;

import java.util.List;

import static com.yilmazgokhan.bauexample.util.Constants.CUSTOM_TAG;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mViewModel;

    private GameAdapter gameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //region View Binding
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //endregion

        //Prepare View Model
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        this.initComponents();
        this.initClicks();
        this.initObservers();
        this.observeGameData();
    }

    /**
     * Initialize components & Create necessary adapter
     */
    private void initComponents() {
        //Recycler View initialize
        binding.rvGames.setLayoutManager(new LinearLayoutManager(this));
        binding.rvGames.setItemAnimator(new DefaultItemAnimator());
        gameAdapter = new GameAdapter(this);
        binding.rvGames.setAdapter(gameAdapter);
    }

    /**
     * Handle RecyclerView's clicks
     */
    private void initClicks() {
        gameAdapter.setOnClickListener((pos, gameModel) -> {
            Log.d(CUSTOM_TAG, "onClick: ");
            Toast.makeText(this, gameModel.getTitle(), Toast.LENGTH_SHORT).show();
            // TODO: 25.10.2021
        });
    }

    /**
     * Initialize observers for getting data from the ViewModel
     */
    private void initObservers() {
        mViewModel.getGameList().observe(this, new Observer<List<GameModel>>() {
            @Override
            public void onChanged(List<GameModel> gameModels) {
                Log.d(CUSTOM_TAG, "onChanged: ");
                prepareRecycler(gameModels);
            }
        });
        /*
        mViewModel.callRequest().observe(this, (Observer<List<GameModel>>) gameModels -> Log.d("TAG", "onChanged: "));
         */
    }

    /**
     * Observe the game data that stored in the ViewModel
     */
    private void observeGameData() {
        mViewModel.getGamesData();
    }

    /**
     * Set the data to the RecyclerView's adapter
     *
     * @param models as List<GameModel>
     */
    private void prepareRecycler(List<GameModel> models) {
        gameAdapter.setAdapterList(models);
    }
}