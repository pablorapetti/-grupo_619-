package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface EventsInterface {
    @POST("/api/api/event")
    Call<EventsResponse> publicar(@Body EntidadEvents body, @Header("token") String token);
}
