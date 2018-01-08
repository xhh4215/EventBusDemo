package com.example.xhh.eventbusdemo.util;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by xhh on 2018/1/3.
 */

public class EventHelper {
    public void initEventBus() {
        EventBus.getDefault().register(MyApplication.getContext());
    }

    public void finishEventBus() {
        EventBus.getDefault().unregister(MyApplication.getContext());
    }

    public void postEventBus(Object object) {
        EventBus.getDefault().post(object);
    }
}
