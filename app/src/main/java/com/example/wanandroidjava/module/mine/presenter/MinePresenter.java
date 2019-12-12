package com.example.wanandroidjava.module.mine.presenter;

import com.example.wanandroidjava.http.RequestListener;
import com.example.wanandroidjava.module.mine.model.MineRequest;
import com.example.wanandroidjava.module.mine.model.UserInfoBean;
import com.example.wanandroidjava.module.mine.view.MineView;

import per.goweii.basic.core.base.BasePresenter;
import per.goweii.rxhttp.request.exception.ExceptionHandle;


/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class MinePresenter extends BasePresenter<MineView> {

    public void getUserInfo() {
        addToRxLife(MineRequest.getUserInfo(new RequestListener<UserInfoBean>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, UserInfoBean data) {
                if (isAttach()) {
                    getBaseView().getUserInfoSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttach()) {
                    getBaseView().getUserInfoFail(code, msg);
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
