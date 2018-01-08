package com.example.xhh.eventbusdemo.event;

/**
 * Created by xhh on 2018/1/8.
 * 定义发送的事件源
 */

public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
