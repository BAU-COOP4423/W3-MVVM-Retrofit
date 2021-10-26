package com.yilmazgokhan.bauexample.service;

import com.yilmazgokhan.bauexample.data.GameModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface IRequest {

    @GET("games")
    Call<List<GameModel>> getGameList();
}
