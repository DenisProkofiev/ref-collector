package ru.hellforge.refcollector;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RefcollectorApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("fxml/main.fxml"));
//        ButtonController controller = loader.getController();
//        StartPropertiesDto properties= controller.getStartProperties();
        SpringApplication.run(RefcollectorApplication.class);
    }
}
