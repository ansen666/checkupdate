package com.ansen.checkupdate.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ansen.checkupdate.R;
import com.ansen.checkupdate.entity.CheckUpdate;
import com.ansen.checkupdate.utils.Utils;
import com.ansen.http.net.HTTPCaller;
import com.ansen.http.net.RequestDataCallback;

import io.github.lizhangqu.coreprogress.ProgressUIListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvCurrentVersionCode;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCurrentVersionCode= (TextView) findViewById(R.id.tv_current_version_code);
        tvCurrentVersionCode.setText("当前版本:"+ Utils.getVersionCode(this));

        findViewById(R.id.btn_check_update).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_check_update://检查更新
                HTTPCaller.getInstance().get(CheckUpdate.class,"http://139.196.35.30:8080/OkHttpTest/checkUpdate.do",null,requestDataCallback);
                break;
        }
    }

    private RequestDataCallback<CheckUpdate> requestDataCallback=new RequestDataCallback<CheckUpdate>(){
        @Override
        public void dataCallback(CheckUpdate obj) {
            if(obj!=null){
                if(obj.getErrorCode()==0){
                    showUpdaloadDialog(obj.getUrl());
                }else{
                    Toast.makeText(MainActivity.this,obj.getErrorReason(),Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    /**
     * 显示更新对话框
     * @param downloadUrl
     */
    private void showUpdaloadDialog(final String downloadUrl){
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setTitle("版本升级").
                setIcon(R.mipmap.ic_launcher). // 设置提示框的图标
                setMessage("发现新版本！请及时更新").// 设置要显示的信息
                setPositiveButton("确定", new DialogInterface.OnClickListener() {// 设置确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startUpload(downloadUrl);//下载最新的版本程序
                    }
                }).setNegativeButton("取消", null);//设置取消按钮,null是什么都不做，并关闭对话框
        AlertDialog alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();
    }

    /**
     * 开始下载
     * @param downloadUrl 下载url
     */
    private void startUpload(String downloadUrl){
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("正在下载新版本");
        progressDialog.setCancelable(false);//不能手动取消下载进度对话框

        final String fileSavePath=Utils.getSaveFilePath(downloadUrl);
        HTTPCaller.getInstance().downloadFile(downloadUrl,fileSavePath,null,new ProgressUIListener(){

            @Override
            public void onUIProgressStart(long totalBytes) {//下载开始
                progressDialog.setMax((int)totalBytes);
                progressDialog.show();
            }

            //更新进度
            @Override
            public void onUIProgressChanged(long numBytes, long totalBytes, float percent, float speed) {
                progressDialog.setProgress((int)numBytes);
            }

            @Override
            public void onUIProgressFinish() {//下载完成
                Toast.makeText(MainActivity.this,"下载完成",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                openAPK(fileSavePath);
            }
        });
    }

    /**
     * 下载完成安装apk
     * @param fileSavePath
     */
    private void openAPK(String fileSavePath){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://"+fileSavePath),"application/vnd.android.package-archive");
        startActivity(intent);
    }
}
