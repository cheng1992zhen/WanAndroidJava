package com.example.wanandroidjava.module.mine.presenter;

import com.example.wanandroidjava.http.RequestListener;
import com.example.wanandroidjava.module.mine.model.CoinRankBean;
import com.example.wanandroidjava.module.mine.model.MineRequest;
import com.example.wanandroidjava.module.mine.view.CoinRankView;

import per.goweii.basic.core.base.BasePresenter;
import per.goweii.rxhttp.request.exception.ExceptionHandle;


/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class CoinRankPresenter extends BasePresenter<CoinRankView> {

    public void getCoinRankList(int page) {
        addToRxLife(MineRequest.getCoinRankList(page, new RequestListener<CoinRankBean>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, CoinRankBean data) {
                if (isAttach()) {
                    getBaseView().getCoinRankListSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttach()) {
                    getBaseView().getCoinRankListFail(code, msg);
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
