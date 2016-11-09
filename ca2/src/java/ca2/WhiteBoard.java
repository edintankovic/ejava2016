/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author edint_000
 */
@ServerEndpoint("/wbendpoint/{category}")
public class WhiteBoard {
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public String onMessage(String message, @PathParam("category") String category) {
        
        for (Session peer : peers) {
            try {
                if (peer.getUserProperties().get("category").equals(category) ||
                        peer.getUserProperties().get("category").equals("ALL") )
                    peer.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    
    @OnOpen
    public void onOpen (Session peer, @PathParam("category") String category) {
        peer.getUserProperties().put("category", category);
        peers.add(peer);
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
    }
    
}
