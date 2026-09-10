package EjerciciosSemana1;

public class Punto11 {
    public static void main(String[] args) {
    // Dado un arreglo A de N elementos se desea generar tres arreglos que tengan los elementos negativos, ceros y positivos del arreglo inicial.

        int [] a = {-12, -9, -7, -5, -3, -2, -1, 0 , 0 , 1, 6, 10, 25, 30, 3, 9};
        int negativos = 0;
        int ceros = 0;
        int positivos = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] < 0){
                negativos++;
            } else if(a[i] == 0){
                ceros++;
            } else {
                positivos++;
            }
        }
        int [] b = new int[negativos];
        int [] c = new int[ceros];
        int [] d = new int[positivos];
        int neg = 0;
        int cer = 0;
        int pos = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] < 0){
                b[neg] = a[i];
                neg++;
            } else if(a[i] == 0){
                c[cer] = a[i];
                cer++;
            } else {
                d[pos] = a[i];
                pos++;
            }  
        }
        System.out.println("negativos: ");
        for(int i = 0; i < b.length; i++){
            System.out.println("b[" + i + "] = " + b[i]);
        }
        System.out.println("ceros: ");
        for(int i = 0; i < c.length; i++){
            System.out.println("c[" + i + "] = " + c[i]);
        }
        System.out.println("positivos: ");
        for(int i = 0; i < d.length; i++){
            System.out.println("d[" + i + "] = " + d[i]);
        }
    }
}
