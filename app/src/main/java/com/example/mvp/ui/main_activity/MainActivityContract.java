package com.example.mvp.ui.main_activity;

import com.example.mvp.data.model.User;

import java.util.List;

public interface MainActivityContract {
    interface Presenter {
        void getListUser();
    }
    public interface View {
        void updateList(List<User> list);
        void showProgress();
        void unShowProgress();
    }
}
