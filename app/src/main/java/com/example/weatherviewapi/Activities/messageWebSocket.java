package com.example.weatherviewapi.Activities;
import android.util.Log;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
public class messageWebSocket {
    private static final String TAG = "messageWebSocket";
    private WebSocketClient mWebSocketClient;
    private String messageUpdate;

    public messageWebSocket(String uri) {
        try {
            URI webSocketUri = new URI(uri);
            mWebSocketClient = new WebSocketClient(webSocketUri) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.d(TAG, "Conexão aberta!");
                }
                @Override
                public void onMessage(String message) {
                    messageUpdate = message;
                }
                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d(TAG, "Conexão fechada: " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    Log.e(TAG, "Erro no WebSocket", ex);
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        if (mWebSocketClient != null) {
            mWebSocketClient.connect();
        }
    }

    public void close() {
        if (mWebSocketClient != null) {
            mWebSocketClient.close();
        }
    }

    public boolean isOpen() {
        return mWebSocketClient != null && mWebSocketClient.isOpen();
    }

    public String getMessageUpdate() {
        return messageUpdate;
    }
}
