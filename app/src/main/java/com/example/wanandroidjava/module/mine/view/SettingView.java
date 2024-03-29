package com.example.wanandroidjava.module.mine.view;

import com.example.wanandroidjava.module.main.model.UpdateBean;

import per.goweii.basic.core.base.BaseView;
import per.goweii.rxhttp.request.base.BaseBean;

/**
 * @author CuiZhen
 * @date 2019/5/19
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface SettingView extends BaseView {
    void updateSuccess(int code, UpdateBean data, boolean click);
    void updateFailed(int code, String msg, boolean click);

    void logoutSuccess(int code, BaseBean data);
    void logoutFailed(int code, String msg);

    void getCacheSizeSuccess(String size);
}
