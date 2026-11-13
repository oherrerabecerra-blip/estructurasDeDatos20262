package ProyectoFinal;
public class ExamenFinal extends Evaluacion {
    private double porcentaje;
    public ExamenFinal(String nombre, double nota, double porcentaje){
        super(nombre, nota); 
        this.porcentaje = porcentaje;
    }
    @Override public double calcularNota() { return getNota() * porcentaje / 100.0; }
}
