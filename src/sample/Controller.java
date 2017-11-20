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
    TextField txtNumber, txtBuscar;

    @FXML
    Button addBtn, buscarBtn, borrarBtn;

    private int index = 0;
    private Nodo frente  = null;

  private Nodo ultimo;
   // Cola cola = new Cola();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                insertar();
            }
        });

        this.buscarBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buscar();
            }
        });

        this.borrarBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                extraer();
            }
        });

    }

    public void insertar(){
        int valor = Integer.parseInt(txtNumber.getText());
        Nodo nuevo = new Nodo(valor);
        if (frente == null){
            frente = nuevo;
        }else{
            Nodo temp = frente;
           while (temp.getProximo() != null) {
               temp = temp.getProximo();
           }
            temp.setProx(nuevo);
        }
        index++;
        mostrar();
    }

    public void mostrar(){
      //if (frente != null){
            contenedor.getChildren().clear();
            Nodo temp = frente;

            //while(temp!=null) { //existe otra forma para mostrar los valores de la cola
            for (int x= 0; x<index; x++){
                HBox hBox = new HBox();
                VBox vBox = new VBox();
                ImageView hombre = null;
                try {
                    File archivo = new File("src/sample/Imagenes/hombre.png");
                    Image image = new Image(archivo.toURI().toURL().toString());
                    hombre = new ImageView(image);

                } catch (Exception e) {

                }
                vBox.setAlignment(Pos.CENTER);


                Label label = new Label(String.valueOf(temp.getValor()));
                label.setStyle("-fx-font-size:18px;");
                hBox.getChildren().add(vBox);
                vBox.getChildren().add(label);
                if (hombre != null) vBox.getChildren().add(hombre);
                contenedor.getChildren().add(hBox);
                temp = temp.getProximo();
            }
        //}

    }

    //Método para búscar en la cola
    public void buscar(){
        contenedor.getChildren().clear();
        Nodo temp = frente;
        if (frente == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Practica Colas");
            alert.setHeaderText("La cola está vacía.");
            alert.setContentText("Intentalo más tarde...");
            alert.showAndWait();
        }else{
            for (int x= 0; x<index; x++){
                HBox hBox = new HBox();
                VBox vBox = new VBox();
                ImageView mujer = null;
                try {
                    File archivo = new File("src/sample/Imagenes/hombre.png");
                    Image image = new Image(archivo.toURI().toURL().toString());
                    mujer = new ImageView(image);

                } catch (Exception e) {

                }
                vBox.setAlignment(Pos.CENTER);


                Label cola = new Label(String.valueOf(temp.getValor()));
                cola.setStyle("-fx-font-size:18px;");
                if (String.valueOf(temp.getValor()).equals(txtBuscar.getText())){
                    cola.setStyle("-fx-background-color: #0ff");
                }
                hBox.getChildren().add(vBox);
                vBox.getChildren().add(cola);
                if (mujer != null){
                    vBox.getChildren().add(mujer);
                }
                contenedor.getChildren().add(hBox);
                temp = temp.getProximo();
            }

        }
    }

    //Método para eliminar elemento de la cola
    public void extraer() {
        if(frente == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Practica Colas");
            alert.setHeaderText("La cola está vacía.");
            alert.setContentText("Intentalo más tarde...");
            alert.showAndWait();
        }else {
            int valorExtraer = frente.getValor();//Variable temporal
            frente = frente.getProximo();//Cambiar el frente por el siguiente
            index--;
            mostrar();
        }
    }
}
