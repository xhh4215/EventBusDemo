package com.example.xhh.eventbusdemo.ui;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.xhh.eventbusdemo.R;
import com.example.xhh.eventbusdemo.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * EventBus的事件的优先级的学习
 * EventBus的事件的优先级的意义在于高优先级的事件能接收更低优先级的其他用户
 */
public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {
     private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        button = (Button) findViewById(R.id.button01);
        button.setOnClickListener(this);
        EventBus.getDefault().register(ThreadActivity.this);

    }
    @Subscribe(threadMode = ThreadMode.POSTING ,priority = 1)
    public void MessageDeal(MessageEvent messageEvent){
        Toast.makeText(this, "我的优先级是1"+messageEvent.getMessage(), Toast.LENGTH_SHORT).show();
//

    }
    @Subscribe(threadMode = ThreadMode.POSTING ,priority = 2)
    public void MessageDeal1(MessageEvent messageEvent){
//        取消事件的传递 通常是更高优先级的事件取消对低优先级的事件的订阅
        EventBus.getDefault().cancelEventDelivery(messageEvent);
        Toast.makeText(this, "我的优先级是2"+messageEvent.getMessage(), Toast.LENGTH_SHORT).show();
    }
    @Subscribe(threadMode = ThreadMode.POSTING ,priority = 3)
    public void MessageDeal2(MessageEvent messageEvent){
        EventBus.getDefault().cancelEventDelivery(messageEvent);
        Toast.makeText(this, "我的优先级是3"+messageEvent.getMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button01:
                //发送粘性事件
                EventBus.getDefault().postSticky(new MessageEvent("事件的优先级的测试"));
                break;
           }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(ThreadActivity.this);
    }
}
