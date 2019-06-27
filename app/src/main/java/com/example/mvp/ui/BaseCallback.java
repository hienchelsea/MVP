package com.example.mvp.ui;

public interface BaseCallback<T> {
    void onPreExcute();
    void onExcuteSuccess();
    void updateData(T data);

}
