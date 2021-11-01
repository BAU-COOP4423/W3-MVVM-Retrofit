package com.yilmazgokhan.bauexample.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yilmazgokhan.bauexample.data.main_list.GameModel;
import com.yilmazgokhan.bauexample.service.ClientGame;
import com.yilmazgokhan.bauexample.service.IRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yilmazgokhan.bauexample.util.Constants.CUSTOM_TAG;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<GameModel>> gameList = new MutableLiveData<>();

    /**
     * Send HTTP Request for getting game list
     */
    public void getGamesData() {
        IRequest request = ClientGame.getApiClient().create(IRequest.class);
        Call<List<GameModel>> call = request.getGameList();
        call.enqueue(new Callback<List<GameModel>>() {
            @Override
            public void onResponse(Call<List<GameModel>> call, Response<List<GameModel>> response) {
                if (response.isSuccessful()) {
                    Log.d(CUSTOM_TAG, "onResponse: ");
                    gameList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<GameModel>> call, Throwable t) {
                Log.d(CUSTOM_TAG, "onFailure: ");
            }
        });
        //return gameList;
    }

    /**
     * This method is used to observe the Live Data
     */
    public LiveData<List<GameModel>> getGameList() {
        return gameList;
    }
}
