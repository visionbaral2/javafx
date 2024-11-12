package com.example.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Information");

        // Labels and Text Fields
        TextField nameField = new TextField();
        TextField addressField = new TextField();
        TextField provinceField = new TextField();
        TextField cityField = new TextField();
        TextField postalCodeField = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();

        // Checkboxes
        CheckBox studentCouncilBox = new CheckBox("Student Council");
        CheckBox volunteerWorkBox = new CheckBox("Volunteer Work");

        // Radio Buttons for Majors
        RadioButton csRadio = new RadioButton("Computer Science");
        RadioButton businessRadio = new RadioButton("Business");
        ToggleGroup majorGroup = new ToggleGroup();
        csRadio.setToggleGroup(majorGroup);
        businessRadio.setToggleGroup(majorGroup);

        ComboBox<String> courseComboBox = new ComboBox<>();
        courseComboBox.getItems().addAll("Python", "C#", "Java");
        courseComboBox.setPromptText("Select a course");

        ListView<String> courseListView = new ListView<>();
        courseListView.setPrefHeight(70);
        courseListView.setPrefWidth(120);

        // Add selected course to ListView if not already added
        courseComboBox.setOnAction(e -> {
            String selectedCourse = courseComboBox.getValue();
            if (selectedCourse != null && !courseListView.getItems().contains(selectedCourse)) {
                courseListView.getItems().add(selectedCourse);
            }
        });

        // Display Area for student information
        TextArea displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setWrapText(true);

        Button displayButton = new Button("Display");
        displayButton.setOnAction(e -> {
            StringBuilder info = new StringBuilder();
            info.append("Name: ").append(nameField.getText()).append("\n")
                    .append("Address: ").append(addressField.getText()).append("\n")
                    .append("City: ").append(cityField.getText()).append("\n")
                    .append("Province: ").append(provinceField.getText()).append("\n")
                    .append("Postal Code: ").append(postalCodeField.getText()).append("\n")
                    .append("Phone: ").append(phoneField.getText()).append("\n")
                    .append("Email: ").append(emailField.getText()).append("\n");

            info.append("Courses:\n");
            for (String course : courseListView.getItems()) {
                info.append(course).append("\n");
            }

            if (studentCouncilBox.isSelected()) info.append("Student Council\n");
            if (volunteerWorkBox.isSelected()) info.append("Volunteer Work\n");

            displayArea.setText(info.toString());
        });

        // Layout for input fields
        GridPane infoGrid = new GridPane();
        infoGrid.setPadding(new Insets(10));
        infoGrid.setHgap(10);
        infoGrid.setVgap(5);
        infoGrid.add(new Label("Name:"), 0, 0);
        infoGrid.add(nameField, 1, 0);
        infoGrid.add(new Label("Address:"), 0, 1);
        infoGrid.add(addressField, 1, 1);
        infoGrid.add(new Label("Province:"), 0, 2);
        infoGrid.add(provinceField, 1, 2);
        infoGrid.add(new Label("City:"), 0, 3);
        infoGrid.add(cityField, 1, 3);
        infoGrid.add(new Label("Postal Code:"), 0, 4);
        infoGrid.add(postalCodeField, 1, 4);
        infoGrid.add(new Label("Phone Number:"), 0, 5);
        infoGrid.add(phoneField, 1, 5);
        infoGrid.add(new Label("Email:"), 0, 6);
        infoGrid.add(emailField, 1, 6);
        infoGrid.add(displayButton, 1, 7); // Position Display button at the bottom of fields

        // Right section layout
        VBox radioBox = new VBox(10, csRadio, businessRadio, courseComboBox);
        VBox checkBoxVBox = new VBox(5, studentCouncilBox, volunteerWorkBox);
        VBox rightBox = new VBox(10, radioBox, courseListView, checkBoxVBox);
        rightBox.setPadding(new Insets(10));

        // Main layout
        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(infoGrid);
        mainPane.setRight(rightBox);
        mainPane.setBottom(displayArea);
        BorderPane.setAlignment(displayArea, Pos.CENTER);

        Scene scene = new Scene(mainPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
