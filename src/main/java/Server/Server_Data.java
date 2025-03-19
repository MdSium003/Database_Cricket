package Server;

import Core.Player;
import Exception.Login_fail;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server_Data {

    private static Server_Data instance;
    private final String INPUT_OUTPUT;
    private final String Login_id_pass;
    private final ArrayList<Player> playersList;
    private final ArrayList<Player> marketList;
    private final Map<String, String> login_credentials = new HashMap<>();
    public Server_Data(){
        playersList = new ArrayList<>();
        marketList = new ArrayList<>();
        INPUT_OUTPUT = "E:\\1-2 Session\\JavaFX\\Cricket_System\\Database_Cricket\\src\\main\\java\\Server\\players.txt";
        Login_id_pass = "E:\\1-2 Session\\JavaFX\\Cricket_System\\Database_Cricket\\src\\main\\java\\Server\\Login_id_pass.txt";
        extract_data();
        extract_Login_id_pass();
    }

    public static Server_Data getInstance(){
        if(instance == null){
            instance = new Server_Data();
        }
        return instance;
    }

    private void extract_Login_id_pass(){
        try(BufferedReader scan = new BufferedReader(new FileReader(Login_id_pass))){
            String data;
            while((data = scan.readLine()) != null){
                String[] splited_data = data.split(",");
                login_credentials.put(splited_data[0], splited_data[1]);
            }
            scan.close();
        }
        catch(IOException ex){
            System.out.println("No File found to read DATA for Login_id_pass");
        }
    }

    public boolean save_new_id_pass(String id, String pass){
        if(login_credentials.containsKey(id)){
            return false;
        }
        else{
            login_credentials.put(id, pass);
            return true;
        }
    }

    private void extract_data(){
        try(BufferedReader scan = new BufferedReader(new FileReader(INPUT_OUTPUT))){
            String data;
            while((data = scan.readLine()) != null){
                String[] splited_data = data.split(",");
                int number = 0;
                int marketing = 0;
                if(!splited_data[6].isEmpty()) number = Integer.parseInt(splited_data[6]);
                if(!splited_data[8].isEmpty()) marketing = Integer.parseInt(splited_data[8]);
                Player temp_player = new Player(splited_data[0], splited_data[1], Integer.parseInt(splited_data[2]), Double.parseDouble(splited_data[3]), splited_data[4], splited_data[5], number, Integer.parseInt(splited_data[7]), marketing);
                if(marketing == 0) playersList.add(temp_player);
                else marketList.add(temp_player);
                //System.out.println("haha");
            }
            scan.close();
        }
        catch(IOException ex){
            System.out.println("No File found to read DATA");
        }
    }

    public void save_data(){
        try(BufferedWriter out = new BufferedWriter(new FileWriter(INPUT_OUTPUT))){
            for(Player i : playersList){
                out.write(i.save());
            }
            for(Player i : marketList){
                out.write(i.save());
            }
            out.close();
        }
        catch(IOException ex){
            System.out.println("No File found to save data");
        }
    }

    public ArrayList<Player> extract_club(String club) {
        ArrayList<Player> Found_Players = new ArrayList<>();
        for(Player i: playersList){
            if(i.getClub().equalsIgnoreCase(club)){
                Found_Players.add(new Player(i.getName(), i.getCountry(), i.getAge(), i.getHeight(), i.getClub(), i.getPosition(), i.getNumber(), i.getSalary(), i.getSold()));
                System.out.println(i);
            }
        }
        return Found_Players;
    }

    public ArrayList<Player> checklogin(String id, String pass) throws Login_fail {
        if(login_credentials.containsKey(id) && login_credentials.get(id).equals(pass)){
            return extract_club(id);
        }
        else{
            System.out.println("Login Failed"+ id + " " + pass);
            throw new Login_fail("fail");
        }
    }


    public void addnewplayer(Player player) {
        playersList.add(player);
    }

    public void addmarket(Player player) {
        Player check = new Player();
        for(Player i: playersList){
            if(i.getName().equalsIgnoreCase(player.getName())){
                check = i;
                break;
            }
        }
        playersList.remove(check);
        marketList.add(check);
        check.setSold(1);
    }

    public ArrayList<Player> getMarketList() {
        ArrayList<Player> new_market_players = new ArrayList<>();
        for(Player i: marketList){
            new_market_players.add(new Player(i.getName(), i.getCountry(), i.getAge(), i.getHeight(), i.getClub(), i.getPosition(), i.getNumber(), i.getSalary(), i.getSold()));
        }
        return new_market_players;
    }

    public void remove_market(Player player) {
        Player check = new Player();
        for(Player i: marketList){
            if(i.getName().equalsIgnoreCase(player.getName())){
                check = i;
                break;
            }
        }
        marketList.remove(check);
        check.setSold(0);
        playersList.add(check);
        check.setClub(player.getClub());
    }

    public void save_id_pass() {
        try(BufferedWriter out = new BufferedWriter(new FileWriter(Login_id_pass))){
            for(Map.Entry<String, String> a : login_credentials.entrySet()){
                out.write(a.getKey() + "," + a.getValue()+"\n");
            }
            out.close();
        }
        catch(IOException ex){
            System.out.println("No File found to save data");
        }
    }
}
