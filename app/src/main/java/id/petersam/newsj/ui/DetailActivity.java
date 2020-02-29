package id.petersam.newsj.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;

import id.petersam.newsj.R;
import id.petersam.newsj.data.entity.Article;
import id.petersam.newsj.databinding.ActivityDetailBinding;
import id.petersam.newsj.util.DateParser;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_NEWS = "EXTRA_NEWS";

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getNews();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getNews() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Article article = bundle.getParcelable(EXTRA_NEWS);

            if (article != null) {
                Glide.with(this)
                        .load(article.getUrlToImage())
                        .placeholder(new ColorDrawable(Color.LTGRAY))
                        .into(binding.image);
                binding.title.setText(article.getTitle());
                binding.publishedAt.setText(DateParser.toLocalDateTimeFull(article.getPublishedAt()));
                binding.author.setText(article.getAuthor());
                binding.content.setText(article.getContent());
            }
        }
    }
}
