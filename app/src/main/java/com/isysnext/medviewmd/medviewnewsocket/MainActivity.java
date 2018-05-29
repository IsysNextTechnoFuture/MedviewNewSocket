package com.isysnext.medviewmd.medviewnewsocket;

import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        AppController.getInstance().publicSocket();
/*
        PNConfiguration config = new PNConfiguration();
        config.setPublishKey(SyncStateContract.Constants.PUBNUB_PUBLISH_KEY);
        config.setSubscribeKey(SyncStateContract.Constants.PUBNUB_SUBSCRIBE_KEY);
        config.setUuid(this.mUsername);
        config.setSecure(true);
        this.mPubnub_DataStream = new PubNub(config);*/
    }
}
