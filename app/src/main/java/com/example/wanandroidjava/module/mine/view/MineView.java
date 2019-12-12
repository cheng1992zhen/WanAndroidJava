package com.example.wanandroidjava.module.mine.view;


import com.example.wanandroidjava.module.mine.model.UserInfoBean;

import per.goweii.basic.core.base.BaseView;

/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface MineView extends BaseView {
    void getUserInfoSuccess(int code, UserInfoBean coin);

    void getUserInfoFail(int code, String msg);
}
