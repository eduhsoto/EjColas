/*package sample;

import javax.xml.soap.Node;

public class Cola {

    private Nodo frente;//El inicio de la cola

    //Contructor simple
    public Cola() {
        this.frente = null;
    }

    //Método para insertar siguiente elemento (nodo), el elemento debe colocarse detrás del último nodo
    public void insertar(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (frente == null) {
            frente = nuevo;
        } else {

            Nodo temp = frente;
            while (temp.getProximo() != null) {
                temp = temp.getProximo();
            }
            temp.setProx(nuevo);
        }
    }

    //Método para mostrar los elementos de la cola
    public Object mostrar() {
        if (frente != null) {
            Nodo temp = frente;
            //System.out.println("Los valores de la cola son:");
            while (temp != null) {
                //System.out.println(temp.getValor());

                temp = temp.getProximo();
            }
        } else {
          return   System.out.printf("La cola está vacía.");
        }

        return null;
    }





    //Método para extraer el elemento del frente
    public int extraer() {
        if(frente == null) {
            return 0;
        }else {
            int valorExtraer = frente.getValor();//Variable temporal
            frente = frente.getProximo();//Cambiar el frente por el siguiente
            System.out.println("Valor extraído " + valorExtraer);
            return valorExtraer; //Devolver el valor extraido de la cola
        }
    }
}
*/