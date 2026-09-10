package EjerciciosSemana1;

public class Punto10 {
    public static void main(String[] args) {
        // dado un arreglo A de N se desea saber crear otro arreglo tal que cada uno de sus elemtos sea la suma de los opuestos en el arreglo.
        int[] a = { 5,16,8,9,10,25,19,13,22,2};
        int[] b = new int[5];

        for (int i = 0; i < b.length; i++){
            b[i] = a[i] + a[a.length - 1 - i];
        }
        System.out.println("La suma del arreglo b es: ");
        for(int i=0; i<b.length; i++){
            System.out.println("b[" + i + "] = " + b[i]);
        }
    }
}
