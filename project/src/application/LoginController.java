package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    // Triggered when Enter is pressed in password field
    @FXML
    public void onEnterPressed() {
        handleLogin();
    }

    public void handleLogin() {
        String user = usernameField.getText().trim();
        String pass = passwordField.getText().trim();
        System.out.println("Login attempt: " + user + " / " + pass);

        if (user.equals("admin") && pass.equals("1234")) {
            System.out.println("Login successful.");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Fuel.fxml"));
                Parent root = loader.load();

                // Access FuelController and pass initial fuel details
                FuelController fuelController = loader.getController();
                fuelController.setFuelDetails("Petrol", 0.0, 0.0); // Default values

                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Car Details");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid login credentials.");
        }
    }

    public void goToRegister() throws Exception {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("register.fxml")));
        stage.setScene(scene);
        stage.setTitle("Register");
    }

    public void goToForgotPassword() throws Exception {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("forgot_password.fxml")));
        stage.setScene(scene);
        stage.setTitle("Forgot Password");
    }
}