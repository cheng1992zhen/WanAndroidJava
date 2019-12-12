package com.example.wanandroidjava.module.main.presenter;

import com.example.wanandroidjava.event.ArticleShareEvent;
import com.example.wanandroidjava.http.RequestListener;
import com.example.wanandroidjava.module.main.model.MainRequest;
import com.example.wanandroidjava.module.main.view.ShareArticleView;

import per.goweii.basic.core.base.BasePresenter;
import per.goweii.rxhttp.request.base.BaseBean;
import per.goweii.rxhttp.request.exception.ExceptionHandle;


/**
 * @author CuiZhen
 * @date 2019/10/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class ShareArticlePresenter extends BasePresenter<ShareArticleView> {

    public void shareArticle(String title, String link) {
        addToRxLife(MainRequest.shareArticle(title, link, new RequestListener<BaseBean>() {
            @Override
            public void onStart() {
                showLoadingDialog();
            }

            @Override
            public void onSuccess(int code, BaseBean data) {
                new ArticleShareEvent().post();
                if (isAttach()) {
                    getBaseView().shareArticleSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttach()) {
                    getBaseView().shareArticleFailed(code, msg);
                }
            }

            @Override
            public void onError(ExceptionHandle handle) {
            }

            @Override
            public void onFinish() {
                dismissLoadingDialog();
            }
        }));
    }
}
