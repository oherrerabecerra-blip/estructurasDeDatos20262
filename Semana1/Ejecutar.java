package Semana1;

public class Ejecutar {
    public static void main(String[] args) {
        
        // declaracion del arreglo 
         int[] a = { 5,16,8,9,10,25,19,13,22,2};
        
         for(int i = 0; i < a.length; i++) {
        System.out.println("a[" + i + "] =" + a[i]);
         }
            // suma de los elementos del arreglo
         int suma = 0; 
         for(int i = 0; i < a.length; i++) {
             suma += a[i];
         }
         System.out.println("La suma de los elementos del arreglo es: " + suma);

         // declaracioon del arreglo de la forma (2)
         int [] b = new int [11]; 
         int min = 1; int max = 50;
         for ( int i = 0; i < b.length; i++) {
            //llenar el arreglo con numeros aleatorios entre 1 y 50
             b[i] = (int)(Math.random() * (max - min + 1) + min);
         }
         // mostrar los elementos del arreglo b
         for(int i = 0; i < b.length; i++) {
             System.out.println("b[" + i + "] =" + b[i]);
         }
    }
    
}
