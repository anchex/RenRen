package com.renren.api.client;

import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.renren.api.client.utils.HttpURLUtils;
import com.renren.api.client.utils.Md5Utils;

/**
 * @author 李勇 2011-02-17
 */
public abstract class AbstractServiceTest {

    private RenrenApiClient client;

    static {
        //TODO 配置apiKey和apiSecret
        RenrenApiConfig.renrenApiKey = null;
        RenrenApiConfig.renrenApiSecret = null;
    }

    public AbstractServiceTest() {
        this.init();
    }

    protected void init() {
        String sessionKey = this.getSessionKey(RenrenApiConfig.renrenApiKey,
                RenrenApiConfig.renrenApiSecret);
        this.client = new RenrenApiClient(sessionKey);
    }

    private String getSessionKey(String apiKey, String apiSecret) {
        //TODO 用OAuth2.0获取sessionKey
        return null;
    }

    protected RenrenApiClient getRenrenApiClient() {
        return this.client;
    }
}
