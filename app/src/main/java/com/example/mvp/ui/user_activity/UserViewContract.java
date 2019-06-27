package com.example.mvp.ui.user_activity;

import com.example.mvp.data.model.User;

public interface UserViewContract {
    interface Presenter {
        void getUserById(int id);
    }
    public interface View {
        void updateView(User user);

        void unShowProgress();

        void showProgress();

        void updateUser(User user);
    }
}
