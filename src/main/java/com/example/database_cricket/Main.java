package com.example.database_cricket;

import Controller.MainPage;
import Controller.PlayerCard;
import Core.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Controller.LoginControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public Client client;
    Stage stage;
    private LoginControl controller;

    private List<Player> shownlist = new ArrayList<>();

    public int page = 1;
    public Main() {}

    @Override
    public void start(Stage stage) throws Exception {
        String serverAddress = "127.0.0.1";
        int serverPort = 43444;
        client = new Client(serverAddress, serverPort);
        client.setMain(this);

        this.stage = stage;
        stage.setResizable(false);
        showLogin();
    }

    public void showLogin() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/database_cricket/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1000,750);
        stage.setTitle("Cricket Manager - Login");
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();

        controller = fxmlLoader.getController();
        controller.init(this);
        //updater.setLoginController(controller);
    }

    public void id_pass_check(String id, String pass) throws Exception{
        client.login(id, pass);
    }

    public void login(boolean val) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.loginfail(val);
            }
        });
    }

    public void save_new_id_pass(String id, String pass) throws Exception{
        client.save_new_id_pass(id,pass);
    }

    public void save_new_id_pass_2(boolean val) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.signupconfirm_2(val);
            }
        });
    }

    MainPage MPController;
    public void showMainmenu(String club) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/database_cricket/MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1000,750);
        stage.setTitle("Cricket Manager");
        stage.setScene(scene);
        stage.show();

        MPController = fxmlLoader.getController();
        MPController.init(this, club);
        shownlist = client.database.getplayerlist();
        populatePlayerGrid(MPController.getgrid(),"No such player with this instance", false);
    }



    public void searchoperation(String name, GridPane grid, int operation){
        String temp = "No such player with this instance";
        if(operation == 0){
            shownlist = client.database.searchby_name(name);
        }
        else if(operation == 1){
            shownlist = client.database.searchby_country_club(name, "ANY");
        }
        else if(operation == 2){
            shownlist = client.database.searchby_position(name);
        }
        else if(operation == 3){
            String[] splited_data = name.split("-");
            shownlist = client.database.searchby_salary(Long.parseLong(splited_data[0]), Long.parseLong(splited_data[1]));
        }
        else if(operation == 4){
            shownlist.clear();
            shownlist = new ArrayList<>();
            temp = client.database.searchby_countryplayers();
        }
        else if(operation == 5){
            shownlist = client.database.club_height(client.database.club_name);
        }
        else if(operation == 6){
            shownlist = client.database.club_age(client.database.club_name);
        }
        else if(operation == 7){
            shownlist = client.database.club_salary(client.database.club_name);
        }
        else if(operation == 8){
            shownlist.clear();
            shownlist = new ArrayList<>();
            temp = client.database.club_total_salary(client.database.club_name);
        }
        populatePlayerGrid(grid,temp, false);
    }


    private void populatePlayerGrid(GridPane grid, String temp, boolean flag) {
        grid.getChildren().clear();

        String playercard = "/com/example/database_cricket/PlayerCard.fxml"; // FXML path for the player box
        int column = 0;
        int row = 0;
        try {
            for (Player player : shownlist) {
                FXMLLoader cardload = new FXMLLoader();
                cardload.setLocation(getClass().getResource(playercard));

                AnchorPane playerPane = cardload.load();
                PlayerCard playerCard = cardload.getController();
                playerCard.update(player,this, flag, client.clubname); // Update GUI with player details

                grid.add(playerPane, column, row++); // Add player box to grid
                GridPane.setMargin(playerPane, new Insets(10));
                System.out.println(player);
            }
            if(shownlist.isEmpty()){
                MPController.middletext(temp);
            }
            else{
                MPController.middletext("");
            }
        } catch (IOException e) {
            System.err.println("Error populating player grid: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadall(GridPane grid){
        shownlist = client.database.getplayerlist();
        page = 1;
        populatePlayerGrid(grid, "No such player with this instance", false);
    }

    public void updateplayercard(Player player){
        MPController.updatecard(player);
    }

    public void marketshow(String pass) {
        shownlist = client.database.getmarket();
        if(pass.equalsIgnoreCase( "controller")) page = 2;
        if(page == 2) populatePlayerGrid(MPController.getgrid(), "No such player with this instance", true);
    }

    public static void main (String[] args) {
        launch();
    }

    public void sellrequest(Player player) throws IOException {
        client.sellrequest(player, MPController.getgrid());
        if(page == 1) loadall(MPController.getgrid());
    }

    public void buyrequest(Player player) {
        client.buyrequest(player, MPController.getgrid());
        marketshow("server");
    }

    public void addnewplayer(Player newplayer, GridPane grid) {
        client.addnewplayer(newplayer, grid);
    }

    public void logout() throws Exception {
        client.logoutreq();
        showLogin();
    }
}
