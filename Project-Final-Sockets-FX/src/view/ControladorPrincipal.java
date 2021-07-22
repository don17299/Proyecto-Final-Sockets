package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.interfaz.AppClientG;
import model.interfaz.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {
    //labeles
    @FXML
    Label labelMC;
    @FXML
    Label labelMS;

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
    private ComboBox<String> comboOpciones;

    @FXML
    ImageView imagen;

    private int opcion;

    Main controladorEnlace;
    ObservableList<String> list = FXCollections.observableArrayList("Crear Cuenta","Crear Bolsillo",
            "Cancelar Bolsillo",
            "Cancelar Cuenta",
            "Depositar",
            "Retirar",
            "Trasladar",
            "Consultar Saldo de Cuenta",
            "Consultar Numero de Cuentas");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        comboOpciones.setItems(list);

        reconocerTexto();
        reconocerCombo();

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

    public void reconocerCombo(){
        comboOpciones.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            //System.out.println(newValue);
            switch (newValue) {
                case "Crear Cuenta":
                    opcion = 1;
                    labelMC.setText("Ingrese su nombre y apellido");
                    break;
                case "Crear Bolsillo":
                    opcion = 2;
                    labelMC.setText("Ingrese su número de cuenta : ");
                    break;
                case "Cancelar Bolsillo":
                    opcion = 3;
                    labelMC.setText("Ingrese el numero de cuenta del bolsillo");
                    break;
                case "Cancelar Cuenta":
                    opcion = 4;
                    labelMC.setText("Ingrese su número de cuenta : ");
                    break;
                case "Depositar":
                    opcion = 5;
                    labelMC.setText("Ingrese el numero de su cuenta y la cantidad a depositar separados por espacio");
                    break;
                case "Retirar":
                    opcion = 6;
                    labelMC.setText("Ingrese el numero de su cuenta y la cantidad a retirar separados por espacio");
                    break;
                case "Trasladar":
                    opcion = 7;
                    labelMC.setText("Ingrese el numero de su cuenta y el saldo a trasladar al bolsillo separados por un espacio");
                    break;
                case "Consultar Saldo de Cuenta":
                    opcion = 8;
                    labelMC.setText("Ingrese su numero de cuenta de ahorros o un bolsillo");
                    break;
                case "Consultar Numero de Cuentas":
                    opcion = 9;
                    primerDato.setDisable(true);
                    realizar.setDisable(false);
                    break;
                default:
                    System.out.println("error");
            }
        });
    }

    @FXML
    public void realizarButton(){
        primerDato.setDisable(false);
        controladorEnlace.setCliente(new AppClientG());
        String numCuenta=numCuenta =primerDato.getText();
        try {
            controladorEnlace.getCliente().init(opcion,numCuenta,this);
            primerDato.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void mostrarMensajeServidor(String message){
        labelMS.setText(message);
    }

    public void enlazarVentanaYControlador(Main principal)
    {
        controladorEnlace=principal;
    }


}
