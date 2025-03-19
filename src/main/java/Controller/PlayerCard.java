package Controller;

import Core.Player;
import com.example.database_cricket.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class PlayerCard {

    @FXML
    private Label countryrow;

    @FXML
    private Button detailsButton;

    @FXML
    private Label nameRow;

    @FXML
    private Button sellButton;

    private Main main;
    private Player player;
    private boolean flag = false;

    @FXML
    void details(ActionEvent event) {
        main.updateplayercard(player);
    }

    @FXML
    void sell(ActionEvent event) throws IOException {
        if(!flag) main.sellrequest(player);
        else {
            main.buyrequest(player);
        }
    }

    public void update(Player player, Main main, boolean flag, String text) {
        nameRow.setText(player.getName());
        countryrow.setText(player.countryrow());
        this.main = main;
        this.player = player;
        if(flag){
            sellButton.setText("Buy");
            this.flag = true;
        }
        if(text.equalsIgnoreCase(player.getClub()) && flag) {
            sellButton.setVisible(false);
        }
    }
}
