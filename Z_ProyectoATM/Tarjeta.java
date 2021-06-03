public class Tarjeta {
	
	private String cuit;
	private String numero;
	private String pin;
	private Cliente cliente;
	
	//Post: Se inicializa la tarjeta//
	public Tarjeta(String numero, String pin, String cuit) {
		
		this.cuit = cuit;
		this.numero = numero;
		this.pin = pin;
	}
	
	//Post: Se devuelve un string con el numero, el pin y el cuit de la tarjeta//
	public String toString() {
		return this.numero+","+this.pin+","+this.cuit;
	}
	//Post: Se devuelve el cliente de la tarjeta//
	public Cliente getCliente() {
		return cliente;
	}
	//Post: Se devuelve el cuit de la tarjeta//
	public String getCuit() {
		return cuit;
	}
	//Post: Se devuelve el numero de la tarjeta//
	public String getNumero() {
		return numero;
	}
	//Post: Se devuelve el pin de la tarjeta//
	public String getPin() {
		return pin;
	}
}