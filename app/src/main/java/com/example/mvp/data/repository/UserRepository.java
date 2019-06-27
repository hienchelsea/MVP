package com.example.mvp.data.repository;

import com.example.mvp.data.datasource.UserDatasource;
import com.example.mvp.data.datasource.UserRemoteDatasource;
import com.example.mvp.data.model.Page;
import com.example.mvp.data.model.User;
import com.example.mvp.ui.main_activity.RepositoryMainCallBack;
import com.example.mvp.ui.user_activity.RepositoryUserCallBack;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class UserRepository implements UserDatasource.Remote, UserCallBack {

    private static UserRepository sInstance;
    private UserRemoteDatasource mUserRemoteDatasource;
    RepositoryMainCallBack mRepositoryMainCallBack;
    RepositoryUserCallBack mRepositoryUserCallBack;

    public static UserRepository getInstance() {
        if (sInstance == null)
            sInstance = new UserRepository();
        return sInstance;
    }

    private UserRepository() {
        mUserRemoteDatasource = UserRemoteDatasource.getInstance();
        mUserRemoteDatasource.setUserCallBack(this);
    }

    @Override
    public String getListUser() {
        try {
            mUserRemoteDatasource.getListUser();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String findUserById(int id) {
        try {
            mUserRemoteDatasource.findUserById(id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertUser(User user) {
        mUserRemoteDatasource.insertUser(user);
    }

    @Override
    public void deleteUser(int id) {
        mUserRemoteDatasource.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        mUserRemoteDatasource.updateUser(user);
    }

    @Override
    public void onPreExcute() {
        if (mRepositoryUserCallBack != null)
            mRepositoryUserCallBack.onPreExcute();
        else
            mRepositoryMainCallBack.onPreExcute();

    }

    @Override
    public void onExcuteSuccess() {
        if (mRepositoryMainCallBack != null)
            mRepositoryMainCallBack.onExcuteSuccess();
        else
            mRepositoryUserCallBack.onExcuteSuccess();
    }

    @Override
    public void updateData(String data) {
        if (mRepositoryMainCallBack != null) {
            Page page = new Gson().fromJson(data, Page.class);
            mRepositoryMainCallBack.updateData((ArrayList<User>) page.getData());
        } else {
            User user = new Gson().fromJson(data, User.class);
            mRepositoryUserCallBack.updateData(user);
        }
    }

    public void setmRepositoryMainCallBack(RepositoryMainCallBack callBack) {
        this.mRepositoryMainCallBack = callBack;
        this.mRepositoryUserCallBack = null;
    }

    public void setmRepositoryUserCallBack(RepositoryUserCallBack callBack) {
        this.mRepositoryUserCallBack = callBack;
        this.mRepositoryMainCallBack = null;
    }
}

