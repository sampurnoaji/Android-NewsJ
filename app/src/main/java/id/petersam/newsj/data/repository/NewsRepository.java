package id.petersam.newsj.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import id.petersam.newsj.network.response.GetNewsResponse;
import id.petersam.newsj.network.retrofit.RetrofitClient;
import id.petersam.newsj.network.service.NewsApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private static final String TAG = NewsRepository.class.getSimpleName();
    private NewsApiService newsApiService;

    public NewsRepository() {
        newsApiService = RetrofitClient.getRetrofitInstance().create(NewsApiService.class);
    }

    public LiveData<GetNewsResponse> getNews(String countryId) {
        final MutableLiveData<GetNewsResponse> mutableLiveData = new MutableLiveData<>();
        newsApiService.getNews(countryId).enqueue(new Callback<GetNewsResponse>() {
            @Override
            public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                Log.d(TAG, response.toString());

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, response.body().toString());

                        mutableLiveData.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetNewsResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;
    }
}
