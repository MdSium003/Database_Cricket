package Controller;

import com.example.database_cricket.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class LoginControl {

    private Main main;

    @FXML
    private MediaView mediaView;

    //@FXML
    //private TextField Club;

    @FXML
    private Button signin;

    @FXML
    private Button signup;

    @FXML
    private TextField clubname;

    @FXML
    private PasswordField password;
    //private

    @FXML
    private Button signupconfirm;

    @FXML
    private ImageView soundoff;

    @FXML
    private ImageView soundon;

    @FXML
    private Button cancel;

    @FXML
    private PasswordField confirmpassword;

    @FXML
    private Text logintext1;

    @FXML
    private ToggleButton sound;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;

    public void initialize(){
        file = new File("src/main/resources/Videos/login.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        signupconfirm.setVisible(false);
        cancel.setVisible(false);
        confirmpassword.setVisible(false);
        logintext1.setVisible(false);
        soundoff.setVisible(false);
    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
    }
    @FXML
    public void soundchange(ActionEvent actionEvent) {
        mediaPlayer.setMute(!mediaPlayer.isMute());
        soundon.setVisible(!soundon.isVisible());
        soundoff.setVisible(!soundoff.isVisible());
    }

    public void checklogin_page(ActionEvent actionEvent) throws Exception {
        main.id_pass_check(clubname.getText(), password.getText());
    }

    public void signuppage(ActionEvent actionEvent) {
        signin.setVisible(false);
        signup.setVisible(false);
        signupconfirm.setVisible(true);
        cancel.setVisible(true);
        confirmpassword.setVisible(true);
        clubname.setPromptText("Enter Club Name");
        password.setPromptText("Enter Password");
        confirmpassword.setPromptText("Enter Password");
        logintext1.setVisible(false);
    }

    public void signupconfirm(ActionEvent actionEvent) throws Exception {
        if(password.getText().equals(confirmpassword.getText())){
            main.save_new_id_pass(clubname.getText(), password.getText());
        }
        else{
            password.setText("");
            confirmpassword.setText("");
            password.setPromptText("Passwords do not match");
            confirmpassword.setPromptText("Passwords do not match");
        }
    }

    public void signupconfirm_2(boolean val) {
        try{
            if(val){
                //main.showSign();
                confirmpassword.setText("");
                clubname.setText("");
                password.setText("");
                signupconfirm.setVisible(false);
                cancel.setVisible(false);
                confirmpassword.setVisible(false);
                signin.setVisible(true);
                signup.setVisible(true);
                logintext1.setVisible(true);
            }
            else{
                throw new Exception();
            }

        } catch (Exception e) {
            clubname.setText("");
            password.setText("");
            confirmpassword.setText("");
            clubname.setPromptText("Club already exists");
            password.setPromptText("Enter Password");
            confirmpassword.setPromptText("Enter Password");
        }
    }

    public void cancelback(ActionEvent actionEvent) {
        confirmpassword.setVisible(false);
        cancel.setVisible(false);
        signupconfirm.setVisible(false);
        signin.setVisible(true);
        signup.setVisible(true);
        clubname.setPromptText("Enter Club Name");
        password.setPromptText("Enter Password");
    }

    public void init(Main main) {
        this.main = main;
    }

    public void loginfail(boolean val) {
            if(val){
                mediaPlayer.stop();
                mediaPlayer.dispose();
                try {
                    main.showMainmenu(clubname.getText());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                clubname.setText("");
                password.setText("");
                clubname.setPromptText("Invalid Club Name");
                password.setPromptText("Invalid Password");
            }
    }


}
