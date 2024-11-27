package com.example.weatherviewapi.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import com.example.weatherviewapi.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private messageWebSocket messageWs;
    private RequestAPI requestApi;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String webSocketUrl = "ws://localhost:3004/addWeather";
        requestApi = new RequestAPI();
        responseApi();

        messageWs = new messageWebSocket(webSocketUrl);
        messageWs.connect();

        boolean messageUpdate = messageWs.getMessageUpdate().equals("update");

        if(messageUpdate){
            responseApi();
        }
    }

    protected void responseApi(){
        requestApi.get("http://localhost:3004/listWeather", new RequestAPI.RequestCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "Resposta recebida: " + response);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "Falha na requisição", e);
            }

            @Override
            public void onError(int code, String message) {
                Log.e(TAG, "Erro na resposta: Código " + code + " - " + message);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (messageWs != null) {
            messageWs.close();
        }
    }
}
