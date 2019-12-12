package com.example.wanandroidjava.module.home.view;

import com.example.wanandroidjava.module.main.model.ArticleListBean;

import per.goweii.basic.core.base.BaseView;

/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface SearchResultView extends BaseView {
    void searchSuccess(int code, ArticleListBean data);
    void searchFailed(int code, String msg);
}
