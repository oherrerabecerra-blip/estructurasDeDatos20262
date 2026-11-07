import java.util.LinkedList;
import java.util.Queue;

public class Materia {
    private String codigo;
    private String nombre;
    private int cupos;
    private Queue<String> colaEspera = new LinkedList<>();

    public Materia(String codigo, String nombre, int cupos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cupos = cupos;
    }

    public boolean inscribir(String idEstudiante) {
        if (cupos > 0) {
            cupos--;
            System.out.println("Inscripcion exitosa: " + idEstudiante);
            return true;
        } else {
            colaEspera.offer(idEstudiante);
            System.out.println("Materia llena. Estudiante agregado a cola de espera.");
            return false;
        }
    }

    public String getCodigo() { return codigo; }

    public void mostrarCola() {
        System.out.println("Cola de espera de " + nombre);
        for (String estudiante : colaEspera) {
            System.out.println(estudiante);
        }
    }
}
