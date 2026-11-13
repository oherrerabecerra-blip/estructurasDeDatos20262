package ProyectoFinal;
public class RegistroNotas {
    private Double[][] notas = new Double[10][20];
    private String[][] materias = new String[10][20];
    public void agregarNota(int semestre, int posicion, String materia, double nota) {
        validar(semestre, posicion);
        validarNota(nota);
        materias[semestre][posicion] = materia;
        notas[semestre][posicion] = nota;
    }
    public void cambiarNota(int semestre, int posicion, double nota) {
        validar(semestre, posicion);
        validarNota(nota);
        if (notas[semestre][posicion] == null) throw new IllegalStateException("No existe una nota.");
        notas[semestre][posicion] = nota;
    }
    public Double obtenerNota(int semestre, int posicion) {
        validar(semestre, posicion);
        return notas[semestre][posicion];
    }
    public String obtenerMateria(int semestre, int posicion) {
        validar(semestre, posicion);
        return materias[semestre][posicion];
    }
    public double promedioSemestre(int semestre) {
        if (semestre < 0 || semestre >= 10) throw new IndexOutOfBoundsException("Semestre invalido.");
        double suma = 0; int cantidad = 0;
        for (int j = 0; j < 20; j++) if (notas[semestre][j] != null) { suma += notas[semestre][j]; cantidad++; }
        return cantidad == 0 ? 0 : suma / cantidad;
    }
    public double promedioAcumulado() {
        double suma = 0; int cantidad = 0;
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 20; j++)
                if (notas[i][j] != null) { suma += notas[i][j]; cantidad++; }
        return cantidad == 0 ? 0 : suma / cantidad;
    }
    public void mostrarReprobadas() {
        System.out.println("Materias reprobadas:");
        boolean hay = false;
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 20; j++)
                if (notas[i][j] != null && notas[i][j] < 3.0) {
                    System.out.println(materias[i][j] + " | Semestre " + (i + 1) + " | Nota: " + notas[i][j]);
                    hay = true;
                }
        if (!hay) System.out.println("No hay materias reprobadas.");
    }
    public void mostrarNotas() {
        for (int i = 0; i < 10; i++) {
            boolean tiene = false;
            for (int j = 0; j < 20; j++) if (notas[i][j] != null) {
                if (!tiene) System.out.println("Semestre " + (i + 1) + ":");
                System.out.println("  " + materias[i][j] + ": " + notas[i][j]);
                tiene = true;
            }
            if (tiene) System.out.println("Promedio: " + String.format("%.2f", promedioSemestre(i)));
        }
        System.out.println("Promedio acumulado: " + String.format("%.2f", promedioAcumulado()));
    }
    private void validar(int semestre, int posicion) {
        if (semestre < 0 || semestre >= 10 || posicion < 0 || posicion >= 20)
            throw new IndexOutOfBoundsException("Semestre o posicion invalida.");
    }
    private void validarNota(double nota) {
        if (nota < 0 || nota > 5) throw new IllegalArgumentException("La nota debe estar entre 0 y 5.");
    }
}