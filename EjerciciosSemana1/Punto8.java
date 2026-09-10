package EjerciciosSemana1;

public class Punto8 {
    public static void main(String[] args){
        // elavora un programa que lea 30 numeros y imprima el numero mayor el numero menor y el numero de veses que se repite ambos
        int [] a = new int[30];
        int Min = 1 , Max = 30;
        for(int i=0;i<a.length;i++){
            a[i] = (int)(Math.random() * (Max - Min + 1) + Min);
        }
        System.out.println("Arreglo a:");
        for(int i=0;i<a.length;i++){
            System.out.print(a[i] + " ");
        }
        // encontrar el numero mayor y el numero menor
        int numeroMayor = a[0];
        int numeroMenor = a[0];
        for(int i=0; i<30; i++){
            if (numeroMayor < a[i]) {
                numeroMayor = a[i];
            }
            if (numeroMenor > a[i]) {
                numeroMenor = a[i];
            }
        }
        // contar el numero de veces que se repite el numero mayor y el numero menor
        int contadorMayor = 0;
        int contadorMenor = 0;
      for(int i=0; i<30; i++){
            if(a[i] == numeroMayor){
                contadorMayor++;
            }        
        }
        for(int i=0; i<30; i++){
            if(a[i] == numeroMenor){
                contadorMenor++;
            }        
        }
        System.out.println("Número mayor: " + numeroMayor + " (se repite " + contadorMayor + " veces)");
        System.out.println("Número menor: " + numeroMenor + " (se repite " + contadorMenor + " veces)");
    }
}

