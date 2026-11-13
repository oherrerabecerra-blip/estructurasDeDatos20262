package ProyectoFinal;
public class Quiz extends Evaluacion {
    private double porcentaje;
    public Quiz(String nombre, double nota, double porcentaje) {
        super(nombre, nota);
         this.porcentaje = porcentaje;
    }
    @Override public double calcularNota() { return getNota() * porcentaje / 100.0; }
}