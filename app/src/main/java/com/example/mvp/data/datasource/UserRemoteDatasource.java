package com.example.mvp.data.datasource;

import android.os.AsyncTask;
import android.util.Log;

import com.example.vando.mvp.data.model.User;
import com.example.vando.mvp.data.repository.UserCallBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UserRemoteDatasource implements UserDatasource.Remote {
    private static UserRemoteDatasource sUserModel;
    private UserCallBack mCallBack;

    public static UserRemoteDatasource getInstance() {
        if (sUserModel == null) {
            sUserModel = new UserRemoteDatasource();
        }
        return sUserModel;
    }

    private UserRemoteDatasource() {
    }


    @Override
    public String getListUser() throws MalformedURLException {
        final URL url = new URL("https://reqres.in/api/users");
        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
                mCallBack.onPreExcute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                String data = "";
                try {
                    return getData(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "error";
                }
            }

            @Override
            protected void onPostExecute(String response) {

                if (response == null) {
                    response = "error";
                }
                mCallBack.updateData(response);
                Log.i("INFO", response);

                mCallBack.onExcuteSuccess();
            }
        }.execute();
        return "";
    }

    @Override
    public String findUserById(int id) throws MalformedURLException {
        final URL url = new URL("https://reqres.in/api/users/" + id);
        new AsyncTask<Integer, Integer, String>() {
            @Override
            protected void onPreExecute() {
                mCallBack.onPreExcute();
            }

            @Override
            protected String doInBackground(Integer... values) {
                try {
                    return getData(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "error";
                }
            }

            @Override
            protected void onPostExecute(String response) {
                if (!isCancelled())
                    this.cancel(true);
                mCallBack.onExcuteSuccess();
                if (response == null) {
                    response = "error";
                } else
                    response = response.substring(response.indexOf(":{") + 1, response.length() - 2);
                Log.i("INFO", response);
                mCallBack.updateData(response);
            }
        }.execute(id);
        return null;
    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public void updateUser(User user) {

    }

    public void setUserCallBack(UserCallBack callBack) {
        mCallBack = callBack;
    }

    private String getData(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        urlConnection.disconnect();
        return stringBuilder.toString();
    }
}
