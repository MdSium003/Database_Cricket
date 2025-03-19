package com.example.database_cricket;

import Core.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Exception.Login_fail;
import Server.Server_Data;

public class Database {
    private List<Player> playersList;
    private List<Player> market_players;
    private final Scanner scan_data = new Scanner(System.in);
    public String club_name;

    public Database(){
        playersList = new ArrayList<>();
        market_players = new ArrayList<>();
    }

    public void login(ArrayList<Player> playersList, String club_name, ArrayList<Player> market_players){
        this.playersList = playersList;
        this.club_name = club_name;
        this.market_players = market_players;
    }

    public List<Player> getmarket(){
        List<Player> new_market_players = new ArrayList<>();
        for(Player i: market_players){
            new_market_players.add(new Player(i.getName(), i.getCountry(), i.getAge(), i.getHeight(), i.getClub(), i.getPosition(), i.getNumber(), i.getSalary(), i.getSold()));
        }
        return new_market_players;
    }

    public ArrayList<Player> searchby_name(String name){
        ArrayList<Player> Found_Players = new ArrayList<>();
        for(Player i: playersList){
            if(i.getName().equalsIgnoreCase(name)){
                Found_Players.add(i);
            }
        }
        return Found_Players;
    }

    public ArrayList<Player> searchby_country_club(String country, String club){
        ArrayList<Player> Found_Players = new ArrayList<>();
        for(Player i: playersList){
            if(i.getCountry().equalsIgnoreCase(country) && (club .equalsIgnoreCase("ANY")||i.getClub().equalsIgnoreCase(club))){
                Found_Players.add(i);
            }
        }
        return Found_Players;
    }

    public ArrayList<Player> searchby_position(String position){
        ArrayList<Player> Found_Players = new ArrayList<>();
        for(Player i: playersList){
            if(i.getPosition().equalsIgnoreCase(position)){
                Found_Players.add(i);
            }
        }
        return Found_Players;
    }

    public ArrayList<Player> searchby_salary(long min, long max){
        ArrayList<Player> Found_Players = new ArrayList<>();
        for(Player i: playersList){
            if(i.getSalary() >= min && i.getSalary() <= max){
                Found_Players.add(i);
            }
        }
        return Found_Players;
    }

    public String searchby_countryplayers(){
        Map<String, Integer> map = new HashMap<>();
        for(Player i: playersList){
            map.put(i.getCountry(), map.getOrDefault(i.getCountry(), 0)+1);
        }
        String answer = new String();
        for(Map.Entry<String, Integer> i: map.entrySet()){
             System.out.println("Country: " + i.getKey() + ", Player Count: " + i.getValue());
             answer = (answer + "Country: " + i.getKey() + ", Player Count: " + i.getValue() +"\n");
        }
        return answer;
    }

    public ArrayList<Player> club_salary(String club){
        ArrayList<Player> Found_Players = new ArrayList<>();
        long salary = 0;
        for(Player i: playersList){
            if(i.getClub().equalsIgnoreCase(club)){
                salary = (salary > i.getSalary())?salary : i.getSalary();
            }
        }
        for(Player i: playersList){
            if(i.getClub().equalsIgnoreCase(club) && i.getSalary() == salary){
                Found_Players.add(i);
            }
        }
        return Found_Players;
    }

    public ArrayList<Player> club_age(String club){
        ArrayList<Player> Found_Players = new ArrayList<>();
        int age = 0;
        for(Player i: playersList){
            if(i.getClub().equalsIgnoreCase(club)){
                age = (age > i.getAge())?age : i.getAge();
            }
        }
        for(Player i: playersList){
            if(i.getClub().equalsIgnoreCase(club) && i.getAge() == age){
                Found_Players.add(i);
            }
        }
        return Found_Players;
    }

    public ArrayList<Player> club_height(String club){
        ArrayList<Player> Found_Players = new ArrayList<>();
        double height = 0;
        for(Player i: playersList){
            if(i.getClub().equalsIgnoreCase(club)){
                height = (height > i.getHeight())?height : i.getHeight();
            }
        }
        for(Player i: playersList){
            if(i.getClub().equalsIgnoreCase(club) && i.getHeight() == height){
                Found_Players.add(i);
            }
        }
        return Found_Players;
    }

    public String club_total_salary(String club){
        long total = 0;
        for(Player i: playersList){
            if(i.getClub().equalsIgnoreCase(club)){
                total += i.getSalary();
            }
        }
        return ("Total Salary: "+total);
    }

    public List<Player> getplayerlist(){
        List<Player> new_playersList = new ArrayList<>();
        for(Player i: playersList){
            new_playersList.add(new Player(i.getName(), i.getCountry(), i.getAge(), i.getHeight(), i.getClub(), i.getPosition(), i.getNumber(), i.getSalary(), i.getSold()));
        }
        return new_playersList;
    }

    public void sellrequest(Player player){
        Player check = new Player();
        for(Player i: playersList){
            if(i.getName().equalsIgnoreCase(player.getName())){
                check = i;
                break;
            }
        }
        playersList.remove(check);
    }

    public void new_sellupdate(Player player) {
        market_players.add(player);
    }

    public void buyrequest(Player player) {
        player.setClub(club_name);
        playersList.add(player);
    }

    public void new_buyupdate(Player player) {
        Player check = new Player();
        for(Player i: market_players){
            if(i.getName().equalsIgnoreCase(player.getName())){
                check = i;
                break;
            }
        }
        market_players.remove(check);
    }
}
