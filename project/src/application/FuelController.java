package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

public class FuelController {

    @FXML
    private ComboBox<String> fuelTypeCombo;

    @FXML
    private TextField litersField;

    // Initialize ComboBox with fuel options
    @FXML
    public void initialize() {
        fuelTypeCombo.setItems(FXCollections.observableArrayList(
            "Petrol 93", "Petrol 95", "Diesel 50ppm", "Diesel 500ppm"
        ));
    }

    // Triggered when "Proceed to Payment" is clicked
    @FXML
    private void proceedToPayment() {
        String selectedFuel = fuelTypeCombo.getValue();
        String litersText = litersField.getText();

        if (selectedFuel == null || litersText.isEmpty()) {
            System.out.println("⚠️ Please select a fuel type and enter liters.");
            return;
        }

        try {
            double liters = Double.parseDouble(litersText);
            double pricePerLiter = getPricePerLiter(selectedFuel);
            double totalCost = liters * pricePerLiter;

            // Load CarDetails.fxml and pass fuel details
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CarDetails.fxml"));
            Parent root = loader.load();

            CarDetailsController carDetailsController = loader.getController();
            carDetailsController.setFuelDetails(selectedFuel, liters, totalCost);

            Stage stage = (Stage) fuelTypeCombo.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Car Details");

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number format for liters.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Returns price per liter based on fuel type
    private double getPricePerLiter(String fuelType) {
        switch (fuelType) {
            case "Petrol 93": return 23.50;
            case "Petrol 95": return 24.80;
            case "Diesel 50ppm": return 22.00;
            case "Diesel 500ppm": return 21.50;
            default: return 25.00; // fallback
        }
    }

    // Optional: used if FuelController is reused elsewhere
    public void setFuelDetails(String fuelType, double quantity, double price) {
        fuelTypeCombo.setValue(fuelType);
        litersField.setText(String.valueOf(quantity));
        // You could also display price in a label if needed
    }
}