package com.isysnext.medviewmd.medviewnewsocket;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;

public class ChatActivity extends AppCompatActivity  {
    private TextView toolbar_title, textViewTyping, textviewAlert;
    private Toolbar toolbar;
    private EditText editTextMessage;
    private ImageView imageViewEnter, imageBack;
    private RecyclerView recyclerViewChat;
   /* private ChatAdapter chatAdapter;
    private LinearLayoutManager recylerViewLayoutManager;
    private ArrayList<ChatBean> chatBeanList = new ArrayList<>();
    private ImageView imageViewDriver;
    private CountDownTimer countDownTimer;
    private String driverId = "",time = "", driverName = "", driverImage = "", orderId = "", from = "";
    boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
        clearChatNotification();
        getCurrentTime();
        getIntentDate();
        AppController.getInstance().getChatMessage(ChatActivity.this, this);
        if (Constants.isInternetOn(ChatActivity.this)) {
            getAllChatApi();
        } else {
            Constants.internetPopup(getResources().getString(R.string.alert), getResources().getString(R.string.no_internet), ChatActivity.this);
        }

        editTextMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() >= 1 || s.toString().trim().length() >= 1) {
                    imageViewEnter.setVisibility(View.VISIBLE);
                } else {
                    imageViewEnter.setVisibility(View.GONE);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AppController.getInstance().typeStatusChat(driverId);
            }

        });
    }

    private void getIntentDate(){
        Intent intent = getIntent();
        from = intent.getStringExtra(Constants.FROM);
        if(from.equals("LIVE_TRACKING")) {
            driverName = intent.getStringExtra(Constants.DRIVER_NAME);
            driverImage = intent.getStringExtra(Constants.DRIVER_IMAGE);
            orderId = intent.getStringExtra(Constants.ORDER_ID);
            driverId = intent.getStringExtra(Constants.DRIVER_ID);
            toolbar_title.setText(Constants.wordFirstCap(driverName));
            Glide.with(this).load(driverImage)
                    .crossFade()
                    .thumbnail(0.5f)
                    .placeholder(R.mipmap.user_icon).dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewDriver);
        }else if(from.equals("NOTIFICATION")){

            driverName = intent.getStringExtra(Constants.DRIVER_NAME);
            driverImage = intent.getStringExtra(Constants.PROFILE_IMAGE);
            orderId = intent.getStringExtra(Constants.ORDER_ID);
            driverId = intent.getStringExtra(Constants.FROM_ID);
            toolbar_title.setText(Constants.wordFirstCap(driverName));
            Glide.with(this).load(driverImage)
                    .crossFade()
                    .thumbnail(0.5f)
                    .placeholder(R.mipmap.user_icon).dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewDriver);
        }else if(from.equals("MANUAL_NOTIFICATION")){
             driverName = intent.getStringExtra(Constants.DRIVER_NAME);
             driverImage = intent.getStringExtra(Constants.PROFILE_IMAGE);
            orderId = intent.getStringExtra(Constants.ORDER_ID);
            driverId = intent.getStringExtra(Constants.FROM_ID);
            toolbar_title.setText(Constants.wordFirstCap(driverName));
            Glide.with(this).load(driverImage)
                    .crossFade()
                    .thumbnail(0.5f)
                    .placeholder(R.mipmap.user_icon).dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewDriver);
        }


    }

    *//**
     * This method is used to clear chat notification
     *//*
    private void clearChatNotification(){
        NotificationManager notifManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancel(2);

    }

    *//**
     * This method is used to get current time
     *//*
    private void getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = sdf.format(Calendar.getInstance().getTime());
        Log.d("CurrentTime", String.valueOf(time));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(!from.equals("LIVE_TRACKING")){
            startActivity(new Intent(ChatActivity.this, BaseDrawerActivity.class));
            finish();
        }
    }

    //Start Countodwn method
    private void startTimer(int noOfMinutes) {
        countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                // textViewWeAreLooking.setText(hms);//set text
            }

            public void onFinish() {
                textViewTyping.setVisibility(View.GONE);
                // textViewWeAreLooking.setText("TIME'S UP!!"); //On finish change timer text
                stopCountdown();
                countDownTimer = null;//set CountDownTimer to null
                // startTimer.setText(getString(R.string.start_timer));//Change button text
            }
        }.start();

    }
    //Stop Countdown method
    private void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }


    *//**
     * This method is used to initialize all views
     *//*
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        textViewTyping = (TextView) findViewById(R.id.textViewTyping);
        textviewAlert = (TextView) findViewById(R.id.textviewAlert);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        imageViewEnter = (ImageView) findViewById(R.id.imageViewEnter);
        imageBack  = (ImageView) findViewById(R.id.imageBack);
        imageViewDriver = (ImageView) findViewById(R.id.imageViewDriver);
        recyclerViewChat = (RecyclerView) findViewById(R.id.recyclerViewChat);
        recylerViewLayoutManager = new LinearLayoutManager(this);
        recyclerViewChat.setLayoutManager(recylerViewLayoutManager);
        recylerViewLayoutManager.setStackFromEnd(true);
        recylerViewLayoutManager.setReverseLayout(false);

        imageBack.setOnClickListener(this);
        imageViewEnter.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
                if(!from.equals("LIVE_TRACKING")){
                    startActivity(new Intent(ChatActivity.this, BaseDrawerActivity.class));
                    finish();
                }else {
                    finish();
                }
                break;
            case R.id.imageViewEnter:
                String fromId = SharedPreferences.getInstance(ChatActivity.this).getString(Constants.ID);
                String accessToken = SharedPreferences.getInstance(ChatActivity.this).getString(Constants.ACCESS_TOKEN);
                String chatStr = editTextMessage.getText().toString();
                AppController.getInstance().sendChat(orderId, fromId, driverId, chatStr, accessToken);

                editTextMessage.setText("");

                ChatBean chatBean = new ChatBean();
                chatBean.setOrderId(orderId);
                chatBean.setMessageStr(chatStr);
                chatBean.setMineMessage(true);
                chatBean.setTimeStr(time);
                chatBean.setFromId(fromId);
                chatBean.setToId(driverId);
                chatBeanList.add(chatBean);

                if (chatBeanList.size() == 0) {
                    recyclerViewChat.setVisibility(View.GONE);
                    textviewAlert.setVisibility(View.VISIBLE);

                }else{
                    recyclerViewChat.setVisibility(View.VISIBLE);
                    textviewAlert.setVisibility(View.GONE);
                }
               *//* chatAdapter = new ChatAdapter(ChatActivity.this, chatBeanList);
                recyclerViewChat.setAdapter(chatAdapter);*//*
                setList();
                recyclerViewChat.scrollToPosition(chatAdapter.getItemCount() - 1);
                break;
        }

    }

    *//**
     * This method is used to call chat api.
     *//*
    public void getAllChatApi() {
        String accessToken = SharedPreferences.getInstance(ChatActivity.this).getString(Constants.ACCESS_TOKEN);
        Api api = ApiFactory.getClient().create(Api.class);
        Call<ResponseBody> call = api.getAllChat(accessToken, orderId);
        if(isFirst) {
            Constants.showProgressDialog(ChatActivity.this, "");
        }
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Constants.hideProgressDialog();
                isFirst = false;
                String output = null;
                try {
                    if (response != null && response.isSuccessful() && response.code() == 200) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        output = sb.toString();
                        Log.e(Constants.TAG, "CHAT RESPONSE" + output);
                        hideLoader();
                        JSONObject object = new JSONObject(output);
                        if (object.optString("success").equalsIgnoreCase("true")) {
                            JSONObject jsonObject = object.getJSONObject("data");
                            JSONArray jsonArray = jsonObject.getJSONArray("chat");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObjectChat = jsonArray.getJSONObject(i);
                                ChatBean chatBean = new ChatBean();
                                chatBean.setMessageId(jsonObjectChat.optString("id"));
                                chatBean.setToId(jsonObjectChat.optString("to_id"));
                                chatBean.setFromId(jsonObjectChat.optString("from_id"));
                                chatBean.setMessageStr(jsonObjectChat.optString("message"));
                                chatBean.setTimeStr(jsonObjectChat.optString("updated_at"));
                                chatBean.setMineMessage(false);
                                chatBeanList.add(chatBean);
                            }
                           setList();
                            recyclerViewChat.scrollToPosition(chatAdapter.getItemCount() - 1);
                            chatAdapter.notifyDataSetChanged();
                            if (chatBeanList.size() == 0) {
                                recyclerViewChat.setVisibility(View.GONE);
                                textviewAlert.setVisibility(View.VISIBLE);

                            }
                        } else if (object.optString("success").equalsIgnoreCase("false")) {

                        }
                    } else if (response.code() == 400 || response.code() == 500 || response.code() == 404 || response.code() == 422) {
                        Constants.hideProgressDialog();
                        if(response.code() == 422) {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
                            StringBuilder sb = new StringBuilder();
                            String line = null;
                            while ((line = reader.readLine()) != null) {
                                sb.append(line + "\n");
                            }
                            output = sb.toString();
                            Log.e(Constants.TAG, "CHAT FALSE RESPONSE===>>>>>>>>" + output);
                            JSONObject object = new JSONObject(output);
                            if (!object.optBoolean("success")) {
                                JSONArray jsonArray = object.optJSONArray("error");
                                if (jsonArray == null) {

                                    Constants.showMessage(object.getJSONObject("error").getString("message"), ChatActivity.this);
                                } else {
                                    Constants.showMessage(jsonArray.getJSONObject(0).getString("message"), ChatActivity.this);

                                }
                            }
                        }
                    }else if (response.code() == 401) {
                        if (response.body() == null) {
                            Constants.AlertTokenExpirePopUp(getResources().getString(R.string.unable_to_connect), getResources().getString(R.string.your_login_session_is_expired), ChatActivity.this);
                        } else {
                            Constants.AlertTokenExpirePopUp(getResources().getString(R.string.unable_to_connect), getResources().getString(R.string.your_login_session_is_expired), ChatActivity.this);
                        }
                    }


                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                } catch (Exception e) {
                    Log.d(Constants.TAG, "Exception");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Constants.hideProgressDialog();
            }
        });
    }

    @Override
    public void getChatResponse(JSONObject jsonObject) {
        Log.e(Constants.TAG, "CHAT RESPONSE" + jsonObject);
        ChatBean chatBean = new ChatBean();
        chatBean.setMessageStr(jsonObject.optString("message"));
        chatBean.setOrderId(jsonObject.optString("order_id"));
        chatBean.setTimeStr(jsonObject.optString("updated_at"));
        chatBean.setFromId( jsonObject.optString("from_id"));
        chatBean.setToId(jsonObject.optString("to_id"));
        chatBean.setMessageId(jsonObject.optString("id"));
        chatBean.setMineMessage(false);
        chatBeanList.add(chatBean);
        if (chatBeanList.size() == 0) {
            recyclerViewChat.setVisibility(View.GONE);
            textviewAlert.setVisibility(View.VISIBLE);

        }else{
            recyclerViewChat.setVisibility(View.VISIBLE);
            textviewAlert.setVisibility(View.GONE);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               *//* if (Constants.isInternetOn(ChatActivity.this)) {
                    getAllChatApi();
                } else {
                    Constants.internetPopup(getResources().getString(R.string.alert), getResources().getString(R.string.no_internet), ChatActivity.this);
                }*//*
                setList();
                recyclerViewChat.scrollToPosition(chatAdapter.getItemCount() - 1);
            }
        });


    }

    @Override
    public void getTypeStatus(JSONObject jsonObject) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewTyping.setVisibility(View.VISIBLE);
                try {
                    startTimer(2000);

                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    public void isConnected(boolean isConnected) {

    }


    private void setList() {
        try {
            if (chatAdapter == null) {
                chatAdapter = new ChatAdapter(ChatActivity.this, chatBeanList);
                recyclerViewChat.setAdapter(chatAdapter);

            } else {
                chatAdapter.notifyDataSetChanged();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
