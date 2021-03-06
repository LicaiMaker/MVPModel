package com.mvp.presenter;

import android.content.Context;

import com.mvp.model.INetConnect;
import com.mvp.model.impl.NetConnect;
import com.mvp.view.ISplashView;

/**
 * Created by LiCai on 2016/4/10.
 */
public class SplashPresenter {

    private INetConnect connect;
    private ISplashView iView;

    public SplashPresenter(ISplashView iView){
        this. iView = iView;
        connect = new NetConnect();
    }

    public void didFinishLoading(Context context){
        iView.showProcessBar();
        if( connect.isNetConnect(context)){
            iView.startNextActivity();
        } else{
            iView.showNetError();
        }
        iView.hideProcessBar();
    }
}
