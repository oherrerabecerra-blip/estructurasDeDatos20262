package ProyectoFinal;

public class aula extends Campus {
    private int capacidad;
    private boolean [][] ocupado = new boolean[7][24];
    public aula(String nombre, int capacidad) {
        super(nombre);
        this.capacidad = capacidad;
    }
    public int getCapacidad() {
        return capacidad;
    }
    //metodo para validar el dia y la hora
    private void validarDiaHora(int dia, int hora, int duracion) {
        if (dia < 0 || dia >= 7 || hora < 0 || hora >= 24){
            System.out.println("Día u hora inválidos.");
            return;
        }
    }
    //metodo para validar el rango de horas
    private void validarRango(int dia, int hora, int duracion){
        if(dia < 0 || dia >=7 || hora < 0 || hora >= 24 || duracion <= 0 || (hora + duracion > 24)){
            System.out.println("reserva invalida");
            return;
        }
    }
    //metodo para mirar si esta disponible 
    public boolean consultarDisponibilidad(int dia, int hora){
        validarDiaHora(dia, hora, 0);
        return !ocupado[dia][hora];      
    }
    //metodo para reservar horario
    public boolean reservar(int dia, int hora, int duracion){
        validarDiaHora(dia, hora, duracion);
        for (int h = hora; h < hora + duracion; h++) {
            if (ocupado[dia][h]) {
                return false; // Ya está ocupado
            }
        }
        // el aula está disponible
        for (int h = hora; h < hora + duracion; h++) {
            ocupado[dia][h] = true;
        }
        return true;
    }
    //metodo para liberar horario
    public boolean liberar(int dia, int hora, int duracion){
        validarRango(dia, hora, duracion);
        for(int h = hora; h < hora + duracion; h++){
            ocupado[dia][h] = false;
        }
        return true;
    }
    @Override 
    public void mostrarInformacion() {
        System.out.println("Aula: " + getNombre() + " Capacitad: " + capacidad);
    }
}    