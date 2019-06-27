package com.example.mvp.ui.main_activity;

import android.util.Log;

import com.example.mvp.data.datasource.UserRemoteDatasource;
import com.example.mvp.data.model.Page;
import com.example.mvp.data.model.User;
import com.example.mvp.data.repository.UserRepository;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainViewPresenter implements MainActivityContract.Presenter, RepositoryMainCallBack {


    MainActivityContract.View mMainCallback;
    UserRemoteDatasource mUserModel;
    private UserRepository mUserRepository;

    public MainViewPresenter(UserRepository mUserRepository, MainActivityContract.View mMainCallback) {
        this.mMainCallback = mMainCallback;
        this.mUserRepository = mUserRepository;
        mUserModel = UserRemoteDatasource.getInstance();
        mUserRepository.setmRepositoryMainCallBack(this);
    }

    @Override
    public void getListUser() {
        String mData = mUserRepository.getListUser();
    }

    @Override
    public void onPreExcute() {
        mMainCallback.showProgress();
    }

    @Override
    public void onExcuteSuccess() {
        mMainCallback.unShowProgress();
    }

    @Override
    public void updateData(ArrayList<User> data) {
        mMainCallback.updateList(data);
    }

}

