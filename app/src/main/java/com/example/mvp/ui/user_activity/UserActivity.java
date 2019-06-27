package com.example.mvp.ui.user_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp.data.repository.UserRepository;
import com.example.mvp.utils.Constants;
import com.example.mvp.data.model.User;
import com.example.mvp.R;
import com.squareup.picasso.Picasso;

public class UserActivity extends AppCompatActivity implements UserViewContract.View {
    TextView mTxtId, mTxtFistName, mTxtLastName, mTxtEmail;
    ImageView mImgView;
    UserViewPresenter mUserPresenter;
    ProgressBar mProgressbar;
    LinearLayout mLayoutMaster;
    private UserRepository mUserRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        mUserRepository = UserRepository.getInstance();
        mUserPresenter = new UserViewPresenter(mUserRepository, this);
        Intent intent = getIntent();
        int id = intent.getIntExtra(Constants.STRING_KEY_INTENT, -1);
        initView();
        if (id >= 0)
            mUserPresenter.getUserById(id);


    }

    private void initView() {
        initControl();
        initEvent();
    }

    @Override
    public void updateView(User user) {
        Picasso.get().load(user.getAvatar())
                .centerCrop()
                .fit()
                .into(mImgView);
        mTxtId.setText(user.getId());
        mTxtFistName.setText(user.getFirstName());
        mTxtLastName.setText(user.getLastName());
        mTxtEmail.setText(user.getEmail());
    }

    @Override
    public void unShowProgress() {
        mProgressbar.setVisibility(View.GONE);
        mLayoutMaster.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        mProgressbar.setVisibility(View.VISIBLE);
        mLayoutMaster.setVisibility(View.INVISIBLE);

    }

    @Override
    public void updateUser(User user) {
        if (user != null) {
            mTxtEmail.setText(user.getEmail());
            mTxtLastName.setText(user.getLastName());
            mTxtFistName.setText(user.getFirstName());
            mTxtId.setText(user.getId() + "");
            Picasso.get().load(user.getAvatar() + "")
                    .centerCrop()
                    .fit()
                    .into(mImgView);
        } else {
            Toast.makeText(this, R.string., Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initEvent() {
    }

    private void initControl() {
        mImgView = findViewById(R.id.img_user);
        mTxtEmail = findViewById(R.id.txt_email);
        mTxtFistName = findViewById(R.id.txt_fist_name);
        mTxtLastName = findViewById(R.id.txt_last_name);
        mTxtId = findViewById(R.id.txt_id);
        mProgressbar = findViewById(R.id.progress_bar);
        mLayoutMaster = findViewById(R.id.layoutMaster);
    }
}


