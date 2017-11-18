package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
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
    TextField txtNumber;

    @FXML
    Button addBtn;

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


        mostrar();
    }

    public void mostrar(){
      if (frente != null){
            Nodo temp = frente;

            while(temp!=null){
                HBox hBox = new HBox();
                VBox vBox = new VBox();
                ImageView hombre = null;
                try {
                        File archivo = new File("src/sample/Imagenes/hombre.png");
                        Image image = new Image(archivo.toURI().toURL().toString());
                        hombre = new ImageView(image);

                }catch (Exception e){

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
        }

    }
}
