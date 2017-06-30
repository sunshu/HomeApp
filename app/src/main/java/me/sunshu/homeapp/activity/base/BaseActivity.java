package me.sunshu.homeapp.activity.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * 创建时间：  17-6-29.
 * 编写人： sunshu
 * 功能描述：
 */

public class BaseActivity extends AppCompatActivity {
    /**
     * 输出日志 TAG
     */
    public String TAG;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        TAG = getComponentName().getShortClassName();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 申请制定权限
     */

    public  void requestPermission(int code, String... permissions){
        ActivityCompat.requestPermissions(this,permissions,code);
    }

    /**
     * 判断是否具有指定权限
     */
}
