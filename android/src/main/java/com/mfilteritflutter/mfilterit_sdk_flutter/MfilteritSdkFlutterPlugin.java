package com.mfilteritflutter.mfilterit_sdk_flutter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.mfilterit.MFilterIt;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * MfilteritSdkFlutterPlugin
 */
public class MfilteritSdkFlutterPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
    private MethodChannel channel;
    private Context context;



    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        Log.e("TAG", "onAttachedToEngine: ");
        context = flutterPluginBinding.getApplicationContext();
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "mfilterit_sdk_flutter");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        switch (call.method) {
            case "sdkInit":
                Log.e("TAG", "init from java");
                String vendorId = call.argument("vendorId");
                int extDataPoints = (Integer) call.argument("extDataPoints");
                Log.e("TAG", "GOT DATAPOINTS" + extDataPoints);
                Log.e("TAG", "GOT VENDOR_ID" + vendorId);
                MFilterIt.sdkInit(context, vendorId, extDataPoints);
                break;
            case "setApplicationData":
                Log.e("TAG", "application data from java" + call.arguments.toString());
                MFilterIt.setApplicationData(context, call.arguments.toString());
                break;
            case "sendEvent":
                Log.e("TAG", "event from java");
                String vendorId1 = call.argument("vendorId");
                String eventArray = call.argument("eventArray");
                String eventType = call.argument("eventType");
                MFilterIt.sendEvent(context, vendorId1, eventArray, eventType);
                break;
            default:
                result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        Log.e("TAG", "Got context");
        context = binding.getActivity().getApplicationContext();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        Log.e("TAG", "onDetachedFromActivityForConfigChanges: ");
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        Log.e("TAG", "onReattachedToActivityForConfigChanges: ");
    }

    @Override
    public void onDetachedFromActivity() {
        Log.e("TAG", "onDetachedFromActivity: ");
    }
}
