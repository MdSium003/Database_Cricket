package Core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PassObject implements Serializable {
    private ArrayList<Player> players;
    private ArrayList<Player> market;
    private HashMap<String, String> value;
    private Player player;
    private String clubname;
    private String password;
    String matter;

    public HashMap<String, String> getValue() {
        return value;
    }

    public void setValue(HashMap<String, String> value) {
        this.value = value;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public String getClubname() {
        return clubname;
    }
    public void setClubname(String clubname) {
        this.clubname = clubname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setMatter(String matter) {
        this.matter = matter;
    }
    public String getMatter() {
        return matter;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }
    public ArrayList<Player> getMarket() {return market;}
    public void setMarket(ArrayList<Player> market) {this.market = market;}
}
