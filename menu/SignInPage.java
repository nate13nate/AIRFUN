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
    public static Scene makeSignInPage(Button button) {
        VBox signIn = new VBox();
        HBox password = new HBox();
        HBox userName = new HBox();
        Label name = new Label("Name");
        Label pass = new Label("Password");
        Label error = new Label("That username is taken!");
        TextField enterName = new TextField();
        TextField enterPass = new TextField();
        Button submit = new Button();

        submit.setText("Submit");
        final ArrayList<User> users = Database.getHighScores();

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
                            signIn.getChildren().add(error);
                            break;
                        }
                    }

                    if (isUnique) {
                        System.out.println("Success! \n Username: " + enterName.getText() +
                                "\nPassword: " + enterPass.getText());
                        database.MakeNewUser.makeUser(enterName.getText(), enterPass.getText());
                    }
                }
            }
        });

        System.out.println(button);
        signIn.getChildren().addAll(password, userName, submit, button);
        return new Scene(signIn, Main.GAME_WIDTH, Main.GAME_HEIGHT);
    }
}
