package com.example.ruhisaraf.finalapp.Models;

import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ruhisaraf on 4/2/2016.
 */
interface MLabsDriver {
    @GET("databases/")
    Call<List<String>> getDatabases(
            @Query("apiKey") String apiKey);

    @GET("databases/{dbName}/collections/")
    Call<List<String>> getCollections(
            @Path("dbName") String databases,
            @Query("apiKey") String apiKey);

    @GET("databases/{dbName}/collections/{collectionsName}")
    Call<List<User>> getDocument(
            @Path("dbName") String databases,
            @Path("collectionsName") String collections,
            @Query("apiKey") String apiKey,
            @QueryMap Map<String, Object> options);


    @GET("databases/{dbName}/collections/{collectionsName}")
    Call<Integer> getNumberOfDocuments(
            @Path("dbName") String databases,
            @Path("collectionsName") String collections,
            @Query("apiKey") String apiKey,
            @QueryMap Map<String, Object> options);

    /* Single document functions */
    @Headers("content-type: application/json")
    @POST("databases/{dbName}/collections/{collectionsName}")
    Call<ResponseBody> insertDocument(
            @Path("dbName") String databases,
            @Path("collectionsName") String collections,
            @Query("apiKey") String apiKey,
            @Body Object document);

    @GET("databases/{dbName}/collections/{collectionsName}/{id}")
    Call<User> viewDocument(
            @Path("dbName") String databases,
            @Path("collectionsName") String collections,
            @Path("id") String userID,
            @Query("apiKey") String apiKey);

    @Headers("content-type: application/json")
    @PUT("databases/{dbName}/collections/{collectionsName}/{id}")
    Call<ResponseBody> updateDocument(
            @Path("dbName") String databases,
            @Path("collectionsName") String collections,
            @Path("id") String userID,
            @Query("apiKey") String apiKey,
            @Body Object document);

    @Headers({
            "asycn: true",
            "timeout: 300000"
    })
    @DELETE("databases/{dbName}/collections/{collectionsName}/{id}")
    Call<ResponseBody> deleteDocument(
            @Path("dbName") String databases,
            @Path("collectionsName") String collections,
            @Path("id") String userID,
            @Query("apiKey") String apiKey);

}

class MLabsServiceGenerator {
    private static String baseURL = "https://api.mongolab.com/api/1/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
