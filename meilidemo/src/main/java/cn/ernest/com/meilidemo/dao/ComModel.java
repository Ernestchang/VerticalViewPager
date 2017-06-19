package cn.ernest.com.meilidemo.dao;


import java.util.Map;

import cn.ernest.com.meilidemo.bean.MeiShowBean;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 2016/6/27 10:37
 * author: ernest
 */
public interface ComModel {

    @GET("/hera/daily/v1/list/android")
    Observable<MeiShowBean> getMeiShow(@QueryMap Map<String, String> map);

}
