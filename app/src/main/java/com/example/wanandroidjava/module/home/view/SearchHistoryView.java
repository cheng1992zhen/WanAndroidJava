package com.example.wanandroidjava.module.home.view;

import com.example.wanandroidjava.module.home.model.HotKeyBean;

import java.util.List;

import per.goweii.basic.core.base.BaseView;

/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface SearchHistoryView extends BaseView {
    void getHotKeyListSuccess(int code, List<HotKeyBean> data);
    void getHotKeyListFail(int code, String msg);
}
