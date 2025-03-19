package com.example.database_cricket;

import Core.PassObject;
import Core.Player;
import Core.SocketWrapper;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class Client {
    public Database database = new Database();
    private ClientReadThread readthread;
    private ClientWriteThread writeThread;
    private Main main;

    public String clubname;

    public Client(String serverAddress, int port) {
        try{
            SocketWrapper socketWrapper = new SocketWrapper(serverAddress, port);
            readthread = new ClientReadThread(socketWrapper,this);
            writeThread = new ClientWriteThread(socketWrapper, "Client",this);
        }
        catch (Exception e){
            System.out.println("Client error:" + e);
        }
    }

    public void setMain(Main main){
        this.main = main;
    }

    public void login(String id, String password) throws Exception{
        writeThread.login(id, password);
    }

    public void login_2(PassObject object) throws Exception {
        if(object.getMatter().equalsIgnoreCase("Login_fail")){
            main.login(false);
        }
        else{
            database.login(object.getPlayers(), object.getClubname(), object.getMarket());
            System.out.println("Players Loaded");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    clubname = object.getClubname();
                    main.login(true);
                }
            });

        }
    }

    public void save_new_id_pass(String id, String pass) {
        writeThread.new_login(id, pass);
    }

    public void save_new_id_pass_2(PassObject object) {
        if(object.getMatter().equalsIgnoreCase("Login_fail_new")){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    main.save_new_id_pass_2(false);
                }
            });
        }
        else{
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    main.save_new_id_pass_2(true);
                }
            });
        }
    }

    public void sellrequest(Player player, GridPane grid) throws IOException {
        database.sellrequest(player);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                main.loadall(grid);
            }
        });

        writeThread.sellrequest(player);
    }

    public void new_sellupdate(Player player) {
        database.new_sellupdate(player);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                main.marketshow("client");
            }
        });
    }

    public void buyrequest(Player player, GridPane getgrid) {
        database.buyrequest(player);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                main.marketshow("client");
            }
        });

        writeThread.buyrequest(player);
    }

    public void new_buyupdate(Player player) {
        database.new_buyupdate(player);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                main.marketshow("client");
            }
        });
    }

    public void addnewplayer(Player newplayer, GridPane grid) {
        database.buyrequest(newplayer);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                main.loadall(grid);
            }
        });

        writeThread.addplayer(newplayer);
    }

    public void logoutreq() {
        writeThread.logout();
    }
}
