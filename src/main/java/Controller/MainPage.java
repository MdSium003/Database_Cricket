package Controller;

import Core.Player;
import com.example.database_cricket.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainPage {
    @FXML
    public Button new_add_button;
    public TextField add_name;
    public TextField add_country;
    public TextField add_position;
    public TextField add_age;
    public TextField add_number;
    public TextField add_salary;
    public TextField add_height;
    public VBox addplayercard;

    int operation = 0;
    @FXML
    private VBox PlayerCard;

    @FXML
    private Button Searchbutton;

    @FXML
    private Button add_player;

    @FXML
    private Text card_name;

    @FXML
    private Text clubname;

    @FXML
    private GridPane grid;

    @FXML
    private Button maxage;

    @FXML
    private Button maxheight;

    @FXML
    private Button maxsalary;

    @FXML
    private Button searchbycountry;

    @FXML
    private Button searchbyname;

    @FXML
    private Button searchbyposition;

    @FXML
    private Button searchbysalary;

    @FXML
    private Button searchcountrywise;

    @FXML
    private Button totalsalary;

    @FXML
    private Button cancelbutton;

    @FXML
    private TextField search_name;

    @FXML
    private HBox seachingbar;

    @FXML
    private Button logout;

    @FXML
    private Text middletext;

    Main main;


    @FXML
    void addplayer(ActionEvent event) {
        searchbyname.setVisible(false);
        searchbyposition.setVisible(false);
        searchbysalary.setVisible(false);
        searchcountrywise.setVisible(false);
        searchbycountry.setVisible(false);
        maxage.setVisible(false);
        maxheight.setVisible(false);
        maxsalary.setVisible(false);
        totalsalary.setVisible(false);
        PlayerCard.setVisible(false);
        seachingbar.setVisible(false);
        cancelbutton.setVisible(false);
        //middletext.setVisible(false);
        Searchbutton.setVisible(false);
        add_player.setVisible(false);
        addplayercard.setVisible(true);
    }

    @FXML
    void countrywise(ActionEvent event) {
        main.searchoperation("hehe", grid, 4);
    }

    @FXML
    void homeclick(ActionEvent event) {
        initialize();
        Searchbutton.setVisible(true);
        add_player.setVisible(true);
        main.loadall(grid);
    }

    @FXML
    void marketclick(ActionEvent event) {
        searchbyname.setVisible(false);
        searchbyposition.setVisible(false);
        searchbysalary.setVisible(false);
        searchcountrywise.setVisible(false);
        searchbycountry.setVisible(false);
        maxage.setVisible(false);
        maxheight.setVisible(false);
        maxsalary.setVisible(false);
        totalsalary.setVisible(false);
        PlayerCard.setVisible(false);
        seachingbar.setVisible(false);
        cancelbutton.setVisible(false);
        middletext.setVisible(false);
        Searchbutton.setVisible(false);
        add_player.setVisible(false);
        addplayercard.setVisible(false);

        main.marketshow("controller");
    }

    @FXML
    void max_age(ActionEvent event) {
        middletext.setVisible(false);
        main.searchoperation("hehe", grid, 6);
    }

    @FXML
    void max_height(ActionEvent event) {
        middletext.setVisible(false);
        main.searchoperation("hehe", grid, 5);
    }

    @FXML
    void max_salary(ActionEvent event) {
        middletext.setVisible(false);
        main.searchoperation("hehe", grid, 7);
    }

    @FXML
    void search(ActionEvent event) {
        searchbyname.setVisible(true);
        searchbyposition.setVisible(true);
        searchbysalary.setVisible(true);
        searchcountrywise.setVisible(true);
        searchbycountry.setVisible(true);
        maxage.setVisible(true);
        maxheight.setVisible(true);
        maxsalary.setVisible(true);
        totalsalary.setVisible(true);
        PlayerCard.setVisible(false);
        seachingbar.setVisible(true);
        cancelbutton.setVisible(true);
        middletext.setVisible(false);
    }

    @FXML
    void searchcountry(ActionEvent event) {
        operation = 1;
        search_name.setPromptText("Enter Country");
        search_name.setText("");
    }

    @FXML
    void searchname(ActionEvent event) {
        operation = 0;
        search_name.setPromptText("Enter Name");
        search_name.setText("");
    }

    @FXML
    void searchposition(ActionEvent event) {
        operation = 2;
        search_name.setPromptText("Enter Position");
        search_name.setText("");
    }

    @FXML
    void searchsalary(ActionEvent event) {
        operation = 3;
        search_name.setPromptText("Salary Start-End");
        search_name.setText("");
    }

    @FXML
    void total_salary(ActionEvent event) {
        main.searchoperation("hehe", grid, 8);
    }


    public void initialize(){
        searchbyname.setVisible(false);
        searchbyposition.setVisible(false);
        searchbysalary.setVisible(false);
        searchcountrywise.setVisible(false);
        searchbycountry.setVisible(false);
        maxage.setVisible(false);
        maxheight.setVisible(false);
        maxsalary.setVisible(false);
        totalsalary.setVisible(false);
        PlayerCard.setVisible(false);
        seachingbar.setVisible(false);
        cancelbutton.setVisible(false);
        middletext.setVisible(false);
        Searchbutton.setVisible(true);
        add_player.setVisible(true);
        addplayercard.setVisible(false);
        add_salary.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { // Regex to allow only digits
                add_salary.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        add_age.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { // Regex to allow only digits
                add_age.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        add_number.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { // Regex to allow only digits
                add_number.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        add_height.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\\\d*(\\\\.\\\\d*)?")) { // Regex to allow only digits
                add_height.setText(newValue.replaceAll("[^\\d\\.]", ""));
            }
        });
    }

    public void init(Main main, String clubname){
        this.clubname.setText(clubname);
        this.main = main;
    }

    public GridPane getgrid(){
        return grid;
    }

    public void cancel_button(ActionEvent actionEvent) {
        searchbyname.setVisible(false);
        searchbyposition.setVisible(false);
        searchbysalary.setVisible(false);
        searchcountrywise.setVisible(false);
        searchbycountry.setVisible(false);
        maxage.setVisible(false);
        maxheight.setVisible(false);
        maxsalary.setVisible(false);
        totalsalary.setVisible(false);
        seachingbar.setVisible(false);
        cancelbutton.setVisible(false);
        middletext.setVisible(false);
        main.loadall(grid);
    }

    public void search_check(ActionEvent actionEvent) {
        middletext.setVisible(false);
        if(operation == 0 ||operation == 1 || operation == 2 || operation == 3){
            main.searchoperation(search_name.getText().strip(), grid, operation);
        }
    }

    public void middletext(String text){
        middletext.setVisible(true);
        middletext.setText(text);
    }

    public void loggingout(ActionEvent actionEvent) throws Exception {
        main.logout();
    }

    public void updatecard(Player player){
        searchbyname.setVisible(false);
        searchbyposition.setVisible(false);
        searchbysalary.setVisible(false);
        searchcountrywise.setVisible(false);
        searchbycountry.setVisible(false);
        maxage.setVisible(false);
        maxheight.setVisible(false);
        maxsalary.setVisible(false);
        totalsalary.setVisible(false);
        PlayerCard.setVisible(true);
        card_name.setText(player.printing());
        addplayercard.setVisible(false);
    }

    public void new_add_player(ActionEvent actionEvent) {
        if(add_name.getText().equalsIgnoreCase("")){
            add_name.setPromptText("Invalid Name");
        }
        else if(add_country.getText().equalsIgnoreCase("")){
            add_country.setPromptText("Invalid Country");
        }
        else if(add_age.getText().equalsIgnoreCase("")){
            add_age.setPromptText("Invalid Age");
        }
        else if(add_height.getText().equalsIgnoreCase("")){
            add_height.setPromptText("Invalid Height");
        }
        else if(add_position.getText().equalsIgnoreCase("")){
            add_position.setPromptText("Invalid Position");
        }
        else if(add_number.getText().equalsIgnoreCase("") || Integer.parseInt(add_number.getText()) < 0){
            add_number.setPromptText("Invalid Number");
        }
        else if(add_salary.getText().equalsIgnoreCase("")){
            add_salary.setPromptText("Invalid Salary");
        }
        else{
            Player newplayer = new Player(add_name.getText(),add_country.getText(),Integer.parseInt(add_age.getText()),Double.parseDouble(add_height.getText()),main.client.clubname,add_position.getText(),Integer.parseInt(add_number.getText()), Long.parseLong(add_salary.getText()), 0);
            main.addnewplayer(newplayer, grid);
            Searchbutton.setVisible(true);
            add_player.setVisible(true);
            addplayercard.setVisible(false);
        }
    }

    public void new_cancel(ActionEvent actionEvent) {
        Searchbutton.setVisible(true);
        add_player.setVisible(true);
        addplayercard.setVisible(false);
    }
}

