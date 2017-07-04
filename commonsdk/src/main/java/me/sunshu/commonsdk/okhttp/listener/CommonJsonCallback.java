package me.sunshu.commonsdk.okhttp.listener;

import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import me.sunshu.commonsdk.exception.OkHttpException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 创建时间：  17-7-4.
 * 编写人： sunshu
 * 功能描述：  处理Json 回调响应
 */

public class CommonJsonCallback implements Callback {

    protected final String RESULT_CODE = "ecode";

    protected final  int RESULT_CODE_VALUE = 0;

    protected final String ERROR_MSG = "emsg";

    protected final String EMPTY_MSG = "";

    /**
     * 自定义异常类型
     * 1. 网络不同
     * 2. json 返回的格式不对
     * 3. 网络位置错误
     */
    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;

    // 此处虽然
    protected Handler mDeliveryHandler; // 发送到主线程序中
    protected DisposeDataListener mListener;
    protected Class<?> mClass;

    public CommonJsonCallback(DisposeDataListener mListener){
        this.mListener = mListener;
    }


    /**
     * 失败处理
     * @param call
     * @param e
     */
    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR,e));
            }
        });
    }

    /**
     * 服务器响应
     * @param call
     * @param response
     * @throws IOException
     */
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().toString();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handlerResponse(result);
            }
        });
    }

    /**
     * 处理服务器返回数据
     */
    private void handlerResponse(Object response){
        if (response == null && TextUtils.isEmpty(response.toString().trim())){
            mListener.onFailure(new OkHttpException(NETWORK_ERROR,NETWORK_ERROR));
            return;
        }

        try {
            JSONObject result = new JSONObject(response.toString());
            if (result.has(RESULT_CODE)){   // 此处定义正确放回
                if (mClass == null){
                    // 没有Class 对象，不需要将json 对象转换为实体对象
                    mListener.onSuccess(response);
                }else{

                    // 需要转为实体对象 此处第一个参数需要是 string
                    Object object = new Gson().fromJson(result.toString(),mClass);
                    // 转换正确
                    if (object != null ){
                        mListener.onSuccess(object);
                    }else{
                        // 返回不合法的json
                        mListener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
                    }

                }

            }else{     // 此处错误返回 对应上面正确返回
                mListener.onFailure(new OkHttpException(OTHER_ERROR,result.get(RESULT_CODE)));
            }
        }catch (JSONException e){
            mListener.onFailure(new OkHttpException(OTHER_ERROR,e.getMessage()));
        }
    }

}
