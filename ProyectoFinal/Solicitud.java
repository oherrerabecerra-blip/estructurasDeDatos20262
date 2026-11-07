public class Solicitud {
    private String idEstudiante;
    private String codigoMateria;

    public Solicitud(String idEstudiante, String codigoMateria) {
        this.idEstudiante = idEstudiante;
        this.codigoMateria = codigoMateria;
    }

    public String getIdEstudiante() { return idEstudiante; }
    public String getCodigoMateria() { return codigoMateria; }
}
