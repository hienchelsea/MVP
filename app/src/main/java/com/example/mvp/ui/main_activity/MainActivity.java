package com.example.mvp.ui.main_activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp.data.repository.UserRepository;
import com.example.mvp.ui.main_activity.adapter.UserAdapter;
import com.example.mvp.utils.Constants;
import com.example.mvp.data.model.User;
import com.example.mvp.R;
import com.example.mvp.ui.user_activity.UserActivity;
import com.example.vando.mvp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.RecyclerViewCallBack, MainActivityContract.View {

    private static final int MESSAGE_DEMO = 123;
    private boolean isInitView = false;
    private RecyclerView reView;
    private TextView mTxtDemo;
    private ProgressBar mProgressbar;
    ArrayList<User> mList;
    UserAdapter mAdapter;
    MainViewPresenter mMainViewPresenter;
    private static final int MESAGE_EMPTY=4;
    private UserRepository mUserRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrequestPermission();
        mInitView();
        mUserRepository= UserRepository.getInstance();
        mMainViewPresenter= new MainViewPresenter(mUserRepository, this);
        mMainViewPresenter.getListUser();
    }

    private void mrequestPermission() {
        String[] per = new String[]{Manifest.permission.INTERNET};
        ActivityCompat.requestPermissions(this, per, Constants.REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Constants.REQUEST_CODE
                && ContextCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            if (!isInitView) {
                mInitView();
            }
        }
    }

    private void mInitView() {
        mInitControl();
        mInitEvent();
        isInitView = true;
    }

    private void mInitEvent() {

    }

    private void mInitControl() {
        reView = findViewById(R.id.re_view);
        mTxtDemo = findViewById(R.id.txt_demo);
        mProgressbar = findViewById(R.id.progess_bar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reView.setLayoutManager(layoutManager);

    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        intent.putExtra(Constants.STRING_KEY_INTENT, mList.get(position).getId());
        startActivity(intent);
    }


    @Override
    public void updateList(List<User> list) {
        this.mList = (ArrayList<User>) list;
        if (mList == null)
            Toast.makeText(this, R.string.list_users_empty, Toast.LENGTH_SHORT).show();
        else {
            mAdapter = new UserAdapter(mList, this);
            reView.setAdapter(mAdapter);
        }
    }

    @Override
    public void showProgress() {
        mProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void unShowProgress() {
        mProgressbar.setVisibility(View.GONE);
    }

}
