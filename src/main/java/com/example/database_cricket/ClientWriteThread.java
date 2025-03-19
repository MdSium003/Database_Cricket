package com.example.database_cricket;

import Core.PassObject;
import Core.Player;
import Core.SocketWrapper;
import com.example.database_cricket.Database;

import java.io.IOException;
import java.util.Scanner;

public class ClientWriteThread implements Runnable {
    private Thread thread;
    private SocketWrapper socketWrapper;
    private String name;
    private Client client;

    public ClientWriteThread(SocketWrapper socketWrapper, String name, Client client) {
        this.socketWrapper = socketWrapper;
        this.thread = new Thread(this);
        this.name = name;
        thread.start();
        this.client = client;
    }

    public void login(String id, String password) throws IOException {
        PassObject obj = new PassObject();
        obj.setClubname(id);
        obj.setPassword(password);
        obj.setMatter("Login_req");
        new Thread(() -> {
            try {
                socketWrapper.write(obj);
            } catch (IOException e) {
                System.out.println("WriteThread error:" + e);
            }
        }).start();
    }

    public void run(){}

    public void new_login(String id, String pass) {
        PassObject obj = new PassObject();
        obj.setClubname(id);
        obj.setPassword(pass);
        obj.setMatter("Login_req_new");
        new Thread(() -> {
            try {
                socketWrapper.write(obj);
            } catch (IOException e) {
                System.out.println("WriteThread error:" + e);
            }
        }).start();
    }

    public void sellrequest(Player player) throws IOException {
        PassObject obj = new PassObject();
        obj.setMatter("Sell_req");
        obj.setPlayer(player);

        new Thread(() -> {
            try {
                socketWrapper.write(obj);
            } catch (IOException e) {
                System.out.println("WriteThread error:" + e);
            }
        }).start();
    }

    public void buyrequest(Player player) {
        PassObject obj = new PassObject();
        obj.setMatter("buy_req");
        obj.setPlayer(player);

        new Thread(() -> {
            try {
                socketWrapper.write(obj);
            } catch (IOException e) {
                System.out.println("WriteThread error:" + e);
            }
        }).start();
    }

    public void addplayer(Player newplayer) {
        PassObject obj = new PassObject();
        obj.setMatter("new_player");
        obj.setPlayer(newplayer);

        new Thread(() -> {
            try {
                socketWrapper.write(obj);
            } catch (IOException e) {
                System.out.println("WriteThread error:" + e);
            }
        }).start();
    }

    public void logout() {
        PassObject obj = new PassObject();
        obj.setMatter("remove_client");

        new Thread(() -> {
            try {
                socketWrapper.write(obj);
            } catch (IOException e) {
                System.out.println("WriteThread error:" + e);
            }
        }).start();
    }
}
