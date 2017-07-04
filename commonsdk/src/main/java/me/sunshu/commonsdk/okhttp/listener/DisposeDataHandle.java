package me.sunshu.commonsdk.okhttp.listener;

import android.os.Handler;

/**
 * 创建时间：  17-7-4.
 * 编写人： sunshu
 * 功能描述： 对CommonJsonCallBack 预处理
 */

public class DisposeDataHandle  extends Handler{

    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;

    public DisposeDataHandle(DisposeDataListener mListener) {
        this.mListener = mListener;
    }

    public DisposeDataHandle(DisposeDataListener mListener, Class<?> mClass) {
        this.mListener = mListener;
        this.mClass = mClass;
    }
}
