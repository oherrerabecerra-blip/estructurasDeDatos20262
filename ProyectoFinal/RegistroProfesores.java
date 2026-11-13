package ProyectoFinal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class RegistroProfesores {
    private HashMap<String, Profesor> profesores = new HashMap<>();
    public void registrar(Profesor p) {
        if (p == null) return;
        if (profesores.putIfAbsent(p.getId(), p) != null) System.out.println("Ya existe un profesor con ese ID.");
        else 
            System.out.println("Profesor registrado.");
    }
    public List<Profesor> listar() {
         return new ArrayList<>(profesores.values()); 
        }
    public Profesor buscarPorId(String id) { 
        return profesores.get(id); 
    }
}