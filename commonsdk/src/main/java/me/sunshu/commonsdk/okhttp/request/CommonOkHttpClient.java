package me.sunshu.commonsdk.okhttp.request;

import android.os.Build;

import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;

import me.sunshu.commonsdk.okhttp.https.HttpsUtils;
import me.sunshu.commonsdk.okhttp.listener.CommonJsonCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 编写人： sunshu
 * 功能描述：请求的发送，请求参数的配置，https支持
 */

public class CommonOkHttpClient {

    private static final int TIME_OUT = 30; // 超时参数
    private static OkHttpClient mOkHttpClient;

    //为client 配置参数

    static{
        // 创建client 对象
         OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
         okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
         okHttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
         okHttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);

        // 允许重定向
        okHttpBuilder.followRedirects(true);
        // https 支持
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        // 过期
        okHttpBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory());
        // 生成client 对象
        mOkHttpClient = okHttpBuilder.build();
    }


    /**
     * 发送具体的http / https 请求
     * @param request
     * @param commonCallback
     * @return
     */
    public static Call sendRequest(Request request, CommonJsonCallback commonCallback){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commonCallback);
        return  call;
    }



}
