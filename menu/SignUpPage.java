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

public class SignUpPage {
    public static Scene makeSignUpPage(Button button, ArrayList<User> users) {
        VBox signUp = new VBox();
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
                boolean isUnique = true;
                if (enterName.getText().compareTo("") != 0) {
                    for (int i = 0; i < users.size(); i++) {
                        if (enterName.getText().compareTo(users.get(i).getUserName()) == 0) {
                            isUnique = false;
                            message.setText("That username is already taken!");
                            break;
                        }
                    }
                    if (isUnique) {
                        message.setText("Success! Go to the sign in page to sign in with your new account");
                        database.MakeNewUser.makeUser(enterName.getText(), enterPass.getText(), users);
                        System.out.println(users.get(users.size()-1).getUserName());
                    }
                }
            }
        });

        signUp.getChildren().addAll(userName, password, message, submit, button);
        return new Scene(signUp, Main.GAME_WIDTH, Main.GAME_HEIGHT);
    }
}
