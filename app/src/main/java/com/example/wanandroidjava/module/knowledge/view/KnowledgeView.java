package com.example.wanandroidjava.module.knowledge.view;

import com.example.wanandroidjava.module.main.model.ChapterBean;

import java.util.List;

import per.goweii.basic.core.base.BaseView;

/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface KnowledgeView extends BaseView {
    void getKnowledgeListSuccess(int code, List<ChapterBean> data);
    void getKnowledgeListFail(int code, String msg);
}
