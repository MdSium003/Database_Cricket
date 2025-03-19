package Server;

import Core.PassObject;
import Core.Player;
import Core.SocketWrapper;
import com.example.database_cricket.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Exception.Login_fail;

public class WriteThread implements Runnable {
    private List<SocketWrapper> clientlist = new ArrayList<>();

    public void login(PassObject obj, SocketWrapper socketWrapper) {
        try {
            Server_Data.getInstance().checklogin(obj.getClubname(), obj.getPassword());
            obj.setPlayers(Server_Data.getInstance().extract_club(obj.getClubname()));
            obj.setMarket(Server_Data.getInstance().getMarketList());
            clientlist.add(socketWrapper);
            System.out.println("Client Connected");
            obj.setMatter("Login_success");
            socketWrapper.write(obj);
        }
        catch (Exception e){
            obj.setMatter("Login_fail");
            System.out.println("WriteThread error:" + e);
            try{
                socketWrapper.write(obj);
            }
            catch (Exception ex){
                System.out.println("WriteThread error:" + ex);
            }
        }
    }

    public void run(){}

    public void login_new(PassObject obj, SocketWrapper socketWrapper) {
        try {
            boolean ans = Server_Data.getInstance().save_new_id_pass(obj.getClubname(), obj.getPassword());
            if(ans) obj.setMatter("Login_success_new");
            else obj.setMatter("Login_fail_new");
            socketWrapper.write(obj);
        }
        catch (Exception e){
            System.out.println("WriteThread error:" + e);
        }
    }

    public void sellrequest(PassObject object, SocketWrapper socketWrapper) {
        Server_Data.getInstance().addmarket(object.getPlayer());
        for(SocketWrapper client : clientlist) {
            try {
                client.write(object);
            } catch (IOException e) {
                System.out.println("WriteThread error:" + e);
            }
        }
    }

    public void buyrequest(PassObject object, SocketWrapper socketWrapper) {
        Server_Data.getInstance().remove_market(object.getPlayer());
        for(SocketWrapper client : clientlist) {
            try {
                client.write(object);
            } catch (IOException e) {
                System.out.println("WriteThread error:" + e);
            }
        }
    }

    public void loggingout(PassObject object, SocketWrapper socketWrapper) {
        clientlist.remove(socketWrapper);
    }
}
