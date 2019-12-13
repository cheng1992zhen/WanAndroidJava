package com.example.wanandroidjava.module.mine.view;

import com.example.wanandroidjava.module.mine.model.CoinRecordBean;

import per.goweii.basic.core.base.BaseView;

/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface CoinView extends BaseView {
    void getCoinSuccess(int code, int coin);

    void getCoinFail(int code, String msg);

    void getCoinRecordListSuccess(int code, CoinRecordBean data);

    void getCoinRecordListFail(int code, String msg);
}
