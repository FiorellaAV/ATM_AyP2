import java.util.Stack;

public class Cuenta {
	
	protected String tipoToString;
	protected double saldo;
	protected String alias;
	protected Stack<Movimiento> historialDeMovimientos;
	
	public Cuenta(String alias, double saldo, Stack<Movimiento> historialDeMovimientos){
		this.saldo = saldo;
		this.alias = alias;			
		this.historialDeMovimientos = historialDeMovimientos;
	}
	//Post: incrementa el saldo de la cuenta
	public void incrementarSaldo(double suma ) {
		this.saldo +=suma;
	}
	//Post: reduce el saldo de la cuenta
	public void reducirSaldo(double resta) throws ErrorDeTransaccion {
		if(this.saldo >= resta) {
				this.saldo -= resta;
		}else { 
			throw new ErrorDeTransaccion("Saldo insuficiente");
		}
	}
	//Post: devuelve una lista con los 10 ultimos movimientos
	public Stack<String> getUltimosMovimientos() {
		Stack<String> ultimosMovimientos = new Stack<String>();
		for(int i = historialDeMovimientos.size()-1; i>historialDeMovimientos.size()-11 && i>=0 ; i--) {
			ultimosMovimientos.add(historialDeMovimientos.get(i).toString());
		}
		
		return ultimosMovimientos;
	}
	//Post: devuelve un String con el alias y el saldo de la cuenta
	public String toString() {
		return this.alias+","+this.saldo;
	}
    //Post: devuelve el saldo de la cuenta
	public double getSaldo() {
		return saldo;
	}
    //Post: devuelve el alias de la cuenta
	public String getAlias() {
		return alias;
	}
    //Post: devuelve una lista con el historial de movimientos
	public Stack<Movimiento> getHistorialDeMovimientos() {
		return historialDeMovimientos;
	}
	//Post: se agrega un movimiento a la lista de historial de movimientos
	public void setMovimiento(Movimiento movimiento) {
		historialDeMovimientos.add(movimiento);
	}
	
}