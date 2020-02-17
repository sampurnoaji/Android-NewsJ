package id.petersam.newsj.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import id.petersam.newsj.R;
import id.petersam.newsj.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
        getNews();
    }

    private void init() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    private void getNews() {
        mainViewModel.getNewsResponseLiveData().observe(this, getNewsResponse -> {
            Log.d(TAG, getNewsResponse.toString());

            binding.tv.setText(getNewsResponse.getArticles().get(0).getTitle());
        });
    }
}
