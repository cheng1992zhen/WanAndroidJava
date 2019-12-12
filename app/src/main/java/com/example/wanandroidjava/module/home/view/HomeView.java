package com.example.wanandroidjava.module.home.view;

import com.example.wanandroidjava.module.home.model.BannerBean;
import com.example.wanandroidjava.module.main.model.ArticleBean;
import com.example.wanandroidjava.module.main.model.ArticleListBean;
import com.example.wanandroidjava.module.main.model.ConfigBean;

import java.util.List;

import per.goweii.basic.core.base.BaseView;


/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface HomeView extends BaseView {
    void allFail();
    void getBannerSuccess(int code, List<BannerBean> data);
    void getBannerFail(int code, String msg);

    void getArticleListSuccess(int code, ArticleListBean data);
    void getArticleListFailed(int code, String msg);
    void getTopArticleListSuccess(int code, List<ArticleBean> data);
    void getTopArticleListFailed(int code, String msg);

    void getConfigSuccess(ConfigBean configBean);
}
