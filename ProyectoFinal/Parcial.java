package ProyectoFinal;
public class Parcial extends Evaluacion {
    private double porcentaje;
    public Parcial(String nombre, double nota, double porcentaje) {
        super(nombre, nota); 
        this.porcentaje = porcentaje;
    }
    @Override public double calcularNota(){ 
        return getNota() * porcentaje / 100.0; 
    }
}

