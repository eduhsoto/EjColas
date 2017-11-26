package sample;

import javafx.scene.control.Alert;

public class Cola {

    public   Nodo frente;//El inicio de la cola
    private int index;//Declaramos una variable para determinar el tamaño

    //Contructor
    public Cola() {
        //Valores iniciales de las variables antes declaradas
        this.frente = null;
        this.index = 0;
    }

    public boolean isVacia(){
        return (frente == null);

    }

    //Método para insertar siguiente elemento (nodo), el elemento debe colocarse detrás del último nodo
    public void insertar(int valor){
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
        index++;//Va incrementando el tamaño, una vez insertado un elemento

    }

    //Obtener el tamaño de la cola
    public int getIndex() {
        return index;
    }

    //Método para extraer el elemento del frente
    public int extraer() {
        if(isVacia()) {
            vaciaError();
            return 0;
        }else {
            int valorExtraer = frente.getValor();//Variable temporal
            frente = frente.getProximo();//Cambiar el frente por el siguiente
           // System.out.println("Valor extraído " + valorExtraer);
            index--;

           return valorExtraer; //Devolver el valor extraido de la cola
        }
    }

    //Vacía la cola
    public void vaciar() {
        if (isVacia()){
         vaciaError();
        }else {
            frente = null;
            index = 0;
        }
    }

    //En caso de que la lista este vacía llama al método vaciarError
    public void vaciaError(){
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Practica Colas");
        alert1.setHeaderText("La cola está vacía.");
        alert1.setContentText("Intentalo más tarde...");
        alert1.showAndWait();
    }

}