package EjerciciosSemana1;

public class Punto15 {
    public static void main(String[] args) {
        // se tiene los nombres y los sueldos de los trabajadores de una empresa se deasea saber cuantos y quines tinene sueldos promedio.
        int [] sueldos = { 1400, 1900, 4000, 2000, 2100};
        String [] nombre = {"carlos", "miguel", "sofia", "andrea", "bruno"};
        int promedio = 0;
        int cantidad = 0;

        for(int i = 0; i < sueldos.length; i++){
            promedio += sueldos[i];
        }
        promedio /= sueldos.length;
        System.out.println("El sueldo promedio es: " + promedio);
        System.out.println("el trabajador con sueldo mayor al promedio es:");
        for(int i = 0; i < sueldos.length; i++){
            if(sueldos[i] > promedio){
                System.out.println(nombre[i] + ": " + sueldos[i]);
                cantidad++;
                System.out.println("Cantidad de trabajadores con sueldo mayor al promedio: " + cantidad);
            }
        }
    }
}
