package com.example.host;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demolib.LibTest;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.containers.BoostFlutterActivity;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static WeakReference<MainActivity> sRef;

    private TextView mOpenNative;
    private TextView mOpenFlutter;
    private TextView mOpenFlutterFragment;

    //Flutter向Native发消息
    private static final String CHANNEL_NATIVE = "com.example.flutter/native";
//    FlutterEngine flutterEngine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LibTest.test();

        sRef = new WeakReference<>(this);

        setContentView(R.layout.activity_main);

        mOpenNative = findViewById(R.id.open_native);
        mOpenFlutter = findViewById(R.id.open_flutter);
        mOpenFlutterFragment = findViewById(R.id.open_flutter_fragment);

        mOpenNative.setOnClickListener(this);
        mOpenFlutter.setOnClickListener(this);
        mOpenFlutterFragment.setOnClickListener(this);

        ChannelDispacher.register();

//        flutterEngine = new FlutterEngine(this);
//        //flutter调用Android，channel定义
//        MethodChannel nativeChannel = new MethodChannel(FlutterBoost.instance().engineProvider().getDartExecutor(), CHANNEL_NATIVE);
//        nativeChannel.setMethodCallHandler(new MethodChannel.MethodCallHandler() {
//            @Override
//            public void onMethodCall(MethodCall call, MethodChannel.Result result) {
//                switch (call.method){
//                    case "test":
//                        System.out.println("12345");
//                        result.success("成功打开第二个原生页面");
//                        break;
//                    default :
//                        result.notImplemented();
//                        break;
//
//                }
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sRef.clear();
        sRef = null;
    }

    @Override
    public void onClick(View v) {
        Map params = new HashMap();
        params.put("test1","v_test1");
        params.put("test2","v_test2");
        //Add some params if needed.
        if (v == mOpenNative) {
            PageRouter.openPageByUrl(this, PageRouter.NATIVE_PAGE_URL, params);
        } else if (v == mOpenFlutter) {
            PageRouter.openPageByUrl(this, PageRouter.FLUTTER_PAGE_URL, params);
        } else if (v == mOpenFlutterFragment) {
            PageRouter.openPageByUrl(this, PageRouter.FLUTTER_FRAGMENT_PAGE_URL,params);
        }
    }
}
