package sample;

import javafx.scene.control.Alert;

import javax.xml.soap.Node;

public class Cola {


    public   Nodo frente;//El inicio de la cola
    private int index;

    //Contructor simple

    public Cola() {
        this.frente = null;
        this.index = 0;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    //Método para insertar siguiente elemento (nodo), el elemento debe colocarse detrás del último nodo
    public void insertar(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (isVacia()) {
            frente = nuevo;
        } else {

            Nodo temp = frente;
            while (temp.getProximo() != null) {
                temp = temp.getProximo();
            }
            temp.setProx(nuevo);
        }
        index++;

    }

    public boolean isVacia(){
     return (frente == null);

    }


    //Método para extraer el elemento del frente
    public int extraer() {
        if(isVacia()) {
            return 0;
        }else {
            int valorExtraer = frente.getValor();//Variable temporal
            frente = frente.getProximo();//Cambiar el frente por el siguiente
           // System.out.println("Valor extraído " + valorExtraer);
            index--;

           return valorExtraer; //Devolver el valor extraido de la cola

        }

    }

    public void   vaciar() {
        frente = null;
        index = 0;
        // mostrar ();
    }



    public void vaciaError(){
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Practica Colas");
        alert1.setHeaderText("La cola está vacía.");
        alert1.setContentText("Intentalo más tarde...");
        alert1.showAndWait();
    }

}