package com.example.wanandroidjava.module.knowledge.presenter;

import com.example.wanandroidjava.http.RequestListener;
import com.example.wanandroidjava.module.knowledge.model.KnowledgeRequest;
import com.example.wanandroidjava.module.knowledge.view.KnowledgeView;
import com.example.wanandroidjava.module.main.model.ChapterBean;

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
public class KnowledgePresenter extends BasePresenter<KnowledgeView> {

    public void getKnowledgeListCache() {
        KnowledgeRequest.getKnowledgeListCache(new RequestListener<List<ChapterBean>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, List<ChapterBean> data) {
                if (isAttach()) {
                    getBaseView().getKnowledgeListSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
            }

            @Override
            public void onError(ExceptionHandle handle) {
            }

            @Override
            public void onFinish() {
            }
        });
    }

    public void getKnowledgeList() {
        KnowledgeRequest.getKnowledgeList(getRxLife(), new RequestListener<List<ChapterBean>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, List<ChapterBean> data) {
                if (isAttach()) {
                    getBaseView().getKnowledgeListSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttach()) {
                    getBaseView().getKnowledgeListFail(code, msg);
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
