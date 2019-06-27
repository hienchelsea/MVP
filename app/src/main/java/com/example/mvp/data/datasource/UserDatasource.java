package com.example.mvp.data.datasource;

import com.example.mvp.data.model.User;

import java.net.MalformedURLException;

public interface UserDatasource {
    interface Remote {
        String getListUser() throws MalformedURLException;

        String findUserById(int id) throws MalformedURLException;

        void insertUser(User user);

        void deleteUser(int id);

        void updateUser(User user);
    }
    interface Local{
    }
}
