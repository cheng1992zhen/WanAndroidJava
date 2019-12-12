package com.example.wanandroidjava.module.home.presenter;

import com.example.wanandroidjava.http.RequestListener;
import com.example.wanandroidjava.module.home.model.HomeRequest;
import com.example.wanandroidjava.module.home.model.HotKeyBean;
import com.example.wanandroidjava.module.home.view.SearchHistoryView;
import com.example.wanandroidjava.utils.SearchHistoryUtils;
import com.example.wanandroidjava.utils.SettingUtils;

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
public class SearchHistoryPresenter extends BasePresenter<SearchHistoryView> {

    private final SearchHistoryUtils mSearchHistoryUtils = SearchHistoryUtils.newInstance();

    public void getHotKeyList(){
        HomeRequest.getHotKeyList(getRxLife(), new RequestListener<List<HotKeyBean>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, List<HotKeyBean> data) {
                if (isAttach()) {
                    getBaseView().getHotKeyListSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttach()) {
                    getBaseView().getHotKeyListFail(code, msg);
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

    public List<String> getHistory(){
        return mSearchHistoryUtils.get();
    }

    public void saveHistory(List<String> list){
        List<String> saves = list;
        int max = SettingUtils.getInstance().getSearchHistoryMaxCount();
        if (list != null && list.size() > max) {
            saves = list.subList(0, max - 1);
        }
        mSearchHistoryUtils.save(saves);
    }

}
