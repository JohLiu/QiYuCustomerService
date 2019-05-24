package com.joh.qiyudemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.qiyukf.unicorn.api.ImageLoaderListener;
import com.qiyukf.unicorn.api.UnicornImageLoader;


/**
 * 图片加载
 *
 * @author : Joh_hz
 * @date : 2019/5/24 15:26
 */
public class GlideImageLoader implements UnicornImageLoader {
    private Context context;

    public GlideImageLoader(Context context) {
        this.context = context.getApplicationContext();
    }

    @Nullable
    @Override
    public Bitmap loadImageSync(String uri, int width, int height) {
        return null;
    }

    @Override
    public void loadImage(String uri, int width, int height, final ImageLoaderListener listener) {
        if (width <= 0 || height <= 0) {
            width = height = Integer.MIN_VALUE;
        }

        //Glide4.0以下版本
//        Glide.with(context).load(uri).asBitmap().into(new SimpleTarget<Bitmap>(width, height) {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                if (listener != null) {
//                    listener.onLoadComplete(resource);
//                }
//            }
//
//            @Override
//            public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                if (listener != null) {
//                    listener.onLoadFailed(e);
//                }
//            }
//        });

        //Glide4.0往上版本
        RequestOptions options = new RequestOptions()
                .centerCrop();
        Glide.with(context).asBitmap().load(uri).apply(options)
                .into(new SimpleTarget<Bitmap>(width, height) {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        if (listener != null) {
                            listener.onLoadComplete(resource);
                        }
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        Throwable t = new Throwable("加载异常");
                        listener.onLoadFailed(t);
                    }
                });
    }
}
