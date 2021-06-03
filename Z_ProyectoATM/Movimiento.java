public class Movimiento {
	
	private String fecha;
	private String motivo;
	private String alias;
	private double importe;
	//Post: Se inicializa la clase movimiento//
	public Movimiento(String fecha, String motivo, String alias, double importe) {
		this.fecha = fecha;
		this.motivo = motivo;
		this.alias = alias;
		this.importe = importe;
	}
	//Post: Se devuelve un string con la fecha, el motivo , el alias y el importe//
	public String toString() {
		return this.fecha+","+this.motivo+","+this.alias+","+this.importe;
	}
    //Post: Se devuelve la fecha//
	public String getFecha() {
		return fecha;
	}
    //Post: Se devueve el motivo//
	public String getMotivo() {
		return motivo;
	}
    //Post: Se devuelve el alias//
	public String getAlias() {
		return alias;
	}
    //Post: Se devuelve el importe//
	public double getImporte() {
		return importe;
	}
}
