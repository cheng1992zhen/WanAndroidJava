package com.example.wanandroidjava.module.knowledge.view;

import com.example.wanandroidjava.module.main.model.ArticleListBean;

import per.goweii.basic.core.base.BaseView;

/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface KnowledgeArticleView extends BaseView {
    void getKnowledgeArticleListSuccess(int code, ArticleListBean data);
    void getKnowledgeArticleListFail(int code, String msg);
}
