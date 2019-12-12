package com.example.wanandroidjava.module.main.view;

import com.example.wanandroidjava.module.main.model.CollectArticleEntity;

import per.goweii.basic.core.base.BaseView;

/**
 * @author CuiZhen
 * @date 2019/5/20
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface WebView extends BaseView {
    void collectSuccess(CollectArticleEntity entity);
    void collectFailed(String msg);

    void uncollectSuccess(CollectArticleEntity entity);

    void uncollectFailed(String msg);
}
