//package ru.hellforge.refcollector.fxml.controllers;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//import lombok.Data;
//import ru.hellforge.refcollector.fxml.StartPropertiesDto;
//
//@Data
//public class ButtonController {
//    @FXML
//     public TextField applicationHost;
//    @FXML
//    public TextField dataBaseLocation;
//
//    private StartPropertiesDto startProperties;
//
//    @FXML
//    void startApplication(ActionEvent event) {
//        startProperties = StartPropertiesDto.builder()
//                .applicationHost(applicationHost.getAccessibleText())
//                .applicationHost(dataBaseLocation.getAccessibleText()).build();
//    }
//
//}
