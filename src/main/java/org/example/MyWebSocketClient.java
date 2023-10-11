package org.example;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class MyWebSocketClient extends WebSocketClient {


    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("connected");

    }

    @Override
    public void onMessage(String message) {

    }

    @Override
    public void send(String text) {
        super.send(text);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }
}
