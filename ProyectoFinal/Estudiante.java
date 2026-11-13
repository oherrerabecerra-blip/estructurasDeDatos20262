package ProyectoFinal;
import java.util.LinkedList;
public class Estudiante extends Persona {
    private String programa;
    private int semestre;
    private LinkedList<String> historialMaterias = new LinkedList<>();
    public Estudiante(String id, String nombre, String email, String programa, int semestre) {
        super(id, nombre, email);
        this.programa = programa;
        this.semestre = semestre;
        }
    public String getPrograma(){
        return programa;
        }   
    public int getSemestre(){
        return semestre; 
        }
    public void agregarMateriaCursada(String materia){
        historialMaterias.add(materia); 
        }
    public LinkedList<String> getHistorialMaterias(){
        return new LinkedList<>(historialMaterias);
        }
    public void mostrarHistorial(){
        if (historialMaterias.isEmpty()) 
            System.out.println("Sin materias en el historial.");
        for (String materia : historialMaterias) 
            System.out.println("- " + materia);
    }
    public void inscribirMateria(String codigo) {
        System.out.println(getNombre() + " solicito inscripcion en " + codigo);
    }
    @Override
    public void mostrarInformacion() {
        System.out.println("Estudiante: " + getNombre() + " | ID: " + getId()+ " | Email: " + getEmail() + " | Programa: " + programa+ " | Semestre: " + semestre);
    }
    @Override
    public String toString() { return getId() + " - " + getNombre() + " (" + programa + ")"; }
}