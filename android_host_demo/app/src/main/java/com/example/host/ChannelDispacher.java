package com.example.host;

import com.idlefish.flutterboost.FlutterBoost;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class ChannelDispacher {
    private static final String CHANNEL_NATIVE = "com.example.flutter/native";


    public static void register() {
        MethodChannel nativeChannel = new MethodChannel(FlutterBoost.instance().engineProvider().getDartExecutor(), CHANNEL_NATIVE);
        nativeChannel.setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(MethodCall call, MethodChannel.Result result) {
                switch (call.method){
                    case "test":
                        System.out.println("12345omjhgbvd");
                        result.success("成功打开第二个原生页面");
                        break;
                    default :
                        result.notImplemented();
                        break;

                }
            }
        });
    }



}
