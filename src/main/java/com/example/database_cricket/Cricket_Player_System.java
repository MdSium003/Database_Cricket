package com.example.database_cricket;

import Core.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Cricket_Player_System {
    private final Database data;
    private final Scanner scan_data;
    private ArrayList<Player> found;
    
    public Cricket_Player_System(){
            data = new Database();
            scan_data = new Scanner(System.in);
            //data.login();
    }
    void print(ArrayList<Player> playersList){
        if(playersList.isEmpty()){
            System.out.println("No such player with this name");
        }
        else{
            for(Player i: playersList){
                System.out.println(i);
            }
        }
    }

    void print(Map<String, Integer> map){
        for(Map.Entry<String, Integer> i: map.entrySet()){
            System.out.println("Country: " + i.getKey() + ", Player Count: " + i.getValue());
        } 
    }

    void print(long total){
        if(total <= 0) System.out.println("No such player with this name");
        else System.out.println("Total yearly salary: "+ (total*52));
    }

    public int Mainmenu(){
        System.out.println("\nMain Menu:\n" + 
                            "(1) Search Players\n" + 
                            "(2) Search Clubs\n" + 
                            "(3) Add Player\n" + 
                            "(4) Exit System");
        //System.out.print("Choose an option: ");
        
        int choice;
        while(true){
            try {
                System.out.print("Choose an option: ");
                choice = scan_data.nextInt();
                break;
            } 
            catch (Exception e) {
                System.out.print("Invalid choice! Please choose a valid option.");
                scan_data.nextLine();
            }
        }
        scan_data.nextLine();

        switch(choice){
            case 1 -> {
                return 1;
            }
            case 2 -> {
                return 2;
            }
            case 3 -> {
                return 3;
            }
            case 4 -> {
                //data.save_data();
                System.out.println("Data Preserved!! Ended");
                return 4;
            }
            default -> System.out.println("\nInvalid choice! Please choose a valid option.");
        }
        return 0;
    }

    public int searchPlayers(){
        System.out.println("\nPlayer Searching Options:\n"+ 
                            "(1) By Player Name\n" + 
                            "(2) By Club and Country\n" + 
                            "(3) By Position\n" +
                            "(4) By Salary Range\n" + 
                            "(5) Country-wise player count\n" +
                            "(6) Back to Main Menu");
        //System.out.print("Choose an option: ");
        int choice;
        while(true){
            try {
                System.out.print("Choose an option: ");
                choice = scan_data.nextInt();
                scan_data.nextLine(); 
                break;
            } 
            catch (Exception e) {
                System.out.print("Invalid choice! Please choose a valid option.");
                scan_data.nextLine();
            }
        }

        switch(choice){
            case 1 -> {
                System.out.print("Enter player name: ");
                String name = scan_data.nextLine();
                found = data.searchby_name(name);
                print(found);
            }
            case 2 -> {
                System.out.print("Enter country name: ");
                String country = scan_data.nextLine();
                System.out.print("Enter club name: ");
                String club = scan_data.nextLine();
                found = data.searchby_country_club(country, club);
                print(found);
            }
            case 3 -> {
                System.out.print("Enter position: ");
                String position = scan_data.nextLine();
                found = data.searchby_position(position);
                print(found);
            }
            case 4 -> {
                long min;
                while(true){
                    try {
                        System.out.print("Enter minimum salary: ");
                        min = scan_data.nextLong();
                        scan_data.nextLine(); 
                        break;
                    } 
                    catch (Exception e) {
                        System.out.print("Invalid Input!!");
                        scan_data.nextLine();
                    }
                }
                long max;
                while(true){
                    try {
                        System.out.print("Enter maximum salary: ");
                        max = scan_data.nextLong();
                        scan_data.nextLine(); 
                        break;
                    } 
                    catch (Exception e) {
                        System.out.print("Invalid Input!!");
                        scan_data.nextLine();
                    }
                }
                found = data.searchby_salary(min, max);
                print(found);
            }
            case 5 -> {
                //Map<String, Integer> map = data.searchby_countryplayers();
                //print(map);
            }
            case 6 -> {
                return 0;
            }
            default -> System.out.println("\nInvalid choice! Please choose a valid option.");
            }
        return 1;
    }

    public int searchClubs(){
            System.out.println("\nClub Searching Options:\n" +
                                "(1) Player(s) with the maximum salary of a club\n" +
                                "(2) Player(s) with the maximum age of a club\n" +
                                "(3) Player(s) with the maximum height of a club\n" +
                                "(4) Total yearly salary of a club\n" +
                                "(5) Back to Main Menu" );
            //System.out.print("Choose an option: ");
            int choice;
            while(true){
                try {
                    System.out.print("Choose an option: ");
                    choice = scan_data.nextInt();
                    scan_data.nextLine(); 
                    break;
                } 
                catch (Exception e) {
                    System.out.print("Invalid choice! Please choose a valid option.");
                    scan_data.nextLine();
                }
            }
            
            String club;
            switch(choice){
                case 1 -> {
                    System.out.print("Enter club name: ");
                    club = scan_data.nextLine();
                    found = data.club_salary(club);
                    print(found);
            }
                case 2 -> {
                    System.out.print("Enter club name: ");
                    club = scan_data.nextLine();
                    found = data.club_age(club);
                    print(found);
            }
                case 3 -> {
                    System.out.print("Enter club name: ");
                    club = scan_data.nextLine();
                    found = data.club_height(club);
                    print(found);
            }
                case 4 -> {
                    System.out.print("Enter club name: ");
                    club = scan_data.nextLine();
                    //long total = data.club_total_salary(club);
                    //print(total);
            }
                case 5 -> {
                    return 0;
            }
                default -> System.out.println("\nInvalid choice! Please choose a valid option.");
            }
        return 2;
    }


    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/fxml/login.fxml"));
        Parent root = loader.load();

        // Loading the controller
//        LoginController controller = loader.getController();
//        controller.init(this);
//        updater.setLoginController(controller);

        Stage stage = new Stage();
        //stage = primaryStage;
        stage.setResizable(false);
        stage.setOnCloseRequest(e->closeProgram());
        // Set the primary stage
        stage.setTitle("Football Manager - Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void closeProgram() {
    }

}
