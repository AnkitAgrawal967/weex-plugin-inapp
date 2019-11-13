package org.weex.plugin.weexplugininapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import org.json.JSONException;
import org.json.JSONObject;
import org.weex.plugin.weexplugininapp.billing.BillingHandler;

import java.util.HashMap;
import java.util.Map;

@WeexModule(name = "weexPluginInapp")
public class WeexPluginInappModule extends WXModule {

    private static final String TAG = "WeexPluginInappModule";

    // Define the callback
    private JSCallback jsCallback;
    private Activity thisActivity;
    private String productId;

    // Inapp repsonse map
    private Map<String, Object> response = new HashMap();

    //sync ret example
    //TODO: Auto-generated method example
    @JSMethod(uiThread = true)
    public String syncRet(String param) {
        return param;
    }

    //async ret example
    //TODO: Auto-generated method example
    @JSMethod(uiThread = true)
    public void asyncRet(String param, JSCallback callback) {
        callback.invoke(param);
    }


    private String getDataByKey(String params, String key) {
        String data;

        JSONObject json = new JSONObject();
        try {
            json = new JSONObject(params);
            data = (String) json.get(key);
        } catch (Throwable t) {
            data = null;
            Log.e("-> show", "Could not parse malformed JSON: \"" + json + "\"");
        }

        return data;
    }

    @JSMethod(uiThread = true)
    public void show(String params, String key) throws JSONException {
        Log.d(TAG, "-> Showing!!!");
        String data = this.getDataByKey(params, key);
        if (data != null) {
            // TODO
        } else {
            Toast.makeText(mWXSDKInstance.getContext(), data, Toast.LENGTH_SHORT).show();
        }
    }

    @JSMethod(uiThread = true)
    public void buy(String productId, JSCallback jsCallback) {
        Log.d(TAG, "-> buy " + productId);

        Toast.makeText(mWXSDKInstance.getContext(),
                "Buy ", Toast.LENGTH_SHORT
        ).show();

        this.jsCallback = jsCallback;
        this.response.put("productId", productId);
        this.thisActivity = ((Activity) mWXSDKInstance.getContext());
        this.productId = productId;

        Log.d("productId", this.response.toString());

        this.doPurchase();
    }

    @JSMethod(uiThread = true)
    public void subscribe(String productId, JSCallback jsCallback) {
        Log.d(TAG, "-> Subscribe " + productId);

        Toast.makeText(mWXSDKInstance.getContext(),
                "subscribe ", Toast.LENGTH_SHORT
        ).show();

        this.jsCallback = jsCallback;
        this.thisActivity = ((Activity) mWXSDKInstance.getContext());

        // this.doPurchase();
    }

    @JSMethod(uiThread = true)
    public void manageSubscriptions() {
        Log.d(TAG, "-> manageSubscriptions");

        Toast.makeText(mWXSDKInstance.getContext(),
                "manageSubscriptions", Toast.LENGTH_SHORT
        ).show();

        this.thisActivity = ((Activity) mWXSDKInstance.getContext());
        Uri webpage = Uri.parse("http://play.google.com/store/account/subscriptions");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        this.thisActivity.startActivity(intent);
    }

    @JSMethod(uiThread = true)
    public void getProductInfo(String params, String key, JSCallback jsCallback) {
        Log.d(TAG, "-> getProductInfo");
        String data = this.getDataByKey(params, key);

        if (data == null) {
            jsCallback.invoke(null);
        } else {
            // params.list is an array with all product id, iterate getting information about each product
            // and return all at once in the callback
            // JSONArray list = json.getJSONArray("list");

            Toast.makeText(mWXSDKInstance.getContext(),
                    "getProductInfo ", Toast.LENGTH_SHORT
            ).show();

            this.jsCallback = jsCallback;
            this.thisActivity = ((Activity) mWXSDKInstance.getContext());
            // jsCallback('getProductInfo');
            // this.doPurchase();
        }
    }

    private void doPurchase() {
        BillingHandler bh = new BillingHandler(thisActivity, productId, this.jsCallback);
        bh.init();
    }
}