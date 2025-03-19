package com.example.database_cricket;

import Core.PassObject;
import Core.SocketWrapper;

public class ClientReadThread implements Runnable{
    private Thread thread;
    private SocketWrapper socketWrapper;
    private Client client;
    public ClientReadThread(SocketWrapper socketWrapper, Client client) {
        this.socketWrapper = socketWrapper;
        this.thread = new Thread(this);
        thread.start();
        this.client = client;
    }

    public void run(){
        try{
            while(true){
                Object obj = socketWrapper.read();
                if(obj instanceof PassObject ){
                    PassObject object = (PassObject) obj;
                    if(((PassObject) obj).getMatter().equalsIgnoreCase("Login_success") || ((PassObject) obj).getMatter().equalsIgnoreCase("Login_fail"))
                    {
                        client.login_2(object);
                    }
                    if(((PassObject) obj).getMatter().equalsIgnoreCase("Login_success_new") || ((PassObject) obj).getMatter().equalsIgnoreCase("Login_fail_new"))
                    {
                        client.save_new_id_pass_2(object);
                    }
                    else if(((PassObject) obj).getMatter().equalsIgnoreCase("Sell_req"))
                    {
                        System.out.println("Sell Success");
                        client.new_sellupdate(((PassObject) obj).getPlayer());
                    }
                    else if(((PassObject) obj).getMatter().equalsIgnoreCase("Buy_req")){
                        System.out.println("Buy Success");
                        client.new_buyupdate(((PassObject) obj).getPlayer());
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("ReadThread error:" + e);
        }
        finally {
            try{
                socketWrapper.closeConnection();
            }
            catch (Exception e){
                System.out.println("ReadThread error: "+e);
            }
        }
    }
}