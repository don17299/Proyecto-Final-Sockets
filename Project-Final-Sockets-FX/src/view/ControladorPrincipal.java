package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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


    }




    public void enlazarVentaYControlador(Main principal)
    {
        controladorEnlace=principal;
    }


}
