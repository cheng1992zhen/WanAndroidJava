package com.example.wanandroidjava.project.model;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.example.wanandroidjava.http.BaseRequest;
import com.example.wanandroidjava.http.RequestListener;
import com.example.wanandroidjava.http.WanApi;
import com.example.wanandroidjava.http.WanCache;
import com.example.wanandroidjava.module.main.model.ArticleListBean;
import com.example.wanandroidjava.module.main.model.ChapterBean;

import java.util.List;

import per.goweii.rxhttp.core.RxLife;


/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class ProjectRequest extends BaseRequest {

    public static void getProjectChapters(RxLife rxLife, @NonNull RequestListener<List<ChapterBean>> listener) {
        cacheAndNetList(rxLife,
                WanApi.api().getProjectChapters(),
                WanCache.CacheKey.PROJECT_CHAPTERS,
                ChapterBean.class,
                listener);
    }

    public static void getProjectArticleListCache(int id, @IntRange(from = 1) int page, @NonNull RequestListener<ArticleListBean> listener) {
        cacheBean(WanCache.CacheKey.PROJECT_ARTICLE_LIST(id, page),
                ArticleListBean.class,
                listener);
    }

    public static void getProjectArticleList(RxLife rxLife, boolean refresh, int id, @IntRange(from = 1) int page, @NonNull RequestListener<ArticleListBean> listener) {
        if (page == 1) {
            cacheAndNetBean(rxLife,
                    WanApi.api().getProjectArticleList(page, id),
                    refresh,
                    WanCache.CacheKey.PROJECT_ARTICLE_LIST(id, page),
                    ArticleListBean.class,
                    listener);
        } else {
            rxLife.add(request(WanApi.api().getProjectArticleList(page, id), listener));
        }
    }

}
