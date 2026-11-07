public class Profesor extends Persona {
    private String departamento;

    public Profesor(String nombre, String id, String email, String departamento) {
        super(nombre, id, email);
        this.departamento = departamento;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Profesor: " + nombre);
        System.out.println("ID: " + id);
        System.out.println("Email: " + email);
        System.out.println("Departamento: " + departamento);
    }
}
