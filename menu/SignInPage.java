package menu;

import database.Database;
import database.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mainfiles.Main;
import java.util.ArrayList;

public class SignInPage {
    public static Scene makeSignInPage(Button button, ArrayList<User> users) {
        VBox signIn = new VBox();
        HBox password = new HBox();
        HBox userName = new HBox();
        Label name = new Label("Name");
        Label pass = new Label("Password");
        Label message = new Label("");
        TextField enterName = new TextField();
        TextField enterPass = new TextField();
        Button submit = new Button();

        submit.setText("Submit");

        userName.getChildren().addAll(name, enterName);
        password.getChildren().addAll(pass, enterPass);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(users.get(users.size()-1).getUserName());
                if (enterName.getText().compareTo("") != 0) {
                    for (int i = 0; i < users.size(); i++) {
                        if (enterName.getText().compareTo(users.get(i).getUserName()) == 0) {
                            if (enterPass.getText().compareTo(users.get(i).getPass()) == 0) {
                                message.setText("Successfully signed in! You can press the back button to return to the menu");
                                Main.isSignedIn = true;
                                break;
                            }
                        }
                    }
                    if (!Main.isSignedIn) {
                        message.setText("Incorrect username or password");
                    }
                }
            }
        });

        signIn.getChildren().addAll(userName, password, message, submit, button);
        return new Scene(signIn, Main.GAME_WIDTH, Main.GAME_HEIGHT);
    }
}
