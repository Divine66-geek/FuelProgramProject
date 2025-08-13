package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ForgotPasswordController {

    @FXML private TextField usernameField;
    @FXML private Label messageLabel;

    public void handleReset(ActionEvent event) {
        String username = usernameField.getText().trim();

        if (username.isEmpty()) {
            messageLabel.setText("Username is required before resetting your password.");
        } else {
            // Simulate sending reset link
            messageLabel.setText("A password reset link has been sent to the email associated with \"" + username + "\".");
        }
    }

    public void goToLogin(ActionEvent event) {
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene loginScene = new Scene(loginRoot);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.setTitle("Login");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}