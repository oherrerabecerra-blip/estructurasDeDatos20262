package EjerciciosSemana1;

public class punto1{
    public static void main(String[] args){
     //obtener dos arreglos tal que sus elemtos sean los numeros pares y los numeros impares del arreglo a de 10 elemtos
        int[] a = {1,2,3,4,5,6,7,8,9,10};
        int[] pares = new int[10];
        int[] impares = new int[10];
        
        int contadorPares=0;
        int contadorImpares=0;

        for(int i=0; i<10; i++){
            if(a[i]%2==0){
                pares[contadorPares] = a[i];
                contadorPares++;
            }else{
                impares[contadorImpares] = a[i];
                contadorImpares++;
            }
        }
        
        System.out.println("Numeros pares:");
        for(int i=0; i<contadorPares; i++){
            System.out.print(pares[i] + " ");
        }
        System.out.println();
        System.out.println("Numeros impares:");
        for(int i=0; i<contadorImpares; i++){
            System.out.print(impares[i] + " ");
        }
    }
}