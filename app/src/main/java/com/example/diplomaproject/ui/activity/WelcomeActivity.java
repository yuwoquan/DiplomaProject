package com.example.diplomaproject.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.diplomaproject.MainActivity;
import com.example.diplomaproject.R;

import java.util.concurrent.TimeUnit;

import cn.bmob.v3.BmobUser;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
//                    @Override
                    public void accept(Long aLong) throws Exception {
                        BmobUser bmobUser = BmobUser.getCurrentUser(WelcomeActivity.this);
                        if(bmobUser != null){
            startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                        }else{
                            startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                        }
//
                        finish();
                    }
                });
    }

}
