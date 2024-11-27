package com.example.weatherviewapi.Activities;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestAPI {
    private static final String TAG = "RequestAPI";
    private OkHttpClient client;

    public RequestAPI() {
        client = new OkHttpClient();
    }

    public void get(String url, final RequestCallback callback) {

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Erro na requisição", e);
                callback.onFailure(e);  // Chama o método de falha da callback
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    Log.d(TAG, "Resposta: " + responseData);
                    callback.onSuccess(responseData);  // Chama o método de sucesso da callback
                } else {
                    Log.e(TAG, "Erro na resposta: " + response.code());
                    callback.onError(response.code(), response.message());  // Chama o método de erro da callback
                }
            }
        });
    }

    public interface RequestCallback {
        void onSuccess(String response);
        void onFailure(Exception e);
        void onError(int code, String message);
    }
}
