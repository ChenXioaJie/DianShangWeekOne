package com.bwei.lenovo.dianshangweekone.api;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class Api extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this).build();

        ImageLoader.getInstance().init(build);

    }
}
