package com.bwei.monthtext1221;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.SyPicAdapter;
import app.MyApp;
import beans.GoodsBean;
import beans.SyNine;
import beans.SyPic;
import presenter.MainPresenter;
import view.MainView;

public class MainActivity extends AppCompatActivity implements MainView{

    private ViewPager main_view_pager;
    private Button main_btn;
    private MainPresenter mainPresenter=new MainPresenter(this);
    private List<String> listPic=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mainPresenter.getPic();
        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScendActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        main_view_pager = findViewById(R.id.main_view_pager);
        main_btn=findViewById(R.id.main_btn);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void onSuccess(SyPic syPic) {
        Toast.makeText(this,syPic.toString()+"成功",Toast.LENGTH_SHORT).show();
        List<SyPic.DataBean> list = syPic.getData();
        for (int i = 0; i <list.size() ; i++) {
            listPic.add(list.get(i).getIcon());
        }
        SyPicAdapter adapter=new SyPicAdapter(listPic, MyApp.getContext());
        main_view_pager.setAdapter(adapter);
    }

    @Override
    public void onNineSuccess(SyNine syNine) {

    }

    @Override
    public void onListSuccess(GoodsBean goodsBean) {

    }

    @Override
    public void onFailed(String error) {

    }
}
