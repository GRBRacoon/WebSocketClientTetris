package org.example;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.java_websocket.client.WebSocketClient;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URI;


public class WebSocketService {
    public enum MessageType{
        ENTER, GAME,END
    }
    String serverUri;
    String socketUri;
    HttpClient httpClient = HttpClients.createDefault();
    MyWebSocketClient client;
    public WebSocketService(String serverUri,String socketUri) {
        this.serverUri = serverUri;
        this.socketUri = socketUri;
        client=new MyWebSocketClient(URI.create(socketUri));
        client.connect();
    }

    public void CreateRoom(){
        String name="name";
        String uri=serverUri+"chat?name="+name;
        try {
            HttpPost httpPost = new HttpPost(uri);
            httpClient.execute(httpPost);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void joinRoom(){
        String type="ENTER";
        String roomId ="3e796b9b-0568-41ad-ad7d-67eda4150ea2";
        String sender="me";
        String message="hello";

        JSONObject jsonObject= new JSONObject();
        jsonObject.put("type",type);
        jsonObject.put("roomId",roomId);
        jsonObject.put("sender",sender);
        jsonObject.put("message",message);
        client.send(jsonObject.toJSONString());

    }
    public void exitRoom(){
        client.close();
    }
    public void sendMessage(){
        String type= MessageType.GAME.toString();
        String roomId ="3e796b9b-0568-41ad-ad7d-67eda4150ea2";
        String sender="me";
        String message="talk";

        JSONObject jsonObject= new JSONObject();
        jsonObject.put("type",type);
        jsonObject.put("roomId",roomId);
        jsonObject.put("sender",sender);
        jsonObject.put("message",message);
        client.send(jsonObject.toJSONString());
    }

    public boolean logIn(){
        String id="hello";
        String password="hello";
        String uri=serverUri+"chat?name="+id+"&password="+password;
        try {
            HttpPost httpPost = new HttpPost(uri);
            String res=httpClient.execute(httpPost).toString();
            if(res.equals("log in success"))
                return true;
            else if(res.equals("log in fail"))
                return false;
            else return false;

        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void join(){

    }

}
