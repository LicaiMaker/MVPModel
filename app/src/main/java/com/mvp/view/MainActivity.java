package com.mvp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mvp.presenter.SplashPresenter;
import com.summer.mvpmodel.R;

public class MainActivity extends AppCompatActivity implements ISplashView {

    SplashPresenter presenter;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new SplashPresenter( this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.didFinishLoading(this);
    }

    @Override
    public void showProcessBar() {
        if ( progressBar == null) {
            progressBar = new ProgressDialog( this);
            progressBar.setCancelable( true);
            progressBar.setCanceledOnTouchOutside( true);
            progressBar.setMessage( "更新数据中，请稍后" );
        }
        progressBar.show();
    }

    @Override
    public void hideProcessBar() {
        progressBar.hide();
    }

    @Override
    public void showNetError() {
        Toast. makeText(this, "暂无网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startNextActivity() {
        Toast. makeText(this, "跳到下个activity", Toast.LENGTH_SHORT).show();
    }
}
