package id.petersam.newsj.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import id.petersam.newsj.R;
import id.petersam.newsj.data.entity.Article;
import id.petersam.newsj.databinding.ActivityMainBinding;
import id.petersam.newsj.ui.adapter.GridViewAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    private GridViewAdapter adapter;
    private ArrayList<Article> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
        getNews();
    }

    private void init() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        adapter = new GridViewAdapter(this, list);
        binding.rv.setAdapter(adapter);
    }

    private void getNews() {
        mainViewModel.getNewsResponseLiveData().observe(this, getNewsResponse -> {
            Log.d(TAG, getNewsResponse.toString());

            List<Article> articles = getNewsResponse.getArticles();
            list.addAll(articles);
            adapter.notifyDataSetChanged();
        });
    }
}
