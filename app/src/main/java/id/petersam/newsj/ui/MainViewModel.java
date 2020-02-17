package id.petersam.newsj.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.petersam.newsj.data.repository.NewsRepository;
import id.petersam.newsj.network.response.GetNewsResponse;

public class MainViewModel extends ViewModel {

    private LiveData<GetNewsResponse> newsResponseLiveData;

    public MainViewModel() {
        NewsRepository newsRepository = new NewsRepository();
        this.newsResponseLiveData = newsRepository.getNews("id");
    }

    public LiveData<GetNewsResponse> getNewsResponseLiveData() {
        return newsResponseLiveData;
    }
}
