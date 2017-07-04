package me.sunshu.commonsdk;

import android.app.Activity;
import android.os.Bundle;

import me.sunshu.commonsdk.okhttp.listener.CommonJsonCallback;
import me.sunshu.commonsdk.okhttp.listener.DisposeDataListener;
import me.sunshu.commonsdk.okhttp.request.CommonOkHttpClient;
import me.sunshu.commonsdk.okhttp.request.CommonRequest;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonOkHttpClient.sendRequest(CommonRequest.createGetRequest("",null),new CommonJsonCallback(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        }));
    }
}
