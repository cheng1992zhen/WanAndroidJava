package com.example.wanandroidjava.module.main.view;

import com.example.wanandroidjava.module.main.model.ArticleListBean;

import per.goweii.basic.core.base.BaseView;

/**
 * @author CuiZhen
 * @date 2019/10/3
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface UserArticleView extends BaseView {
    void getUserArticleListSuccess(int code, ArticleListBean data);

    void getUserArticleListFailed(int code, String msg);
}
