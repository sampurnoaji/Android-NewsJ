package id.petersam.newsj.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import id.petersam.newsj.data.entity.Article;
import id.petersam.newsj.databinding.ItemTopHeadlinesBinding;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Article> articles;

    private OnItemClickCallback onItemClickCallback;

    public ViewPagerAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Article article);
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ItemTopHeadlinesBinding binding = ItemTopHeadlinesBinding.inflate(LayoutInflater.from(context), container, false);

        Article article = articles.get(position);

        binding.tvTitle.setText(article.getTitle());
        Glide.with(context).load(article.getUrlToImage())
                .placeholder(new ColorDrawable(Color.GRAY))
                .into(binding.ivPoster);

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(article);
            }
        });

        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void refreshList(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }
}
