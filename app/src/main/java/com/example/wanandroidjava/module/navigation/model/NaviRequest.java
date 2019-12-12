package com.example.wanandroidjava.module.navigation.model;

import android.support.annotation.NonNull;

import com.example.wanandroidjava.http.BaseRequest;
import com.example.wanandroidjava.http.RequestListener;
import com.example.wanandroidjava.http.WanApi;
import com.example.wanandroidjava.http.WanCache;

import java.util.List;

import per.goweii.rxhttp.core.RxLife;


/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class NaviRequest extends BaseRequest {

    public static void getNaviListCache(@NonNull RequestListener<List<NaviBean>> listener) {
        cacheList(WanCache.CacheKey.NAVI_LIST,
                NaviBean.class,
                listener);
    }

    public static void getNaviList(RxLife rxLife, @NonNull RequestListener<List<NaviBean>> listener) {
        cacheAndNetList(rxLife,
                WanApi.api().getNaviList(),
                true,
                WanCache.CacheKey.NAVI_LIST,
                NaviBean.class,
                listener);
    }

}
