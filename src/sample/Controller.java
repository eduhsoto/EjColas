package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //Llamamos a los identificadores y les asiginamos
    //su respectivo controlador o contenedor
    @FXML
    HBox contenedor;

    @FXML
    Label lblSize, lblQuit;

    @FXML
    TextField txtNumber, txtBuscar;

    @FXML
    Button btnAdd, btnSearch, btnDelete, btnEmpty;

    Cola cola = new Cola();//Intancia de la clase Cola

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               errorInsert();//Llamamos al método
                //No mostramos el label
               lblQuit.setVisible(false);
            }
        });

        this.btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             errorSearch();
             lblQuit.setVisible(false);
            }
        });

        this.btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Hacemos visible el label
                lblQuit.setVisible(true);
                //Lamada de métodos
                quitQueue();
                mostrar();
                sizeQueue();
            }
        });

        this.btnEmpty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cola.vaciar();
                mostrar();
                sizeQueue();
                lblQuit.setVisible(false);
            }
        });
    }

    public void quitQueue(){//Método para mostrar el valor extraido en un label
        lblQuit.setText("Valor extraído: " + cola.extraer());
        lblQuit.setStyle("-fx-font-size:18px;" + "-fx-font-family: Comic Sans MS;");
    }

    public void sizeQueue(){//Método para mostrar el tamaño de la cola en un label
        lblSize.setText("Tamaño: " +cola.getIndex());
        lblSize.setStyle("-fx-font-size:18px;" + "-fx-font-family: Comic Sans MS;");
    }

    //En caso de que el usuario haya dejado el campo vacío, se le informa
    public boolean errorInsert() {
        if (txtNumber.getText().equals("")) {
           errorEmpty();//Llamamos al método
            return false;
        }
        cola.insertar(Integer.parseInt(txtNumber.getText()));
        mostrar();
        txtNumber.clear();
        sizeQueue();
        return true;
    }

    public boolean errorSearch() {
        if (txtBuscar.getText().equals("")) {
            errorEmpty();
            return false;
        }
        buscar();
        txtBuscar.clear();
        return true;
    }

    //Método para informar
    public void errorEmpty(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No deje espacio en blanco");
        alert.setHeaderText(null);
        alert.setContentText("Inserte un valor");
        alert.showAndWait();

    }

    public void mostrar() {
        //Limpia el contenedor cada vez que se inserte uno nuevo
       contenedor.getChildren().clear();
        Nodo temp =cola.frente;//Llamamos la variable frente
        while(temp!=null) {
            //Creamos los contenedores
            HBox hBox = new HBox();
            VBox vBox = new VBox();
            ImageView hombre = null;
            //Obtenemos el valor del temporal y lo pasamos a un label
            Label lb = new Label(temp.getValor()+"");
            lb.setStyle("-fx-font-size:15px;" + "-fx-font-family: Comic Sans MS;");
            try {
                //Llamamos a la imagen
                File archivo = new File("src/sample/Imagenes/hombre.png");
                Image image = new Image(archivo.toURI().toURL().toString());
                hombre = new ImageView(image);


            } catch (Exception e) {

            }
            //Centramos el vbox
            vBox.setAlignment(Pos.CENTER);
            //el hbox tendrá adentro un vbox
            hBox.getChildren().add(vBox);
            //En el vbox el label
            vBox.getChildren().addAll(lb);
            if (hombre != null){
                vBox.getChildren().add(hombre);
            }
            contenedor.getChildren().addAll(hBox);
            temp=temp.getProximo();
        }
    }

    //Es necesario clonar de nuevo el método de mostrar para limpiar la flecha
    public void buscar(){
        contenedor.getChildren().clear();
        Nodo temp =cola.frente;
        while(temp!=null) {
            HBox hBox = new HBox();
            VBox vBox = new VBox();
            ImageView hombre = null;
            Label lb = new Label(temp.getValor()+"");
            lb.setStyle("-fx-font-size:14px;" + "-fx-font-family: Comic Sans MS;");

            try {
                File archivo = new File("src/sample/Imagenes/hombre.png");
                Image image = new Image(archivo.toURI().toURL().toString());
                hombre = new ImageView(image);


            } catch (Exception e) {

            }

            vBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(vBox);
            vBox.getChildren().addAll(lb);
            if (hombre != null){
                //Dentro del vbox mandamos la imagen
                vBox.getChildren().add(hombre);
            }
            //Si el valor de la cola actual es igual a lo que este en el text
            if (temp.getValor() == Integer.parseInt(txtBuscar.getText())) {
                    lb.setStyle("-fx-font-size:20px;" + "-fx-font-family: Comic Sans MS;");
                    //Llamamos al método
                    vBox.getChildren().addAll(insertArrow());
                }
            contenedor.getChildren().addAll(hBox);
            temp=temp.getProximo();
        }
    }

    //Métodos para insertar una flecha cuando se busca un valor
    public VBox insertArrow(){
        VBox vBox = new VBox();
        ImageView flecha = null;
        try {
                //Obtenemos la imagen
                File archivo = new File("src/sample/Imagenes/flecha.png");
                Image image = new Image(archivo.toURI().toURL().toString());
                flecha = new ImageView(image);
        } catch (Exception e) {

        }
        vBox.setAlignment(Pos.CENTER);
        if (flecha != null) {
            //Agregamos la imagen al vbox
            vBox.getChildren().add(flecha);
        }
        return vBox;//Devolvemos un vbox
    }
}



