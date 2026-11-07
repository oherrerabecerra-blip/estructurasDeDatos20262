public class Estudiante extends Persona {
    private int semestre;
    private Double[][] notas = new Double[10][20];

    public Estudiante(String nombre, String id, String email, int semestre) {
        super(nombre, id, email);
        this.semestre = semestre;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
        System.out.println("Semestre: " + semestre);
    }

    public int getSemestre() { return semestre; }
    public Double[][] getNotas() { return notas; }

    public void registrarNota(int semestre, int materia, double nota) {
        if (semestre >= 0 && semestre < 10 &&
            materia >= 0 && materia < 20 &&
            nota >= 0 && nota <= 5) {
            notas[semestre][materia] = nota;
        } else {
            System.out.println("Datos de nota incorrectos.");
        }
    }
}
