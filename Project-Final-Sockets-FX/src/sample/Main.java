package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
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

        lanzarVentanaInicial();
    }


    /**
     * Metodo que lanza la ventana inicial
     */
    public void lanzarVentanaInicial() throws IOException{

            // Cargo la vista
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/view/VistaPrincipal.fxml"));
            // Cargo la ventana
            Parent root = cargador.load();
            // Creo el Scene

            Stage stage = new Stage();

            ToolBar barraLateral = new ToolBar();
            Button button1 = new Button("Button 1");
            barraLateral.getItems().add(button1);

            VBox vBox = new VBox(barraLateral);
            Scene scene = new Scene(root);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Menu");
           ControladorPrincipal controlador = cargador.getController();
           controlador.enlazarVentaYControlador(this);
           stage.showAndWait();
    }






}
