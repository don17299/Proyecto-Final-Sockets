package sample;

import view.ControladorPrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) throws Exception {

        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        laanzarVentanaInicial();
    }


    /**
     * Metodo que lanza la ventana inicial
     */
    public void laanzarVentanaInicial() throws IOException{

            // Cargo la vista
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/view/VistaPrincipal.fxml"));
            // Cargo la ventana
            Parent root = cargador.load();
            // Creo el Scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Menu");
           ControladorPrincipal controlador = cargador.getController();
           controlador.enlazarVentaYControlador(this);
            stage.showAndWait();


    }






}
