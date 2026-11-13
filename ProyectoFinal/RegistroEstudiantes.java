package ProyectoFinal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class RegistroEstudiantes {
    private HashMap<String, Estudiante> estudiantes = new HashMap<>();
    public void registrar(Estudiante e) {
        if (e == null) return;
        if (estudiantes.putIfAbsent(e.getId(), e) != null) System.out.println("Ya existe un estudiante con ese ID.");
        else System.out.println("Estudiante registrado.");
    }
    public Estudiante buscarPorId(String id) { return estudiantes.get(id); }
    public List<Estudiante> buscarPorNombre(String nombre) {
        List<Estudiante> r = new ArrayList<>();
        for (Estudiante e : estudiantes.values())
            if (e.getNombre().toLowerCase().contains(nombre.toLowerCase())) r.add(e);
        return r;
    }
    public List<Estudiante> listar() { return new ArrayList<>(estudiantes.values()); }
    public int cantidad() { return estudiantes.size(); }
}