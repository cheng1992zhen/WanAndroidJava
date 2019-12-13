package com.example.wanandroidjava.module.mine.view;

import com.example.wanandroidjava.module.main.model.ArticleListBean;

import per.goweii.basic.core.base.BaseView;

/**
 * @author CuiZhen
 * @date 2019/5/17
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface CollectionArticleView extends BaseView {
    void getCollectArticleListSuccess(int code, ArticleListBean data);
    void getCollectArticleListFailed(int code, String msg);
}
