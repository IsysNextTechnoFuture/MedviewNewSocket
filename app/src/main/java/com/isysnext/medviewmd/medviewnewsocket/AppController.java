package com.isysnext.medviewmd.medviewnewsocket;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import org.json.JSONObject;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import io.socket.client.IO;
import io.socket.emitter.Emitter;
/**
 * Created by Arun on 23-Nov-17.
 */

public class AppController extends Application {
    private static final String SOCKET_URL = "https://www.tele-presence.medviewconnect.com/";
    private static AppController instance;
    private String accessToken = "", orderIdStr = "", userIdStr = "";
    public io.socket.client.Socket mSocket;
    private static final String LOG_SOCKET = "AppController";
    Context mContext;
    private String runningActivityName = "";

    private void init(Application application) {
        instance = this;
    }

    public static synchronized AppController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
      //  initCalligraphy();

      /*  accessToken = SharedPreferances.getInstance(getApplicationContext()).getString(Constants.ACCESS_TOKEN);
        orderIdStr = SharedPreferances.getInstance(getApplicationContext()).getString(Constants.ORDER_ID);
         userIdStr = SharedPreferances.getInstance(mContext).getString(Constants.ID);
        if (!accessToken.equals("")) {
            publicSocket();
        }*/
    }

    /**
     * initiaize calligraphy fonts
     */
  /*  private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/QUICKSAND-LIGHT.TTF")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }*/


    public void publicSocket() {

        if(!orderIdStr.equals("")) {
            try {
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, null, null);
                IO.Options opts = new IO.Options();
                IO.setDefaultSSLContext(sc);
                opts.forceNew = false;
                opts.secure = true;
                opts.reconnection = true;
                mSocket = IO.socket(SOCKET_URL, opts);
                mSocket.connect();
                mSocket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        try {
                            Log.d(LOG_SOCKET, "socket connected");
                            JSONObject json = new JSONObject();
                            json.put("orderId", orderIdStr);
                            mSocket.emit("start_tracking", json);
                            onLocation.onConnected(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).on(io.socket.client.Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        try {
                            Log.d(LOG_SOCKET, "socket disconnected");
                            onLocation.onConnected(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).on(io.socket.client.Socket.EVENT_ERROR, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        try {
                            Log.d(LOG_SOCKET, "socket error");
                            publicSocket();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }).on(io.socket.client.Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        try {
                            Log.d(LOG_SOCKET, "socket connect time out error");
                            publicSocket();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }).on("new_location", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {

                        try {

                            Log.d(LOG_SOCKET, "socket update location" + args.toString());
                            // publicSocket();
                            JSONObject object = new JSONObject(args[0].toString());
                            Log.d(LOG_SOCKET, "socket update location" + object.toString());
                            onLocation.onGetLocation(object);

                        } catch (Exception e) {
                          e.printStackTrace();
                        }
                    }
                });

            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        }
    }

    public OnLocation onLocation;

    public  interface OnLocation {
        void onGetLocation(JSONObject jsonObject);
        void onConnected(boolean isConnected);

    }


    /* public void updateLocation(Location location, String orderId, String tackingStatus) {
        try {
            if (mSocket != null) {
                if (mSocket.connected() == true) {
                    JSONObject json = new JSONObject();
                    json.put("orderId", orderId);
                    json.put("latitude", location.getLatitude());
                    json.put("longitude", location.getLongitude());
                    json.put("status", tackingStatus);
                    mSocket.emit("update_location", json);
                }
            }
        } catch (Exception e) {

        }
    }*/

    public ChatListener chatListener;

    public void publicChatSocket() {
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, null, null);
            IO.Options opts = new IO.Options();
            IO.setDefaultSSLContext(sc);
            opts.forceNew = false;
            opts.secure = true;
            opts.reconnection = true;
            mSocket = IO.socket(SOCKET_URL, opts);
            mSocket.connect();
            mSocket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    try {
                        Log.d(LOG_SOCKET, "socket connected");
                        JSONObject json = new JSONObject();
                        json.put("userID", userIdStr);
                        mSocket.emit("register", json);
                        chatListener.isConnected(true);
                    } catch (Exception e) {
                    }
                }
            }).on(io.socket.client.Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    try {
                        Log.d(LOG_SOCKET, "socket disconnected");
                        chatListener.isConnected(false);
                    } catch (Exception e) {
                    }
                }
            }).on(io.socket.client.Socket.EVENT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    try {
                        Log.d(LOG_SOCKET, "socket error");
                        //  publicSocket();

                    } catch (Exception e) {
                    }
                }

            }).on(io.socket.client.Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    try {
                        Log.d(LOG_SOCKET, "socket connect time out error");
                        //  publicSocket();
                        chatListener.isConnected(false);
                    } catch (Exception e) {
                    }
                }

            }).on("chat", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    try {
                        Log.d(LOG_SOCKET, "socket update location" + args.toString());
                        // publicSocket();
                        JSONObject object = new JSONObject(args[0].toString());

                       /* runningActivityName =  Constants.getActivityName(getApplicationContext());
                        if(!runningActivityName.equals(getApplicationContext().getPackageName()+ Constants.CHAT_ACTIVITY)) {
                            sendNotificationForchatScreen(object);
                        }*/
                        chatListener.getChatResponse(object);
                        Log.d(LOG_SOCKET, "socket update location" + object.toString());
                    } catch (Exception e) {
                       e.printStackTrace();
                    }
                }
                }).on("typing", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        try {
                            Log.d(LOG_SOCKET, "socket get typing" + args.toString());
                            JSONObject object = new JSONObject(args[0].toString());
                            chatListener.getTypeStatus(object);
                            Log.d(LOG_SOCKET, "socket get typing" + object.toString());


                        } catch (Exception e) {
                        }

                    }

            });

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }
    public void sendChat(String orderId, String FromId, String toId, String message, String access_token) {
        try {
            if (mSocket == null) {
                publicChatSocket();
            }
                if (mSocket.connected() == true) {
                    JSONObject json = new JSONObject();
                    json.put("orderId", orderId);
                    json.put("from", FromId);
                    json.put("to", toId);
                    json.put("text", message);
                    json.put("accessToken", access_token);
                    mSocket.emit("chat", json);
                    Log.d(LOG_SOCKET, String.valueOf(json));
                }

        } catch (Exception e) {

        }
    }
    public void typeStatusChat(String toId) {
        try {
            if (mSocket == null) {
                publicChatSocket();
            }
                if (mSocket.connected() == true) {
                    JSONObject json = new JSONObject();
                    json.put("to", toId);
                    mSocket.emit("typing", json);
                    Log.d(LOG_SOCKET, String.valueOf(json));
                }

        } catch (Exception e) {

        }
    }


    public  interface ChatListener {

        void getChatResponse(JSONObject jsonObject);
        void getTypeStatus(JSONObject jsonObject);
        void isConnected(boolean isConnected);
    }
  /*  public  void getChatMessage(Context mContext, ChatListener chatListener) {
        this.mContext = mContext;
        this.chatListener = chatListener;
        userIdStr = SharedPreferences.getInstance(mContext).getString(Constants.ID);
        publicChatSocket();
    }*/
   /* private void sendNotificationForchatScreen(JSONObject jsonObject) {
        NotificationManager notificationManagerChat =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManagerChat.cancelAll();

        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(Constants.FROM, "MANUAL_NOTIFICATION");
        intent.putExtra(Constants.ORDER_ID, jsonObject.optString("order_id"));
        intent.putExtra(Constants.FROM_ID, jsonObject.optString("from_id"));
        intent.putExtra(Constants.TO_ID, jsonObject.optString("to_id"));
        intent.putExtra(Constants.ID, jsonObject.optString("id"));
        intent.putExtra(Constants.UPDATED_AT, jsonObject.optString("updated_at"));
        intent.putExtra(Constants.MESSAGE, jsonObject.optString("message"));
        intent.putExtra(Constants.DRIVER_NAME, jsonObject.optString("first_name")+" "+jsonObject.optString("last_name"));
        intent.putExtra(Constants.PROFILE_IMAGE, jsonObject.optString("profile_image"));

*/
     /*   chatBean.setMessageStr(jsonObject.optString("message"));
        chatBean.setOrderId(jsonObject.optString("order_id"));
        chatBean.setTimeStr(jsonObject.optString("updated_at"));
        chatBean.setFromId( jsonObject.optString("from_id"));
        chatBean.setToId(jsonObject.optString("to_id"));
        chatBean.setMessageId(jsonObject.optString("id"));
        */

      /*  driverName = intent.getStringExtra(Constants.DRIVER_NAME);
        driverImage = intent.getStringExtra(Constants.PROFILE_IMAGE);
        orderId = intent.getStringExtra(Constants.ORDER_ID);
        driverId = intent.getStringExtra(Constants.FROM_ID);
        */

 /*       if (intent != null) {
          //  int timeStampt = (int) System.currentTimeMillis();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 2 *//* Request code *//*, intent,
                    PendingIntent.FLAG_ONE_SHOT);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(getResources().getString(R.string.app_name))
                    .setContentText(jsonObject.optString("message"))
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);
            notificationManagerChat.notify(2 *//* ID of notification *//*, notificationBuilder.build());
        }*/
    }



