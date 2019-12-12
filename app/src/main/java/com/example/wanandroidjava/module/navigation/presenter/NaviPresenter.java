package com.example.wanandroidjava.module.navigation.presenter;

import com.example.wanandroidjava.http.RequestListener;
import com.example.wanandroidjava.module.navigation.model.NaviBean;
import com.example.wanandroidjava.module.navigation.model.NaviRequest;
import com.example.wanandroidjava.module.navigation.view.NaviView;

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
public class NaviPresenter extends BasePresenter<NaviView> {

    public void getKnowledgeListCache() {
        NaviRequest.getNaviListCache(new RequestListener<List<NaviBean>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, List<NaviBean> data) {
                if (isAttach()) {
                    getBaseView().getNaviListSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
            }

            @Override
            public void onError(ExceptionHandle handle) {
            }

            @Override
            public void onFinish() {
            }
        });
    }

    public void getKnowledgeList() {
        NaviRequest.getNaviList(getRxLife(), new RequestListener<List<NaviBean>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, List<NaviBean> data) {
                if (isAttach()) {
                    getBaseView().getNaviListSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttach()) {
                    getBaseView().getNaviListFail(code, msg);
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
