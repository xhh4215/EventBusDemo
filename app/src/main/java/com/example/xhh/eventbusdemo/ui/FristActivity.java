package com.example.xhh.eventbusdemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xhh.eventbusdemo.R;
import com.example.xhh.eventbusdemo.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 粘性事件的使用
 * 粘性事件的意义在于事件可以发布但是我不是立马就获取事件发送的消息
 * 可以在发送的数据做过一定的处理之后通过订阅之后处理事件
 */

public class FristActivity extends AppCompatActivity {
    private Button button;
    private Button button2;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist2);
        button = (Button) findViewById(R.id.buttonid);
        button2 = (Button) findViewById(R.id.buttonid2);
        textView = (TextView) findViewById(R.id.textviewid);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(FristActivity.this,SecondActivity.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().register(FristActivity.this);
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void ononMoonStickyEvent(MessageEvent event) {
        textView.setText(event.getMessage());
    }
}
