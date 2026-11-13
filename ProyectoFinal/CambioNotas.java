package ProyectoFinal;
public class CambioNotas {
    private String id;
    private int semestre, posicion;
    private double anterior, nueva;
    public CambioNotas(String id, int semestre, int posicion, double anterior, double nueva) {
        this.id = id; 
        this.semestre = semestre; 
        this.posicion = posicion;
        this.anterior = anterior; 
        this.nueva = nueva;
    }
    public String getId(){
        return id; 
        }
    public int getSemestre(){ 
        return semestre;
        }
    public int getPosicion(){ 
        return posicion;
        }            
    public double getAnterior(){
        return anterior;
        }
    public double getNueva(){
        return nueva;
        }
    }