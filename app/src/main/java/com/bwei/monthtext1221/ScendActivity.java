package com.bwei.monthtext1221;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import adapter.ListAdapter;
import adapter.SyNineAdapter;
import app.MyApp;
import beans.GoodsBean;
import beans.SyNine;
import beans.SyPic;
import model.MainModel;
import presenter.MainPresenter;
import view.MainView;

import static app.MyApp.getContext;

public class ScendActivity extends AppCompatActivity implements View.OnClickListener, MainView {
    private static final String TAG = "ScendActivity+++++++++";
    /**
     * 扫一扫
     */
    private Button scend_sys;
    private ImageView scend_menu2;
    private RecyclerView scend_recycler1;
    private RecyclerView scend_recycler2;
    private MainPresenter mainPresenter=new MainPresenter(this);
    int type=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scend);
        initView();
        //发送请求
        //九宫格
        mainPresenter.getSyNine();
        //list集合
        mainPresenter.getList();
        //切换视图
        scend_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type==2){
                    scend_menu2.setImageResource(R.drawable.menu1);
                    StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    scend_recycler2.setLayoutManager(manager);
                    type=1;
                }else{
                    scend_menu2.setImageResource(R.drawable.menu2);
                    LinearLayoutManager manager = new LinearLayoutManager(MyApp.getContext());
                    scend_recycler2.setLayoutManager(manager);
                    type=2;
                }
            }
        });
    }
    //初始化数据
    private void initView() {
        scend_sys = findViewById(R.id.scend_sys);
        scend_sys.setOnClickListener(this);
        scend_menu2 = findViewById(R.id.scend_menu2);
        scend_recycler1 = findViewById(R.id.scend_recycler1);
        scend_recycler2 = findViewById(R.id.scend_recycler2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.scend_sys://扫一扫监听
                /*if(ActivityCompat.checkSelfPermission(MyApp.getContext(), Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                    IntentIntegrator integrator = new IntentIntegrator(ScendActivity.this);
                    integrator.initiateScan();
                }else{
                    ActivityCompat.requestPermissions(ScendActivity.this,new String[]{Manifest.permission.CAMERA},100);
                }*/

                break;
        }
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(ScendActivity.this,"权限打开",Toast.LENGTH_SHORT).show();
            }else{
                finish();
            }
        }
    }*/

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            String s = result.getContents();
            Toast.makeText(ScendActivity.this,s+"",Toast.LENGTH_SHORT).show();
        }

    }*/

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void onSuccess(SyPic syPic) {

    }
    //九宫格数据回调
    @Override
    public void onNineSuccess(SyNine syNine) {
        //Toast.makeText(this,"成功",Toast.LENGTH_SHORT).show();
        List<SyNine.DataBean> list = syNine.getData();
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this,5);
        scend_recycler1.setLayoutManager(linearLayoutManager);
        SyNineAdapter syNineAdapter=new SyNineAdapter(list,ScendActivity.this);
        scend_recycler1.setAdapter(syNineAdapter);

    }
    //集合回调
    @Override
    public void onListSuccess(GoodsBean goodsBean) {
        /*Toast.makeText(this,goodsBean.toString()+"成功",Toast.LENGTH_SHORT).show();*/
        List<GoodsBean.DataBean> list = goodsBean.getData();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        scend_recycler2.setLayoutManager(manager);
        Log.d(TAG, "onListSuccess: +++++++"+list.toString());
        //设置适配器
        ListAdapter listAdapter=new ListAdapter(list,ScendActivity.this);
        scend_recycler2.setAdapter(listAdapter);
        listAdapter.setOnItemListener2(new ListAdapter.ItemListener() {
            @Override
            public void success(String data) {
                Toast.makeText(MyApp.getContext(),data+"",Toast.LENGTH_SHORT).show();
            }
        });

    }
    //失败吐司
    @Override
    public void onFailed(String error) {
        Toast.makeText(this,"失败",Toast.LENGTH_SHORT).show();
    }
    //内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.ondestory();
    }
}
