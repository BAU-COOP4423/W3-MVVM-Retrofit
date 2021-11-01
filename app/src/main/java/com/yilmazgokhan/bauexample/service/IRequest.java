package com.yilmazgokhan.bauexample.service;

import com.yilmazgokhan.bauexample.data.detail.GameDetailModel;
import com.yilmazgokhan.bauexample.data.main_list.GameModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRequest {

    @GET("games")
    Call<List<GameModel>> getGameList();


    @GET("game")
    Call<GameDetailModel> getGameDetail(@Query("id") int id);
}
