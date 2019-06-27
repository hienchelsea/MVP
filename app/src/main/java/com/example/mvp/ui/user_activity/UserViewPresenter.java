package com.example.mvp.ui.user_activity;

import com.example.mvp.data.model.User;
import com.example.mvp.data.datasource.UserRemoteDatasource;
import com.example.mvp.data.repository.UserRepository;

public class UserViewPresenter implements UserViewContract.Presenter, RepositoryUserCallBack {
    UserRemoteDatasource mUserModel = UserRemoteDatasource.getInstance();
    UserViewContract.View mUserViewCallBack;
    private static UserViewPresenter userPresenter;
    private UserRepository mUserRepository;

    public UserViewPresenter(UserRepository userRepository, UserViewContract.View mUserViewCallBack) {
        this.mUserViewCallBack = mUserViewCallBack;
        this.mUserRepository = userRepository;
        mUserRepository.setmRepositoryUserCallBack(this);
    }

    @Override
    public void getUserById(int id) {
        mUserRepository.findUserById(id);
    }

    @Override
    public void onPreExcute() {
        mUserViewCallBack.showProgress();
    }

    @Override
    public void onExcuteSuccess() {
        mUserViewCallBack.unShowProgress();
    }

    @Override
    public void updateData(User data) {
        mUserViewCallBack.updateUser(data);
    }

}

