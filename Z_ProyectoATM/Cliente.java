import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private String cuit;
	private String alias;
	private Tarjeta tarjeta;
	private ArrayList<Cuenta> listaDeCuentas = new ArrayList<Cuenta>();
	//Post: Se inicializa el cliente
	public Cliente(String cuit, String alias, Tarjeta tarjeta, ArrayList<Cuenta> cuentas) {
		
		this.alias = alias;
		this.cuit = cuit;
		this.tarjeta = tarjeta;
		this.listaDeCuentas = cuentas;
		
	}
	//Post: Se devuelve un string con el cuit, el alias y el numero de tarjeta asociada//
	public String toString() {
		return this.cuit+","+this.alias+","+this.tarjeta.getNumero();
	}
	//Post: Se devuelve el cuit del cliente//
	public String getCuit() {

		return cuit;
	}
	//Post: Se devuelve el alias del cliente//
	public String getAlias() {

		return alias;
	}
	//Post: Se devuelve la tarjeta asociada al cliente//
	public Tarjeta getTarjeta() {

		return tarjeta;
	}
	//Post: Se devuelve un arraglo de las cuentas asociadas a el cliente//
	public List<Cuenta> getCuentas() {
		return listaDeCuentas;
	}
}



