package ProyectoFinal;
public abstract class Evaluacion {
    private String nombre;
    private double nota;
    public Evaluacion(String nombre, double nota){
         this.nombre = nombre; 
         setNota(nota); 
        }
    public String getNombre(){ 
        return nombre; 
    }
    public double getNota(){
        return nota; 
    }
    public void setNota(double nota){
        if (nota < 0 || nota > 5) 
        throw new IllegalArgumentException("La nota debe estar entre 0 y 5.");
        this.nota = nota;
    }
    public abstract double calcularNota();
}