package id.petersam.newsj.ui;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import id.petersam.newsj.R;
import id.petersam.newsj.data.entity.Article;
import id.petersam.newsj.databinding.ActivityMainBinding;
import id.petersam.newsj.ui.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    private List<Article> list = new ArrayList<>();

    private ViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        initPagerAdapter();
        getTopHeadlines();
    }

    private void initPagerAdapter() {
        pagerAdapter = new ViewPagerAdapter(this, list);
        binding.vpNews.setAdapter(pagerAdapter);
        binding.vpNews.setPadding(48, 8, 48, 8);
        binding.vpNews.setPageMargin(24);
        binding.vpNews.setClipToPadding(false);
        binding.vpNews.setOffscreenPageLimit(3);

        final int[] currentPage = {0};
        Handler handler = new Handler();
        Runnable update = () -> {
            if (currentPage[0] == pagerAdapter.getCount() - 1) {
                currentPage[0] = 0;
            }
            binding.vpNews.setCurrentItem(currentPage[0]++, true);
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 3000);
    }

    private void getTopHeadlines() {
        mainViewModel.getNewsResponseLiveData().observe(this, getNewsResponse -> {
            if (getNewsResponse == null) return;

            Log.d(TAG, getNewsResponse.toString());

            List<Article> articles = getNewsResponse.getArticles();
            list.addAll(articles);
            pagerAdapter.refreshList(list);
        });
    }
}
