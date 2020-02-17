package id.petersam.newsj.network.service;

import id.petersam.newsj.network.response.GetNewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("v2/top-headlines")
    Call<GetNewsResponse> getNews(@Query("country") String countryId);
}
