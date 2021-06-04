package utils;

import controllers.JsonService;
import controllers.VirtualTourController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Player;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Virtual Tour Class which loads the view from 'mainTour.fxml and sets the Controller
 */
public class VirtualTour extends Application {

    private VirtualTourController virtualTourController;

    /**
     * main method to initate the start
     *
     * @param args args fetched from main class
     */
    public static void main(String args[]) {
        launch(args);
        System.exit(0);
    }

    /**
     * Creates view from specified fxml file
     *
     * @param stage java fxml container
     */
    public void start(Stage stage) {

        try {
            //FXML loader to get the fxml file and create the view
            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(getClass().getResource("/fxml/mainTour.fxml"));
            Parent root = fxml.load();

            //Call Json service to load player
            JsonService service = new JsonService();
            Player player = service.create();

            //Retrieve controller from loader and pass the player
            this.virtualTourController = this.passPlayerToController(fxml, player);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }

    /**
     * Set the player to the controller associated with the FXML loader
     *
     * @param loader FXML loader
     * @param player Player that needs to be set to the controller
     * @return controller with player information
     */
    private VirtualTourController passPlayerToController(FXMLLoader loader, Player player) {
        VirtualTourController virtualTourController = loader.getController();
        virtualTourController.setPlayer(player);
        return virtualTourController;
    }
}
