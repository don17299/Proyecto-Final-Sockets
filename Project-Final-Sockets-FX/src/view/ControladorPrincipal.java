package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {
    //labeles
    @FXML
    Label labelM1;
    @FXML
    Label labelM2;
    @FXML
    Label lMConfirmacion;
    //labeles del gridpane
    @FXML
    Label label00;
    @FXML
    Label label01;
    @FXML
    Label label10;
    @FXML
    Label label11;
    @FXML
    Label label20;
    @FXML
    Label label21;
    @FXML
    Label label30;
    @FXML
    Label label31;
    @FXML
    Label label40;
    @FXML
    Label label41;
    @FXML
    Label label50;
    @FXML
    Label label51;

    //botones
    @FXML
    Button realizar;

    //campos de texto
    @FXML
    TextField primerDato;
    @FXML
    TextField segundoDato;

    @FXML
    private ComboBox<String> comboOpciones;


    @FXML
    private TextField datos;

    @FXML
    private Label labelkk=new Label();

    @FXML
    ImageView imagen;

    Main controladorEnlace;
    ObservableList<String> list = FXCollections.observableArrayList("Crear cuenta","Crear bolsillo",
            "Cancelar bolsillo",
            "Cancelar cuenta",
            "Depositar",
            "Retirar",
            "Trasladar",
            "Consultar numero de cuentas");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        comboOpciones.setItems(list);
        labelkk.setText("NOOOOOOOO");

        segundoDato.setVisible(false);
<<<<<<< HEAD
=======

>>>>>>> a89a6d8c6e9422bea8a75dd548a3a7b1770b4a7c

        reconocerTexto();
    }

    public void reconocerTexto() {
        primerDato.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && !newValue.isEmpty()) {
                realizar.setDisable(false);
            } else {
                realizar.setDisable(true);
            }
        });
    }




    public void enlazarVentaYControlador(Main principal)
    {
        controladorEnlace=principal;
    }


}
