package com.example.wanandroidjava.module.home.presenter;

import com.example.wanandroidjava.event.CollectionEvent;
import com.example.wanandroidjava.http.RequestListener;
import com.example.wanandroidjava.module.home.view.WebDialogView;
import com.example.wanandroidjava.module.main.model.ArticleBean;
import com.example.wanandroidjava.module.main.model.MainRequest;
import com.example.wanandroidjava.widget.CollectView;

import per.goweii.basic.core.base.BasePresenter;
import per.goweii.rxhttp.request.base.BaseBean;
import per.goweii.rxhttp.request.exception.ExceptionHandle;


/**
 * @author CuiZhen
 * @date 2019/8/31
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class WebDialogPresenter extends BasePresenter<WebDialogView> {

    public void collect(ArticleBean item, final CollectView v) {
        addToRxLife(MainRequest.collect(item.getId(), new RequestListener<BaseBean>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, BaseBean data) {
                CollectionEvent.postCollectWithArticleId(item.getId());
                item.setCollect(true);
                if (!v.isChecked()) {
                    v.toggle();
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (v.isChecked()) {
                    v.toggle();
                }
            }

            @Override
            public void onError(ExceptionHandle handle) {
            }

            @Override
            public void onFinish() {
            }
        }));
    }

    public void uncollect(ArticleBean item, final CollectView v) {
        addToRxLife(MainRequest.uncollect(item.getId(), new RequestListener<BaseBean>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, BaseBean data) {
                CollectionEvent.postUnCollectWithArticleId(item.getId());
                item.setCollect(false);
                if (v.isChecked()) {
                    v.toggle();
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (!v.isChecked()) {
                    v.toggle();
                }
            }

            @Override
            public void onError(ExceptionHandle handle) {
            }

            @Override
            public void onFinish() {
            }
        }));
    }
}
