package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PaymentController {

    @FXML
    private Label amountLabel;

    @FXML
    private Label carDetailsLabel;

    private double paymentAmount;
    private String car;
    private String fuelType;
    private double liters;

    public void setPaymentAmount(double amount) {
        this.paymentAmount = amount;
        if (amountLabel != null) {
            amountLabel.setText("Amount to Pay: R" + String.format("%.2f", amount));
        }
    }

    public void setCarDetails(String car, String fuelType, double liters) {
        this.car = car;
        this.fuelType = fuelType;
        this.liters = liters;
        if (carDetailsLabel != null) {
            carDetailsLabel.setText(
                "Car: " + car + "\nFuel: " + fuelType + "\nLiters: " + String.format("%.2f", liters)
            );
        }
    }

    @FXML
    private void confirmPayment() {
        // Simulate payment confirmation
        System.out.println("✅ Payment Confirmed");
        System.out.println("Receipt:");
        System.out.println("----------------------------");
        System.out.println("Car: " + car);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Liters: " + String.format("%.2f", liters));
        System.out.println("Amount Paid: R" + String.format("%.2f", paymentAmount));
        System.out.println("----------------------------");

        // Optional: Show confirmation popup
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Payment Successful");
        alert.setHeaderText("Thank you!");
        alert.setContentText("Payment of R" + String.format("%.2f", paymentAmount) + " has been processed.");
        alert.showAndWait();

        // TODO: Add database logging or screen navigation here
    }
}