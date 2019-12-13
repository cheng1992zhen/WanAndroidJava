package com.example.wanandroidjava.module.wxarticle.presenter;

import com.example.wanandroidjava.http.RequestListener;
import com.example.wanandroidjava.module.main.model.ChapterBean;
import com.example.wanandroidjava.module.wxarticle.model.WxRequest;
import com.example.wanandroidjava.module.wxarticle.view.WxView;

import java.util.List;

import per.goweii.basic.core.base.BasePresenter;
import per.goweii.rxhttp.request.exception.ExceptionHandle;


/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class WxPresenter extends BasePresenter<WxView> {

    public void getWxArticleChapters(){
        WxRequest.getWxArticleChapters(getRxLife(), new RequestListener<List<ChapterBean>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, List<ChapterBean> data) {
                if (isAttach()) {
                    getBaseView().getWxArticleChaptersSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttach()) {
                    getBaseView().getWxArticleChaptersFailed(code, msg);
                }
            }

            @Override
            public void onError(ExceptionHandle handle) {
            }

            @Override
            public void onFinish() {
            }
        });
    }

}
