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

    @FXML
    HBox contenedor;

    @FXML
    Label lblSize, lblQuit;

    @FXML
    TextField txtNumber, txtBuscar;

    @FXML
    Button btnAdd, btnSearch, btnDelete, btnEmpty;

    Cola cola = new Cola();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               errorInsert();
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
                lblQuit.setVisible(true);
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

    public void quitQueue(){
        lblQuit.setText("Valor extraído: " + cola.extraer());
        lblQuit.setStyle("-fx-font-size:22px;" + "-fx-font-family: Comic Sans MS;");
    }

    public void sizeQueue(){
        lblSize.setText("Tamaño: " +cola.getIndex());
        lblSize.setStyle("-fx-font-size:22px;" + "-fx-font-family: Comic Sans MS;");
    }

    public boolean errorInsert() {
        if (txtNumber.getText().equals("")) {
           errorEmpty();
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

    public void errorEmpty(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No deje espacio en blanco");
        alert.setHeaderText(null);
        alert.setContentText("Inserte un valor");
        alert.showAndWait();

    }

    public void mostrar() {
        contenedor.getChildren().clear();
        Nodo temp =cola.frente;
        while(temp!=null) {
            HBox hBox = new HBox();
            VBox vBox = new VBox();
            ImageView hombre = null;
            Label lb = new Label(temp.getValor()+"");
            lb.setStyle("-fx-font-size:15px;" + "-fx-font-family: Comic Sans MS;");
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
                vBox.getChildren().add(hombre);
            }
            contenedor.getChildren().addAll(hBox);
            temp=temp.getProximo();
        }
    }

    public void buscar(){
        contenedor.getChildren().clear();
        Nodo temp =cola.frente;
        while(temp!=null) {
            HBox hBox = new HBox();
            VBox vBox = new VBox();
            ImageView hombre = null;
            Label lb = new Label(temp.getValor()+"");
            lb.setStyle("-fx-font-size:15px;" + "-fx-font-family: Comic Sans MS;");

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
                vBox.getChildren().add(hombre);
            }

                if (temp.getValor() == Integer.parseInt(txtBuscar.getText())) {
                    lb.setStyle("-fx-font-size:20px;" + "-fx-font-family: Comic Sans MS;");
                    vBox.getChildren().addAll(insertArrow());
                }
            contenedor.getChildren().addAll(hBox);
            temp=temp.getProximo();
        }
    }

    public VBox insertArrow(){
        VBox vBox = new VBox();
        ImageView flecha = null;
        try {
                File archivo = new File("src/sample/Imagenes/flecha.png");
                Image image = new Image(archivo.toURI().toURL().toString());
                flecha = new ImageView(image);
        } catch (Exception e) {

        }
        vBox.setAlignment(Pos.CENTER);
        if (flecha != null) {
            vBox.getChildren().add(flecha);
        }
        return vBox;
    }
}



