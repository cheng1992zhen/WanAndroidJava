package com.example.wanandroidjava.module.main.view;

import com.example.wanandroidjava.module.main.model.UpdateBean;

import per.goweii.basic.core.base.BaseView;

/**
 * @author CuiZhen
 * @date 2019/5/19
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface MainView extends BaseView {
    void updateSuccess(int code, UpdateBean data);
}
