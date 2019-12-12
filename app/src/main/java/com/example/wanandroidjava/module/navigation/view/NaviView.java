package com.example.wanandroidjava.module.navigation.view;

import com.example.wanandroidjava.module.navigation.model.NaviBean;

import java.util.List;

import per.goweii.basic.core.base.BaseView;

/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface NaviView extends BaseView {
    void getNaviListSuccess(int code, List<NaviBean> data);
    void getNaviListFail(int code, String msg);
}
