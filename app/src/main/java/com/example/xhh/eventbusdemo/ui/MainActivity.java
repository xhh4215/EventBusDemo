package com.example.xhh.eventbusdemo.ui;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xhh.eventbusdemo.R;
import com.example.xhh.eventbusdemo.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.buttonid01);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.buttonid02);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.buttonid03);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.buttonid04);
        button4.setOnClickListener(this);
        button5 = (Button) findViewById(R.id.buttonid05);
        button5.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textviewid);
//        注册eventBus
        EventBus.getDefault().register(this);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageReceive(MessageEvent event) {
        textView.setText(event.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageReceive2(MessageEvent event) {
        textView.setText(event.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onMessageReceive3(MessageEvent event) {
        textView.setText(event.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onMessageReceive4(MessageEvent event) {
        textView.setText(event.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageReceive5(MessageEvent event) {
        textView.setText(event.getMessage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonid01:
                Toast.makeText(this, "运行在主的线程", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new MessageEvent("EventBus的练习 当前的事件运行在主线程"));
                break;
            case R.id.buttonid02:
                Toast.makeText(this, "运行在当前线程", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new MessageEvent("EventBus的练习 当前的事件运行在当前线程"));
                break;
            case R.id.buttonid03:
                Toast.makeText(this, "有序的运行在主线程", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new MessageEvent("EventBus的练习 不再主线程发送运行在主线程，在祝线程发送不运行在祝线程"));
                break;
            case R.id.buttonid04:
                Toast.makeText(this, "异步线程", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new MessageEvent("EventBus的练习 当前的事件运行在异步线程"));
                break;
            case R.id.buttonid05:
                Toast.makeText(this, "运行在后台线程", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new MessageEvent("EventBus的练习 当前的事件运行在后台线程"));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        取消eventBus的注册
        EventBus.getDefault().unregister(this);
    }
}
