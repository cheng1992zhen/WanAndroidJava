package com.example.wanandroidjava.module.project.presenter;

import com.example.wanandroidjava.http.RequestListener;
import com.example.wanandroidjava.module.main.model.ChapterBean;
import com.example.wanandroidjava.module.project.model.ProjectRequest;
import com.example.wanandroidjava.module.project.view.ProjectView;

import java.util.List;

import per.goweii.basic.core.base.BasePresenter;
import per.goweii.rxhttp.request.exception.ExceptionHandle;


/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class ProjectPresenter extends BasePresenter<ProjectView> {

    public void getProjectChapters(){
        ProjectRequest.getProjectChapters(getRxLife(), new RequestListener<List<ChapterBean>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, List<ChapterBean> data) {
                if (isAttach()) {
                    getBaseView().getProjectChaptersSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttach()) {
                    getBaseView().getProjectChaptersFailed(code, msg);
                }
            }

            @Override
            public void onError(ExceptionHandle handle) {
            }

            @Override
            public void onFinish() {
            }
        });
    }
}
