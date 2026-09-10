package EjerciciosSemana1;

public class Punto9 {
    public static void main(String[] args){
        //codifique un programa tal que como dentra un arreglo unidimencional de enteros y un numero entero deternime cuantas veces se repite el numero dentro del arreglo.
       int [] a = {1,4,3,5,7,6,4,9,4,10,8,11,4};
       int numero = 4;
       int contador = 0;

       for(int i=0; i<a.length; i++){
           if(a[i] == numero){
               contador++;
           }
       }
       System.out.println("El número " + numero + " se repite " + contador + " veces en el arreglo.");
    }
}
        