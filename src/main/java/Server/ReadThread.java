package Server;

import Core.PassObject;
import Core.SocketWrapper;

public class ReadThread implements Runnable{
    private Thread thread;
    private SocketWrapper socketWrapper;
    WriteThread writeThread;


    public ReadThread(SocketWrapper socketWrapper, WriteThread writeThread) {
        this.socketWrapper = socketWrapper;
        this.thread = new Thread(this);
        thread.start();
        this.writeThread = writeThread;
    }

    public void run(){
        try{
            while(true){
                Object obj = socketWrapper.read();
                if(obj instanceof PassObject){
                    PassObject object = (PassObject) obj;
                    if(object.getMatter().equalsIgnoreCase("Login_req")) {
                        new Thread(() -> writeThread.login(object, socketWrapper)).start();
                    }
                    else if(object.getMatter().equalsIgnoreCase("Login_req_new")) {
                        new Thread(() -> writeThread.login_new(object, socketWrapper)).start();
                    }
                    else if(object.getMatter().equalsIgnoreCase("Sell_req")){
                        new Thread(() -> writeThread.sellrequest(object, socketWrapper)).start();
                    }
                    else if(object.getMatter().equalsIgnoreCase("buy_req")){
                        new Thread(() -> writeThread.buyrequest(object, socketWrapper)).start();
                    }
                    else if(object.getMatter().equalsIgnoreCase("new_player")){
                        Server_Data.getInstance().addnewplayer(object.getPlayer());
                    }
                    else if(object.getMatter().equalsIgnoreCase("remove_client")){
                        new Thread(() -> writeThread.loggingout(object, socketWrapper)).start();
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
