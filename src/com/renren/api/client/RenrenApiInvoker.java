package com.renren.api.client;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.renren.api.client.services.RenrenApiException;
import com.renren.api.client.utils.HttpURLUtils;
import com.renren.api.client.utils.Md5Utils;

/**
 * 
 * @author Administrator
 * 
 */
public final class RenrenApiInvoker {

    public static final String FORMAT_XML = "XML";

    public static final String FORMAT_JSON = "JSON";

    private String sessionKey;

    public RenrenApiInvoker(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public String sendPostRestRequest(TreeMap<String, String> params) {
        if (this.sessionKey != null && this.sessionKey.length() > 0) {
            params.put("session_key", this.sessionKey);
        }
        return sendPostRestRequest(params, FORMAT_JSON);
    }

    public String sendPostRestRequest(TreeMap<String, String> params, String format) {
        return sendPostRestRequest(params, format, RenrenApiConfig.renrenApiUrl);
    }

    public String sendPostRestRequest(TreeMap<String, String> params, String format, String url) {
        prepareParams(params, format);
        String content = HttpURLUtils.doPost(url, params);
        if (content.indexOf("error_code") >= 0) {
            throw this.parseRenrenApiException(content);
        }
        return content;
    }

    /**
     * 对参数进行sig
     * 
     * @param params
     * @param format
     * @return
     */
    public static TreeMap<String, String> prepareParams(TreeMap<String, String> params,
            String format) {
        params.put("api_key", RenrenApiConfig.renrenApiKey);
        params.put("v", RenrenApiConfig.renrenApiVersion);
        params.put("call_id", String.valueOf(System.currentTimeMillis()));
        params.put("format", format);

        return sigParams(params);
    }

    public static TreeMap<String, String> sigParams(TreeMap<String, String> params) {
        StringBuffer sb = new StringBuffer();
        for (Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator(); iterator
                .hasNext();) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }
        sb.append(RenrenApiConfig.renrenApiSecret);
        params.put("sig", Md5Utils.md5(sb.toString()));
        return params;
    }

    private RenrenApiException parseRenrenApiException(String errorJson) {
        String errorFlag = "error_msg";
        int start = errorJson.indexOf(errorFlag);
        start = start + errorFlag.length() + 3;// ":"3个字符
        String errorMsg = errorJson.substring(start);
        int end = errorMsg.indexOf("\"");
        errorMsg = errorMsg.substring(0, end);

        errorFlag = "error_code";
        start = errorJson.indexOf(errorFlag);
        start = start + errorFlag.length() + 2;// ":2个字符
        String errorCode = errorJson.substring(start);
        end = errorCode.indexOf(",");
        errorCode = errorCode.substring(0, end).trim();
        RenrenApiException exception = new RenrenApiException(Integer.parseInt(errorCode), errorMsg);
        return exception;
    }
}
