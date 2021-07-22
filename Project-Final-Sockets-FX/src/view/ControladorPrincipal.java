package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    TextArea informacion;

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
            "Consultar Numero de Cuentas",
            "Cargar Archivo");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        comboOpciones.setItems(list);

        reconocerTexto();
        reconocerCombo();
        informacion.setWrapText(true);

    }

    public void reconocerTexto() {
        primerDato.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && !newValue.trim().isEmpty()) {
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
                    labelMC.setText("Ingrese su nombre y apellido:");
                    break;
                case "Crear Bolsillo":
                    opcion = 2;
                    labelMC.setText("Ingrese su número de cuenta: ");
                    break;
                case "Cancelar Bolsillo":
                    opcion = 3;
                    labelMC.setText("Ingrese el numero de cuenta del bolsillo:");
                    break;
                case "Cancelar Cuenta":
                    opcion = 4;
                    labelMC.setText("Ingrese su número de cuenta: ");
                    break;
                case "Depositar":
                    opcion = 5;
                    labelMC.setText("Ingrese el numero de su cuenta y la cantidad a depositar separados por espacio:");
                    break;
                case "Retirar":
                    opcion = 6;
                    labelMC.setText("Ingrese el numero de su cuenta y la cantidad a retirar separados por espacio:");
                    break;
                case "Trasladar":
                    opcion = 7;
                    labelMC.setText("Ingrese el numero de su cuenta y el saldo a trasladar al bolsillo separados por un espacio:");
                    break;
                case "Consultar Saldo de Cuenta":
                    opcion = 8;
                    labelMC.setText("Ingrese su numero de cuenta de ahorros o un bolsillo:");
                    break;
                case "Consultar Numero de Cuentas":
                    opcion = 9;
                    labelMC.setText("");
                    primerDato.setDisable(true);
                    realizar.setDisable(false);
                    break;
                case "Cargar Archivo":
                    opcion=10;
                    labelMC.setText("Ingrese la ruta del archivo");
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
        String[] mensaje= message.split(":");
        if(mensaje[0].equals("ERR")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error del Banco");
                alert.setHeaderText("Error de Transacción");
                alert.setContentText(mensaje[1]);
                alert.showAndWait();

        }else {
            informacion.setText(message);
        }
    }

    public void enlazarVentanaYControlador(Main principal)
    {
        controladorEnlace=principal;
    }


}
