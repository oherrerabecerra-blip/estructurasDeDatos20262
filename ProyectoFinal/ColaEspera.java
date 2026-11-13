package ProyectoFinal;
import java.util.LinkedList;
import java.util.Queue;
public class ColaEspera {
    private Queue<Estudiante> cola = new LinkedList<>();
    public void encolar(Estudiante e) { 
        if (e != null && !cola.contains(e)) cola.offer(e); 
    }
    public Estudiante desencolar(){
        return cola.poll();
    }
    public boolean estaVacia(){
        return cola.isEmpty();
    }
    public int tamanio(){
        return cola.size();
    }
    public void mostrar() {
        if (cola.isEmpty()) System.out.println("No hay estudiantes en espera.");
        else for (Estudiante e : cola) System.out.println(e);
    }
}