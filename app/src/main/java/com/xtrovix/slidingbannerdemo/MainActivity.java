package com.xtrovix.slidingbannerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;
import com.xtrovix.slidingbannerdemo.model.BannerImageModel;
import com.xtrovix.slidingbannerdemo.model.HomeImageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPagerNewsBanner;
    private CirclePageIndicator dashPagerIndicatorNewsBanner;
    private String[] urlsNewsBanner;
    private int NUM_PAGES_News_Banner;
    private int currentPage = 0;

    private String userToken = "6864b15fae1b33d273367ec39ceca14e";
    private TextView txtNoDataFound;
    List<HomeImageModel> homeImageModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();// find id's of all widgets.

        getBannerImages(userToken);

    }

    private void getBannerImages(String userToken) {

        GetBannerImageInterface bannerImageInterface = ApiClient.getClient().create(GetBannerImageInterface.class);
        Call<BannerImageModel> call = bannerImageInterface.get_type_categories("Bearer " + userToken);

        call.enqueue(new Callback<BannerImageModel>() {
            @Override
            public void onResponse(Call<BannerImageModel> call, Response<BannerImageModel> response) {

                BannerImageModel bannerImageModel = response.body();

                if (response.toString().contains("code=200")) {

                    if (bannerImageModel.getStatus().equals(1)) {

                        homeImageModelList = bannerImageModel.getHomeImage();

                        getBanner();

                    }
                }
            }

            @Override
            public void onFailure(Call<BannerImageModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "something went wrong" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getBanner() {
        String[] slideImagesNews = new String[homeImageModelList.size()];

        if (homeImageModelList.isEmpty()) {
            txtNoDataFound.setVisibility(View.VISIBLE);
        } else {

            for (int i = 0; i < homeImageModelList.size(); i++) {
                slideImagesNews[i] = homeImageModelList.get(i).getImage();

                urlsNewsBanner = slideImagesNews;
            }
            txtNoDataFound.setVisibility(View.GONE);
        }

        viewPagerNewsBanner.setAdapter(new HomeNewsBannerAdapter(MainActivity.this, urlsNewsBanner));
        dashPagerIndicatorNewsBanner.setViewPager(viewPagerNewsBanner);

        float densityNewsBanner = MainActivity.this.getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        dashPagerIndicatorNewsBanner.setRadius(5 * densityNewsBanner);

        NUM_PAGES_News_Banner = urlsNewsBanner.length;

        setAnimationNewsBanner();     // Auto start of viewpager

        // Pager listener over indicator
        dashPagerIndicatorNewsBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

    private void setAnimationNewsBanner() {
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES_News_Banner) {
                    currentPage = 0;
                }
                viewPagerNewsBanner.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
    }

    private void initWidget() {
        viewPagerNewsBanner = findViewById(R.id.viewPager_home_news_home);
        dashPagerIndicatorNewsBanner = findViewById(R.id.sliderDots_service_news_home);
        txtNoDataFound = findViewById(R.id.textview_no_data_new_banner);
    }
}
