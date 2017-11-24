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
    TextField txtNumber, txtBuscar, txtSize;

    @FXML
    Button addBtn, buscarBtn, borrarBtn;

    Cola cola = new Cola();

    private boolean press = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!error()) {
                    return;
                }

                cola.insertar(Integer.parseInt(txtNumber.getText()) );
                mostrar();
                txtNumber.clear();
                lblSize.setText("El tamaño de la cola " +cola.getIndex());
                if (cola.getIndex() == Integer.parseInt(txtNumber.getText())){
                    System.out.println("dewfsd");
                }

                // insertar();
            }
        });

        this.buscarBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                press = true;
                buscar();

            }
        });

        this.borrarBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cola.extraer();
                mostrar();

            }
        });
    }

    public boolean error() {
        if (txtNumber.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No deje espacio en blanco");
            alert.setHeaderText(null);
            alert.setContentText("Inserte un valor");
            alert.showAndWait();

            return false;

        }
        return true;
    }



    public void mostrar() {
        contenedor.getChildren().clear();
        Nodo temp =cola.frente;
        while(temp!=null) {
            HBox hBox = new HBox();
            VBox vBox = new VBox();
            ImageView hombre = null;
            Label lb = new Label(temp.getValor()+"");
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
            if (press !=false) {
                if (temp.getValor() == Integer.parseInt(txtBuscar.getText())) {
                    lb.setStyle("-fx-font-size:20px;" + "-fx-font-family: Comic Sans MS;");
                    vBox.getChildren().addAll(insertArrow());
                }
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



