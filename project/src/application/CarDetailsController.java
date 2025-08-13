package application;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class CarDetailsController {

    @FXML private ComboBox<String> brandComboBox;
    @FXML private ListView<String> modelListView;
    @FXML private Label fuelInfoLabel;

    private String fuelType;
    private double liters;
    private double totalCost;

    private final Map<String, Map<String, Double>> brandToModels = new HashMap<>();

    @FXML
    public void initialize() {
        populateCarData();
        brandComboBox.setItems(FXCollections.observableArrayList(brandToModels.keySet()));

        brandComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldBrand, newBrand) -> {
            if (newBrand != null) {
                modelListView.setItems(FXCollections.observableArrayList(brandToModels.get(newBrand).keySet()));
                modelListView.getSelectionModel().clearSelection();
                fuelInfoLabel.setText("Select a car model to view fuel details.");
            }
        });

        modelListView.getSelectionModel().selectedItemProperty().addListener((obs, oldModel, newModel) -> {
            String selectedBrand = brandComboBox.getValue();
            if (selectedBrand != null && newModel != null) {
                liters = brandToModels.get(selectedBrand).get(newModel);
                totalCost = calculateCost(liters, fuelType);
                updateFuelInfo(selectedBrand, newModel);
            }
        });
    }

    public void setFuelDetails(String fuelType, double liters, double totalCost) {
        this.fuelType = fuelType;
        this.liters = liters;
        this.totalCost = totalCost;
    }

    private void populateCarData() {
        brandToModels.put("Toyota", Map.of(
            "Corolla 1.8 Hybrid", 43.0,
            "Hilux 2.8 GD-6", 80.0,
            "Fortuner 2.4 GD-6", 80.0,
            "RAV4 2.0 GX", 60.0,
            "Yaris 1.5 Pulse", 42.0,
            "Land Cruiser Prado", 150.0,
            "Quantum 2.5D", 70.0
        ));

        brandToModels.put("Volkswagen", Map.of(
            "Golf GTI", 50.0,
            "Polo Vivo 1.4", 45.0,
            "Tiguan 1.4 TSI", 60.0,
            "Amarok V6", 80.0,
            "T-Cross 1.0 TSI", 48.0,
            "Transporter Kombi", 70.0,
            "Touareg V6 TDI", 85.0
        ));

        brandToModels.put("BMW", Map.of(
            "320i Sedan", 59.0,
            "X3 xDrive20d", 65.0,
            "X5 xDrive30d", 85.0,
            "118i Sport Line", 50.0,
            "M4 Coupe", 60.0,
            "X1 sDrive18i", 55.0,
            "740Li", 90.0
        ));

        brandToModels.put("Mercedes-Benz", Map.of(
            "C200 Sedan", 66.0,
            "GLA 200", 50.0,
            "GLE 300d", 85.0,
            "A-Class A200", 45.0,
            "Vito Tourer", 70.0,
            "E-Class E220d", 75.0,
            "S-Class S500", 100.0
        ));

        brandToModels.put("Ford", Map.of(
            "Ranger 2.2 TDCi", 80.0,
            "EcoSport 1.5", 52.0,
            "Everest 2.0 Bi-Turbo", 80.0,
            "Fiesta 1.0 EcoBoost", 42.0,
            "Figo 1.5 Trend", 45.0,
            "Mustang GT", 61.0,
            "Territory 1.8 EcoBoost", 60.0
        ));

        brandToModels.put("Hyundai", Map.of(
            "Tucson 2.0 CRDi", 62.0,
            "i20 1.2 Motion", 40.0,
            "Santa Fe 2.2D", 71.0,
            "Creta 1.5 Premium", 50.0,
            "H-1 Bus", 75.0,
            "Kona Electric", 0.0,
            "Elantra 1.6 Executive", 55.0
        ));

        brandToModels.put("Kia", Map.of(
            "Sportage 1.6T-GDi", 54.0,
            "Seltos 1.5 EX", 50.0,
            "Sorento 2.2D", 70.0,
            "Picanto 1.0 Start", 35.0,
            "Rio 1.4 Tec", 45.0,
            "Carnival 2.2D", 80.0,
            "Sonet 1.5 EX", 48.0
        ));

        brandToModels.put("Nissan", Map.of(
            "Navara 2.3D", 80.0,
            "Qashqai 1.3T", 55.0,
            "X-Trail 2.5 Acenta", 60.0,
            "Magnite 1.0 Turbo", 40.0,
            "Almera 1.5 Acenta", 45.0,
            "NP200 1.6 Base", 50.0,
            "Patrol 5.6 V8", 140.0
        ));

        brandToModels.put("Mazda", Map.of(
            "CX-5 2.5 AWD", 58.0,
            "Mazda3 1.5 Active", 51.0,
            "BT-50 3.2 SLE", 80.0,
            "Mazda2 1.5 Dynamic", 42.0,
            "CX-30 2.0", 50.0,
            "MX-5 Roadster", 45.0,
            "CX-60 2.5 Hybrid", 65.0
        ));

        brandToModels.put("Audi", Map.of(
            "A3 Sportback 35 TFSI", 50.0,
            "Q5 40 TDI", 70.0,
            "A4 2.0 TFSI", 58.0,
            "Q3 35 TFSI", 55.0,
            "A1 1.0 TFSI", 42.0,
            "Q7 45 TDI", 85.0,
            "RS3 Sportback", 60.0
        ));

        brandToModels.put("Renault", Map.of(
            "Kwid 1.0 Expression", 28.0,
            "Duster 1.5 dCi", 50.0,
            "Triber 1.0", 40.0,
            "Captur 1.3 Turbo", 48.0,
            "Clio V Turbo", 45.0,
            "Megane RS", 55.0,
            "Sandero Stepway", 42.0
        ));

        brandToModels.put("Suzuki", Map.of(
            "Swift 1.2 GL", 37.0,
            "Jimny 1.5 GLX", 40.0,
            "Vitara Brezza", 48.0,
            "Baleno 1.4 GL", 42.0,
            "Celerio 1.0 GA", 35.0,
            "Ertiga 1.5 GL", 50.0,
            "S-Presso 1.0", 30.0
        ));

        brandToModels.put("Honda", Map.of(
            "Ballade 1.5 Elegance", 45.0,
            "HR-V 1.5 Comfort", 50.0,
            "CR-V 2.0 Comfort", 60.0,
            "Jazz 1.2 Trend", 40.0,
            "Brio 1.2 Comfort", 35.0,
            "Civic RS", 55.0,
            "Accord 2.0", 65.0
        ));

        brandToModels.put("Isuzu", Map.of(
            "D-Max 3.0 LX", 80.0,
            "MU-X 3.0", 85.0,
            "KB 250", 75.0,
            "D-Max 1.9 Ddi", 70.0,
            "X-Rider 2.5", 78.0,
            "Extended Cab 2.5", 76.0,
            "Double Cab 3.0", 82.0
        ));

        brandToModels.put("Land Rover", Map.of(
            "Defender 110 D240", 90.0,
            "Discovery Sport", 85.0,
            "Range Rover Evoque", 75.0,
            "Range Rover Velar", 80.0,
            "Discovery 5", 95.0,
            "Range Rover Sport", 100.0,
            "Defender 90", 88.0
        ));

        brandToModels.put("Jaguar", Map.of(
            "XE 2.0 R-Dynamic", 60.0,
            "XF 2.0 D", 65.0,
            "F-Pace 2.0", 70.0,
            "E-Pace 2.0", 68.0,
            "F-Type Coupe", 55.0,
            "I-Pace EV", 0.0,
            "XJ Luxury", 80.0
        ));

        brandToModels.put("Volvo", Map.of(
            "XC40 T3", 55.0,
            "XC60 D4", 70.0,
            "XC90 D5", 85.0,
            "S60 T5", 60.0,
            "V40 Cross Country", 50.0,
            "V90 Cross Country", 75.0,
            "C40 Recharge", 0.0
        ));

        brandToModels.put("Mini", Map.of(
                "Cooper Hatch", 40.0,
                "Cooper S", 45.0,
                "Countryman", 50.0,
                "Clubman", 48.0,
                "John Cooper Works", 55.0,
                "Convertible", 42.0,
                "Electric Hatch", 0.0
            ));

            brandToModels.put("Chevrolet", Map.of(
                "Spark 1.2", 38.0,
                "Aveo 1.6", 42.0,
                "Cruze 1.4T", 50.0,
                "Trailblazer 2.8D", 75.0,
                "Utility 1.4", 45.0,
                "Captiva 2.4", 60.0,
                "Orlando 1.8", 55.0
            ));

            brandToModels.put("Fiat", Map.of(
                "500 Lounge", 40.0,
                "Panda 1.2", 35.0,
                "Tipo Sedan", 45.0,
                "Doblo Cargo", 50.0,
                "500X Cross", 48.0,
                "Strada 1.4", 42.0,
                "Punto Evo", 38.0
            ));

            brandToModels.put("Jeep", Map.of(
                "Wrangler Unlimited", 80.0,
                "Grand Cherokee", 85.0,
                "Renegade 1.4T", 55.0,
                "Compass 1.4T", 60.0,
                "Cherokee 3.2", 75.0,
                "Gladiator", 90.0,
                "Commander", 88.0
            ));

            brandToModels.put("Peugeot", Map.of(
                "208 Active", 42.0,
                "2008 GT Line", 50.0,
                "3008 Allure", 60.0,
                "5008 GT", 65.0,
                "Partner Panel Van", 55.0,
                "Expert Crew Van", 70.0,
                "Landtrek 1.9D", 75.0
            ));

            brandToModels.put("Citroën", Map.of(
                "C3 Feel", 40.0,
                "C5 Aircross", 60.0,
                "C4 Cactus", 50.0,
                "Berlingo Van", 55.0,
                "C3 Aircross", 48.0,
                "Dispatch Panel Van", 65.0,
                "DS3 Crossback", 58.0
            ));

            brandToModels.put("Subaru", Map.of(
                "Forester 2.0i", 60.0,
                "Outback 2.5i", 65.0,
                "XV 2.0i", 55.0,
                "Impreza 2.0i", 50.0,
                "WRX STI", 70.0,
                "Legacy 2.5i", 58.0,
                "BRZ Coupe", 62.0
            ));

            brandToModels.put("GWM", Map.of(
                "Steed 5 2.2", 50.0,
                "P-Series 2.0D", 75.0,
                "M4 1.5", 45.0,
                "Haval H6", 60.0,
                "Haval Jolion", 55.0,
                "Haval H9", 70.0,
                "Tank 300", 80.0
            ));

            brandToModels.put("Mahindra", Map.of(
                "Scorpio-N", 65.0,
                "XUV700", 70.0,
                "Thar 2.2D", 60.0,
                "Bolero 2.5D", 55.0,
                "KUV100 NXT", 40.0,
                "XUV300", 50.0,
                "Pik-Up S11", 75.0
            ));

            brandToModels.put("Chery", Map.of(
                "Tiggo 4 Pro", 50.0,
                "Tiggo 7 Pro", 60.0,
                "Tiggo 8 Pro", 70.0,
                "QQ Ice Cream EV", 0.0,
                "Arrizo 5", 45.0,
                "Tiggo 5x", 55.0,
                "Omoda C5", 58.0
            ));
            brandToModels.put("Alfa Romeo", Map.of(
                    "Giulia 2.0T", 60.0,
                    "Stelvio 2.0T", 70.0,
                    "Mito 1.4T", 45.0,
                    "Giulietta 1.8T", 50.0,
                    "Tonale Hybrid", 65.0,
                    "Spider 2.2", 55.0,
                    "Brera 3.2 V6", 58.0
                ));

                brandToModels.put("Daihatsu", Map.of(
                    "Terios 1.5", 45.0,
                    "Charade 1.3", 38.0,
                    "Sirion 1.5", 40.0,
                    "Mira 660cc", 30.0,
                    "Copen Convertible", 35.0,
                    "Gran Max Panel Van", 50.0,
                    "Hijet Truck", 42.0
                ));

                brandToModels.put("Datsun", Map.of(
                    "Go 1.2 Mid", 35.0,
                    "Go+ 1.2 Panel Van", 38.0,
                    "Go+ 7-Seater", 40.0,
                    "Go Lux", 37.0,
                    "Go CVT", 39.0,
                    "Go+ CVT", 41.0,
                    "Go Remix", 36.0
                ));

                brandToModels.put("Genesis", Map.of(
                    "G70 2.0T", 65.0,
                    "G80 3.5T", 75.0,
                    "GV70 SUV", 70.0,
                    "GV80 SUV", 85.0,
                    "G90 Luxury", 90.0,
                    "Electrified GV70", 0.0,
                    "G60 Concept", 68.0
                ));

                brandToModels.put("Opel", Map.of(
                    "Corsa 1.2T", 45.0,
                    "Astra 1.6T", 55.0,
                    "Crossland X", 50.0,
                    "Mokka X", 52.0,
                    "Combo Life", 60.0,
                    "Zafira Life", 65.0,
                    "Vivaro Panel Van", 70.0
                ));

                brandToModels.put("Tata", Map.of(
                    "Indica 1.4", 35.0,
                    "Bolt 1.2T", 40.0,
                    "Tiago 1.2", 42.0,
                    "Tigor 1.2", 44.0,
                    "Nexon EV", 0.0,
                    "Harrier 2.0D", 60.0,
                    "Safari 2.0D", 65.0
                ));

                brandToModels.put("BYD", Map.of(
                    "Atto 3 EV", 0.0,
                    "Dolphin EV", 0.0,
                    "Seal EV", 0.0,
                    "Tang EV", 0.0,
                    "Han EV", 0.0,
                    "Song Plus EV", 0.0,
                    "e6 MPV", 0.0
                ));

                brandToModels.put("Proton", Map.of(
                    "X50 1.5T", 50.0,
                    "X70 1.8T", 60.0,
                    "Saga 1.3", 40.0,
                    "Persona 1.6", 45.0,
                    "Iriz 1.3", 42.0,
                    "Exora MPV", 55.0,
                    "Prevé 1.6T", 48.0
                ));

                brandToModels.put("Smart", Map.of(
                    "Fortwo Coupe", 35.0,
                    "Fortwo Cabrio", 38.0,
                    "Forfour Hatch", 40.0,
                    "EQ Fortwo", 0.0,
                    "EQ Forfour", 0.0,
                    "Crossblade", 42.0,
                    "Roadster", 45.0
                ));

                brandToModels.put("Tesla", Map.of(
                    "Model 3", 0.0,
                    "Model Y", 0.0,
                    "Model S", 0.0,
                    "Model X", 0.0,
                    "Cybertruck", 0.0,
                    "Roadster", 0.0,
                    "Semi", 0.0
                ));

                brandToModels.put("Rivian", Map.of(
                    "R1T Pickup", 0.0,
                    "R1S SUV", 0.0,
                    "EDV Van", 0.0,
                    "R2 Compact SUV", 0.0,
                    "R3 Hatchback", 0.0,
                    "R3X Crossover", 0.0,
                    "Adventure Edition", 0.0
                ));

                brandToModels.put("Lucid", Map.of(
                    "Air Pure", 0.0,
                    "Air Touring", 0.0,
                    "Air Grand Touring", 0.0,
                    "Air Sapphire", 0.0,
                    "Gravity SUV", 0.0,
                    "Air Dream Edition", 0.0,
                    "Air Performance", 0.0
                ));

                brandToModels.put("Ram", Map.of(
                    "1500 Laramie", 85.0,
                    "1500 Rebel", 88.0,
                    "1500 TRX", 95.0,
                    "2500 Heavy Duty", 100.0,
                    "3500 Mega Cab", 110.0,
                    "ProMaster City", 75.0,
                    "ProMaster Cargo", 78.0
                ));

                brandToModels.put("Hummer", Map.of(
                    "H1 Alpha", 120.0,
                    "H2 SUV", 100.0,
                    "H3 Adventure", 90.0,
                    "EV Pickup", 0.0,
                    "EV SUV", 0.0,
                    "H2 SUT", 95.0,
                    "H3T Truck", 85.0
                ));

                brandToModels.put("Bugatti", Map.of(
                    "Chiron", 250.0,
                    "Veyron", 240.0,
                    "Divo", 260.0,
                    "Centodieci", 270.0,
                    "Bolide", 280.0,
                    "La Voiture Noire", 300.0,
                    "EB110", 230.0
                ));

                brandToModels.put("Koenigsegg", Map.of(
                    "Jesko", 300.0,
                    "Regera", 290.0,
                    "Agera RS", 280.0,
                    "Gemera", 270.0,
                    "CCX", 260.0,
                    "One:1", 310.0,
                    "CCR", 250.0
                ));

                brandToModels.put("Pagani", Map.of(
                    "Huayra", 280.0,
                    "Zonda F", 270.0,
                    "Zonda Cinque", 290.0,
                    "Huayra Roadster", 285.0,
                    "Zonda R", 295.0,
                    "Utopia", 300.0,
                    "Zonda Tricolore", 310.0
                ));

                brandToModels.put("McLaren", Map.of(
                    "720S", 250.0,
                    "Artura", 240.0,
                    "GT", 230.0,
                    "765LT", 260.0,
                    "P1", 270.0,
                    "Senna", 280.0,
                    "Elva", 290.0
                ));

                brandToModels.put("Ferrari", Map.of(
                    "Roma", 220.0,
                    "Portofino M", 230.0,
                    "F8 Tributo", 240.0,
                    "SF90 Stradale", 250.0,
                    "296 GTB", 245.0,
                    "812 Superfast", 260.0,
                    "Purosangue SUV", 270.0
                ));
                brandToModels.put("Bentley", Map.of(
                        "Continental GT", 90.0,
                        "Flying Spur", 95.0,
                        "Bentayga V8", 100.0,
                        "Mulsanne", 110.0,
                        "Azure", 105.0,
                        "Brooklands", 108.0,
                        "Arnage R", 102.0
                    ));

                    brandToModels.put("Rolls-Royce", Map.of(
                        "Phantom", 120.0,
                        "Ghost", 115.0,
                        "Wraith", 110.0,
                        "Cullinan", 125.0,
                        "Dawn", 118.0,
                        "Silver Shadow", 100.0,
                        "Spectre EV", 0.0
                    ));

                    brandToModels.put("Lamborghini", Map.of(
                        "Huracán EVO", 85.0,
                        "Aventador S", 90.0,
                        "Urus SUV", 95.0,
                        "Gallardo", 88.0,
                        "Revuelto", 92.0,
                        "Murciélago", 89.0,
                        "Diablo", 87.0
                    ));

                    brandToModels.put("Porsche", Map.of(
                        "911 Carrera", 70.0,
                        "Cayenne", 75.0,
                        "Macan", 65.0,
                        "Panamera", 80.0,
                        "Taycan EV", 0.0,
                        "718 Boxster", 60.0,
                        "718 Cayman", 60.0
                    ));

                    brandToModels.put("Lotus", Map.of(
                        "Evora GT", 55.0,
                        "Elise Sport", 50.0,
                        "Exige Cup", 52.0,
                        "Emira", 58.0,
                        "Eletre EV", 0.0,
                        "Europa S", 54.0,
                        "Esprit V8", 56.0
                    ));

                    brandToModels.put("Aston Martin", Map.of(
                        "DB11", 75.0,
                        "Vantage", 70.0,
                        "DBX SUV", 80.0,
                        "Rapide S", 78.0,
                        "Vanquish", 76.0,
                        "Valhalla", 82.0,
                        "Virage", 74.0
                    ));

                    brandToModels.put("Saab", Map.of(
                        "9-3 Aero", 60.0,
                        "9-5 Sedan", 65.0,
                        "9-4X SUV", 70.0,
                        "900 Turbo", 55.0,
                        "9-3 Convertible", 58.0,
                        "9-5 Estate", 66.0,
                        "9-7X", 72.0
                    ));

                    brandToModels.put("Daewoo", Map.of(
                        "Matiz", 35.0,
                        "Lanos", 40.0,
                        "Nubira", 45.0,
                        "Leganza", 50.0,
                        "Tacuma", 48.0,
                        "Kalos", 42.0,
                        "Rezzo", 46.0
                    ));

                    brandToModels.put("Perodua", Map.of(
                        "Axia", 30.0,
                        "Myvi", 35.0,
                        "Bezza", 38.0,
                        "Alza", 40.0,
                        "Viva", 32.0,
                        "Kembara", 36.0,
                        "Rusa Van", 42.0
                    ));

                    brandToModels.put("Scion", Map.of(
                        "tC Coupe", 45.0,
                        "xB Wagon", 50.0,
                        "xD Hatch", 42.0,
                        "FR-S", 48.0,
                        "iA Sedan", 40.0,
                        "iM Hatch", 44.0,
                        "iQ Micro", 35.0
                    ));

                    brandToModels.put("Holden", Map.of(
                        "Commodore SS", 70.0,
                        "Barina", 45.0,
                        "Captiva", 60.0,
                        "Colorado", 75.0,
                        "Cruze", 55.0,
                        "Trax", 50.0,
                        "Ute SS-V", 72.0
                    ));

                    brandToModels.put("SEAT", Map.of(
                        "Ibiza", 42.0,
                        "Leon", 50.0,
                        "Arona", 48.0,
                        "Ateca", 55.0,
                        "Tarraco", 60.0,
                        "Toledo", 52.0,
                        "Alhambra", 65.0
                    ));

                    brandToModels.put("Skoda", Map.of(
                        "Fabia", 40.0,
                        "Octavia", 50.0,
                        "Superb", 60.0,
                        "Kodiaq", 70.0,
                        "Karoq", 65.0,
                        "Scala", 55.0,
                        "Rapid", 45.0
                    ));

        }

    private double calculateCost(double liters, String fuelType) {
        switch (fuelType) {
            case "Petrol 93": return liters * 23.50;
            case "Petrol 95": return liters * 24.80;
            case "Diesel 50ppm": return liters * 22.00;
            case "Diesel 500ppm": return liters * 21.50;
            default: return liters * 25.00;
        }
    }

    private void updateFuelInfo(String brand, String model) {
        fuelInfoLabel.setText(
            "🚗 Brand: " + brand +
            "\n📍 Model: " + model +
            "\n⛽ Fuel Type: " + fuelType +
            "\n📦 Liters: " + String.format("%.2f", liters) +
            "\n💸 Total Cost: R" + String.format("%.2f", totalCost)
        );
    }

    @FXML
    private void goToPayment() {
        String brand = brandComboBox.getValue();
        String model = modelListView.getSelectionModel().getSelectedItem();
        if (brand == null || model == null) {
            System.out.println("⚠️ Please select both brand and model.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            Parent root = loader.load();

            PaymentController paymentController = loader.getController();
            paymentController.setPaymentAmount(totalCost);
            paymentController.setCarDetails(brand + " " + model, fuelType, liters);

            Stage stage = (Stage) fuelInfoLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Payment");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}