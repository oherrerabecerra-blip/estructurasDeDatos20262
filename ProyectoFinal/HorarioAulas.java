package ProyectoFinal;
public class HorarioAulas {
    private String codigoAula;
    private String nombreAula;
    private String dia;
    private String horaInicio;
    private String horaFin;
    private String nombreMateria;
    private String nombreProfesor;
    private boolean disponible;
    public HorarioAulas(String codigoAula, String nombreAula, String dia, String horaInicio, String horaFin, String nombreMateria, String nombreProfesor, boolean disponible) {
        this.codigoAula = codigoAula;
        this.nombreAula = nombreAula;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.nombreMateria = nombreMateria;
        this.nombreProfesor = nombreProfesor;
        this.disponible = disponible;
    }
    public String getCodigoAula() {
        return codigoAula;
    }
    public String getNombreAula() {
        return nombreAula;
    }
    public String getDia() {
        return dia;
    }
    public String getHoraInicio() {
        return horaInicio;
    }
    public String getHoraFin() {
        return horaFin;
    }
    public String getNombreMateria() {
        return nombreMateria;
    }
    public String getNombreProfesor() {
        return nombreProfesor;
    }
    public boolean isDisponible() {
        return disponible;
    }
    public void setcodigoAula(String codigoAula) {
        this.codigoAula = codigoAula;
    }
    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }
    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    //reserva
    public void reserva(){
        if(disponible){
            disponible = false;
            System.out.println("Aula reservada exitosamente");
        }else{
            System.out.println("El aula no esta disponible");
        }
    }
    public void liberar(){
        disponible = true;
        System.out.println("Aula liberada exitosamente");
    }
    // mostrar informacion del aula
    public void mostrarInformacion(){
        System.out.println("Codigo del aula: " + codigoAula);
        System.out.println("Nombre del aula: " + nombreAula);
        System.out.println("Dia: " + dia);
        System.out.println("Hora de inicio: " + horaInicio);
        System.out.println("Hora de fin: " + horaFin);
        System.out.println("Nombre de la materia: " + nombreMateria);
        System.out.println("Nombre del profesor: " + nombreProfesor);
        System.out.println("Disponible: " + disponible );
    }
    //metodo para agrar una solicitud a la cola 
  //  public void encontarSolicitud(solicitudReserva solicitud){
    //    colaSolicitudes.
    }
