package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class RegisterController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;

    public void handleRegister(ActionEvent event) {
        System.out.println("Registering: " + usernameField.getText());
        // Add registration logic here
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

    // ✅ Step 2: Link Register button to Car Details page
    public void goToCarDetails(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("car_details.fxml"));
            Parent carDetailsRoot = loader.load();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene carDetailsScene = new Scene(carDetailsRoot);
            window.setScene(carDetailsScene);
            window.setTitle("Car Details");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}