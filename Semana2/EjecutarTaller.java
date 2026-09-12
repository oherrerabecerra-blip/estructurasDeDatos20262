package Semana2;

public class EjecutarTaller {
    public static void main(String[] args) {
        //crear el objeto de la clase OperacionesConArreglos
        OperacionesConArreglos objP10 = new OperacionesConArreglos();
        // crear objeto 
         int[] a  = objP10.llenarArrglo(5, 1, 50);
         //mostar el arreglo 
         objP10.mostrarArreglo(a);
         // Sumar los extremos del arreglo
         int [] b = objP10.sumaExtremos(a);
         // mostrar el arreglo b
         objP10.mostrarArreglo(b);
    }
}
