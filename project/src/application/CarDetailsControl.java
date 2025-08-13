package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;

import java.io.File;

public class CarDetailsControl {

    @FXML private TextField regNoField;
    @FXML private TextField regDateField;
    @FXML private TextField chassisNoField;
    @FXML private TextField engineNoField;
    @FXML private Label messageLabel;
    @FXML private Label usernameLabel;
    @FXML private ImageView frontPlateView;
    @FXML private ImageView backPlateView;

    private File frontPlateFile;
    private File backPlateFile;

    public void setUsername(String username) {
        usernameLabel.setText("Welcome, " + username + "!");
    }

    public void handleFrontUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Front Plate Image");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        frontPlateFile = fileChooser.showOpenDialog(null);
        if (frontPlateFile != null) {
            frontPlateView.setImage(new Image(frontPlateFile.toURI().toString()));
        }
    }

    public void handleBackUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Back Plate Image");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        backPlateFile = fileChooser.showOpenDialog(null);
        if (backPlateFile != null) {
            backPlateView.setImage(new Image(backPlateFile.toURI().toString()));
        }
    }

    public void handleSubmit(ActionEvent event) {
        String regNo = regNoField.getText().trim();
        String regDate = regDateField.getText().trim();
        String chassisNo = chassisNoField.getText().trim();
        String engineNo = engineNoField.getText().trim();

        if (regNo.isEmpty() || regDate.isEmpty() || chassisNo.isEmpty() || engineNo.isEmpty()) {
            messageLabel.setText("Please fill in all fields.");
        } else if (frontPlateFile == null || backPlateFile == null) {
            messageLabel.setText("Please upload both front and back plate images.");
        } else {
            System.out.println("Car Details Submitted:");
            System.out.println("Reg No: " + regNo);
            System.out.println("Reg Date: " + regDate);
            System.out.println("Chassis No: " + chassisNo);
            System.out.println("Engine No: " + engineNo);
            System.out.println("Front Plate: " + frontPlateFile.getAbsolutePath());
            System.out.println("Back Plate: " + backPlateFile.getAbsolutePath());
            messageLabel.setText("Car details and images submitted successfully.");
        }
    }
}