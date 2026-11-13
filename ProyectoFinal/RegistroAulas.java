package ProyectoFinal;
import java.util.TreeMap;
public class RegistroAulas {
    private TreeMap<String, Aula> aulas = new TreeMap<>();
    public void registrar(Aula aula) {
        if (aula != null) aulas.put(aula.getNombre(), aula);
    }
    public Aula buscar(String nombre) { 
        return aulas.get(nombre); 
    }
    public boolean eliminar(String nombre) { 
        return aulas.remove(nombre) != null; 
    }
    public void listar() {
        if (aulas.isEmpty()) System.out.println("No hay aulas registradas.");
        for (Aula aula : aulas.values())
            System.out.println(aula.getNombre() + " | Capacidad: " + aula.getCapacidad());
    }
}
