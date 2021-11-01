package com.yilmazgokhan.bauexample.ui.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yilmazgokhan.bauexample.data.detail.GameDetailModel;
import com.yilmazgokhan.bauexample.service.ClientGame;
import com.yilmazgokhan.bauexample.service.IRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yilmazgokhan.bauexample.util.Constants.CUSTOM_TAG;

public class GameDetailViewModel extends ViewModel {

    private MutableLiveData<GameDetailModel> gameDetail = new MutableLiveData<>();

    /**
     * Send HTTP Request for getting game detail via gameId
     */
    public void getGameDetail(int gameId) {
        IRequest request = ClientGame.getApiClient().create(IRequest.class);
        Call<GameDetailModel> call = request.getGameDetail(gameId);
        call.enqueue(new Callback<GameDetailModel>() {
            @Override
            public void onResponse(Call<GameDetailModel> call, Response<GameDetailModel> response) {
                if (response.isSuccessful()) {
                    Log.d(CUSTOM_TAG, "onResponse: ");
                    gameDetail.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GameDetailModel> call, Throwable t) {
                Log.d(CUSTOM_TAG, "onFailure: ");
            }
        });
    }

    /**
     * This method is used to observe the Live Data
     */
    public LiveData<GameDetailModel> getGameDetail() {
        return gameDetail;
    }
}
