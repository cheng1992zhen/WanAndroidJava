package com.example.wanandroidjava.module.main.presenter;


import com.example.wanandroidjava.http.RequestCallback;
import com.example.wanandroidjava.module.main.model.MainRequest;
import com.example.wanandroidjava.module.main.model.UpdateBean;
import com.example.wanandroidjava.module.main.view.MainView;
import com.example.wanandroidjava.utils.UpdateUtils;

import per.goweii.basic.core.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainView> {

    public void update(){
        if (UpdateUtils.newInstance().isTodayChecked()) {
            return;
        }
        MainRequest.update(getRxLife(), new RequestCallback<UpdateBean>() {
            @Override
            public void onSuccess(int code, UpdateBean data) {
                if (isAttach()) {
                    getBaseView().updateSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {

            }
        });
    }

}
