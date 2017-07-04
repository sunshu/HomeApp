package me.sunshu.commonsdk.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * 编写人： sunshu
 * 功能描述：接受请求参数，生成Request对象
 */

public class CommonRequest {

    /**
     * 创建一个Post Request
     *
     * @param url
     * @param params
     * @return 返回 post Request
     */
    public static Request createPostRequest(String url, RequestParams params) {
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                // 遍历请求参数,添加到请求构建类中
                mFormBodyBuild.add(entry.getKey(), entry.getValue());
            }
        } else {
            return null;
        }

        // 通过请求构建类的build方法获取到真正的请求体对象
        FormBody mFormBody = mFormBodyBuild.build();
        return new Request.Builder().url(url).post(mFormBody).build();

    }


    /**
     * 创建一个Get Request
     *
     * @param url
     * @param params
     * @return 返回get Request
     */
    public static Request createGetRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()
                    ) {
                urlBuilder.append(entry.getKey()).append("=")
                        .append(entry.getValue()).append("&");
            }
        }
        // 减去最后一个 & 符号
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).get().build();
    }


}
