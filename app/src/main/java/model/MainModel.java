package model;

import android.util.Log;

import com.google.gson.Gson;

import bases.IView;
import beans.GoodsBean;
import beans.SyNine;
import beans.SyPic;
import http.OkHttpUtils;
import http.httpConfig;

/**
 * Created by Lenovo on 2018/12/21.
 */

public class MainModel{
    OkHttpUtils okHttpUtils = OkHttpUtils.getoKhttpUtils();
    public void getPic(final IMainModelCallBack1 iMainModelCallBack) {
        okHttpUtils.doGet(httpConfig.SyPicurl, new OkHttpUtils.IOKhttpUtilsCallback() {
            @Override
            public void onFailure(String error) {
                if(iMainModelCallBack!=null){
                    iMainModelCallBack.getError(error);
                }
            }

            @Override
            public void onResponse(String json) {
                SyPic syPic = new Gson().fromJson(json, SyPic.class);
                String code = syPic.getCode();
                if(code.equals("0")){
                    if(iMainModelCallBack!=null){
                        iMainModelCallBack.getSuccess(syPic);
                    }
                }else{
                    if(iMainModelCallBack!=null){
                        iMainModelCallBack.getError("未获取到数据");
                    }
                }

            }
        });
    }

    public void getSyNine(final IMainModelCallBack2 iMainModelCallBack) {
        okHttpUtils.doGet(httpConfig.SyNine, new OkHttpUtils.IOKhttpUtilsCallback() {
            @Override
            public void onFailure(String error) {
                if(iMainModelCallBack!=null){
                    iMainModelCallBack.getError(error);
                }
            }

            @Override
            public void onResponse(String json) {
                SyNine syNine = new Gson().fromJson(json, SyNine.class);
                String code = syNine.getCode();
                if(code.equals("0")){
                    if(iMainModelCallBack!=null){
                        iMainModelCallBack.getNineSuccess(syNine);
                    }
                }else{
                    if(iMainModelCallBack!=null){
                        iMainModelCallBack.getError("未获取到数据");
                    }
                }
            }
        });
    }
    public void getList(final IMainModelCallBack3 iMainModelCallBack) {
        okHttpUtils.doGet(httpConfig.listUrl, new OkHttpUtils.IOKhttpUtilsCallback() {
            @Override
            public void onFailure(String error) {
                if(iMainModelCallBack!=null){
                    iMainModelCallBack.getError(error);
                }
            }

            @Override
            public void onResponse(String json) {
                GoodsBean goodsBean = new Gson().fromJson(json, GoodsBean.class);
                String code = goodsBean.getCode();
                if(code.equals("0")){
                    if(iMainModelCallBack!=null){
                        Log.i("aaa", "onResponse: "+goodsBean.getData().toString());
                        iMainModelCallBack.getListSuccess(goodsBean);
                    }
                }else{
                    if(iMainModelCallBack!=null){
                        iMainModelCallBack.getError("未获取到数据");
                    }
                }
            }
        });
    }

    public interface IMainModelCallBack1{
        void getSuccess(SyPic syPic);
        void getError(String error);
    }
    public interface IMainModelCallBack2{
        void getError(String error);
        void getNineSuccess(SyNine syNine);
    }
    public interface IMainModelCallBack3{
        void getError(String error);
        void getListSuccess(GoodsBean goodsBean);
    }
}
