package me.sunshu.commonsdk.okhttp.listener;

import java.util.Objects;

/**
 * 创建时间：  17-7-4.
 * 编写人： sunshu
 * 功能描述：
 */

public interface DisposeDataListener {

    /**
     * 请求成功返回对象
     * @param responseObj：请求成功返回对象
     */
    void onSuccess(Object responseObj);

    /**
     * 请求失败返回对象
     * @param reasonObj：请求失败返回对象
     */

    void onFailure(Object reasonObj);
}
