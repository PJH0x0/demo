package org.pjh.jetpackdemo.model;

import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

public class LogEventListener extends EventListener {
    private static final String TAG = "EventListener";
    public void callStart(Call call) {
        Log.d(TAG, "callStart: ");
    }

    public void dnsStart(Call call, String domainName) {
        Log.d(TAG, "dnsStart: domainName = " + domainName);
    }

    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        Log.d(TAG, "dnsEnd: " + inetAddressList);
    }

    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        Log.d(TAG, "connectStart: " + inetSocketAddress + " proxy = " + proxy);
    }

    public void secureConnectStart(Call call) {
        Log.d(TAG, "secureConnectStart: ");
    }

    public void secureConnectEnd(Call call, @Nullable Handshake handshake) {
        Log.d(TAG, "secureConnectEnd: " + handshake);
    }

    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol) {
        Log.d(TAG, "connectEnd: inetSocketAddress = " + inetSocketAddress + " proxy = " + proxy + " protocol = " + protocol);
    }

    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol, IOException ioe) {
        Log.d(TAG, "connectFailed: inetSocketAddress = " + inetSocketAddress + " proxy = " + inetSocketAddress + " protocol = " + protocol + " ioe");
        ioe.printStackTrace();
    }

    public void connectionAcquired(Call call, Connection connection) {
        Log.d(TAG, "connectionAcquired: connection + " + connection);
    }

    public void connectionReleased(Call call, Connection connection) {
        Log.d(TAG, "connectionReleased: connection = " + connection);
    }

    public void requestHeadersStart(Call call) {
        Log.d(TAG, "requestHeadersStart: ");
    }

    public void requestHeadersEnd(Call call, Request request) {
        Log.d(TAG, "requestHeadersEnd: request = " + request);
    }

    public void requestBodyStart(Call call) {
        Log.d(TAG, "requestBodyStart: ");
    }

    public void requestBodyEnd(Call call, long byteCount) {
        Log.d(TAG, "requestBodyEnd: byteCount = " + byteCount);
    }

    public void requestFailed(Call call, IOException ioe) {
        Log.d(TAG, "requestFailed: ");
    }

    public void responseHeadersStart(Call call) {
        Log.d(TAG, "responseHeadersStart: ");
    }

    public void responseHeadersEnd(Call call, Response byteCount) {
        Log.d(TAG, "responseHeadersEnd: byteCount = " + byteCount);
    }

    public void responseBodyStart(Call call) {
        Log.d(TAG, "responseBodyStart: ");
    }

    public void responseBodyEnd(Call call, long byteCount) {
        Log.d(TAG, "responseBodyEnd: byteCount = " + byteCount);
    }

    public void responseFailed(Call call, IOException ioe) {
        Log.d(TAG, "responseFailed: ");
    }

    public void callEnd(Call call) {
        Log.d(TAG, "callEnd: ");
    }

    public void callFailed(Call call, IOException ioe) {
        Log.d(TAG, "callFailed: ");
    }
}
