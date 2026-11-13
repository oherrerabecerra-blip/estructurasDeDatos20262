package ProyectoFinal;
import java.util.ArrayDeque;
import java.util.Deque;

public class NavegacionReportes {
    private Deque<String> reportes = new ArrayDeque<>();
    public void visitar(String reporte) { 
        if (reporte != null && !reporte.isBlank()) 
            reportes.push(reporte); 
        }
    public String atras() { 
        return reportes.isEmpty() ? null : reportes.pop();
     }
    public String reporteActual() {
         return reportes.peek(); 
        }
    public boolean estaVacia() {
         return reportes.isEmpty();
         }
}