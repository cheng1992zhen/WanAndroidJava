package com.example.wanandroidjava.module.home.view;

import com.example.wanandroidjava.module.main.model.UserPageBean;

import per.goweii.basic.core.base.BaseView;

/**
 * @author CuiZhen
 * @date 2019/10/3
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface UserPageView extends BaseView {
    void getUserPageSuccess(int code, UserPageBean data);

    void getUserPageFailed(int code, String msg);
}
