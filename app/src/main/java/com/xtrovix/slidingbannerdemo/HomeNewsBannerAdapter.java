package com.xtrovix.slidingbannerdemo;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

class HomeNewsBannerAdapter extends PagerAdapter {
    private String[] urls;
    private LayoutInflater layoutInflater;
    private Context context;


    public HomeNewsBannerAdapter(Context context, String[] urls) {
        this.context = context;
        this.urls = urls;
    }


    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View imageLayout = layoutInflater.inflate(R.layout.raw_for_home_news_banner, view, false);

        assert imageLayout != null;
        ImageView imageView = imageLayout
                .findViewById(R.id.img_news_home_banner);


        Picasso.with(context)
                .load(ApiClient.BASE_URL +urls[position])
                .into(imageView);

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }


    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
