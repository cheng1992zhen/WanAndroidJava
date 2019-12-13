package com.example.wanandroidjava.module.mine.view;

import com.example.wanandroidjava.module.main.model.CollectionLinkBean;

import java.util.List;

import per.goweii.basic.core.base.BaseView;

/**
 * @author CuiZhen
 * @date 2019/5/17
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface CollectionLinkView extends BaseView {
    void getCollectLinkListSuccess(int code, List<CollectionLinkBean> data);
    void getCollectLinkListFailed(int code, String msg);

    void updateCollectLinkSuccess(int code, CollectionLinkBean data);
}
