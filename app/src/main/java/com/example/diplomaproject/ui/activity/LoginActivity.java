package com.example.diplomaproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diplomaproject.MainActivity;
import com.example.diplomaproject.MyApplication;
import com.example.diplomaproject.R;
import com.example.diplomaproject.enity.MyUser;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {
//    @BindView(R.id.name)
//    TextInputEditText iname;
//    @BindView(R.id.password)
//    TextInputEditText ipassword;
//    @BindView(R.id.yourname)
//    TextInputEditText yourname;
//    @BindView(R.id.yourpassword)
//    TextInputEditText yourpassword;
    private TextView tvSignupInvoker;
    private LinearLayout llSignup;
    private TextView tvSigninInvoker;
    private LinearLayout llSignin;
    private Button btnSignup;
    private Button btnSignin;
    private QMUITipDialog tipDialog;
    private TextInputEditText iname,ipassword,yourname,yourpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        tvSignupInvoker = (TextView) findViewById(R.id.tvSignupInvoker);
        tvSigninInvoker = (TextView) findViewById(R.id.tvSigninInvoker);
        iname=findViewById(R.id.name);
        ipassword=findViewById(R.id.password);
        yourname=findViewById(R.id.yourname);
        yourname=findViewById(R.id.yourpassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnSignin = (Button) findViewById(R.id.btnSignin);

        llSignup = (LinearLayout) findViewById(R.id.llSignup);
        llSignin = (LinearLayout) findViewById(R.id.llSignin);

        tvSignupInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignupForm();
            }
        });

        tvSigninInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSigninForm();
            }
        });
        showSigninForm();
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tip("注册中");
                init();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String cname = yourname.getText().toString();
                final String cpassword = yourpassword.getText().toString();
                Tip("登录中~");
                LoginIn(cname,cpassword);
            }
        });
    }
    private void init() {
//        BmobUser bUser = new BmobUser();
        MyUser myUser=new MyUser();
        final String name = iname.getText().toString();
        final String password = ipassword.getText().toString();
        myUser.setUsername(name);
        myUser.setPassword(password);
        myUser.setLocalName(name);
        myUser.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                tipDialog.dismiss();
                Tip("登录中~");
                LoginIn(name,password);
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                tipDialog.dismiss();
                showSnackbar();
            }
        });
    }
    private void LoginIn(String name,String psd){

        MyUser myUser=new MyUser();
        myUser.setUsername(name);
        myUser.setPassword(psd);
        myUser.login(MyApplication.getContext(), new SaveListener() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                tipDialog.dismiss();
                showSnackbar();
            }
        });
    }
    private void Tip(String text) {
        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(text)
                .create();
        tipDialog.show();
    }

    private void showSnackbar() {
        Toast.makeText(MyApplication.getContext(), "该用户名已存在", Toast.LENGTH_SHORT).show();
    }

    private void showSignupForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.15f;
        llSignin.requestLayout();
        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.85f;
        llSignup.requestLayout();
        tvSignupInvoker.setVisibility(View.GONE);
        tvSigninInvoker.setVisibility(View.VISIBLE);
        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right_to_left);
        llSignup.startAnimation(translate);
        Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right_to_left);
        btnSignup.startAnimation(clockwise);

    }

    private void showSigninForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.85f;
        llSignin.requestLayout();
        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.15f;
        llSignup.requestLayout();
        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left_to_right);
        llSignin.startAnimation(translate);
        tvSignupInvoker.setVisibility(View.VISIBLE);
        tvSigninInvoker.setVisibility(View.GONE);
        Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_left_to_right);
        btnSignin.startAnimation(clockwise);
    }
}
