package com.example.wanandroidjava.module.project.view;

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
public interface ProjectView extends BaseView {
    void getProjectChaptersSuccess(int code, List<ChapterBean> data);
    void getProjectChaptersFailed(int code, String msg);
}
