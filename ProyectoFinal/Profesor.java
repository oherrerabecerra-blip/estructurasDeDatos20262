package ProyectoFinal;
public class Profesor extends Persona {
    private String especialidad;
    public Profesor(String id, String nombre, String email, String especialidad) {
        super(id, nombre, email);
        this.especialidad = especialidad;
    }
    public String getEspecialidad() { 
        return especialidad;
     }
    public void asignarMateria(String materia) {
        System.out.println("El profesor " + getNombre() + " fue asignado a " + materia);
    }
    @Override
    public void mostrarInformacion() {
        System.out.println("Profesor: " + getNombre() + " | ID: " + getId()
                + " | Email: " + getEmail() + " | Especialidad: " + especialidad);
    }
    @Override
    public String toString() { return getId() + " - " + getNombre() + " (" + especialidad + ")"; }
}