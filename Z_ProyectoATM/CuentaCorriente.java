import java.util.Stack;

public class CuentaCorriente extends Cuenta{
	
	private double descubierto;
	//Post: Se inicializa la cuenta corriente //
	public CuentaCorriente( String alias, double saldo, double descubierto, Stack<Movimiento> historialDeMovimientos){
		super(alias, saldo, historialDeMovimientos);
		this.tipoToString = "cuenta corriente";
		this.descubierto = descubierto;
	}
	
	@Override
	public void reducirSaldo(double resta) throws ErrorDeTransaccion {
		if(this.saldo+this.descubierto >= resta) {
			this.saldo -= resta;
		}else { 
			throw new ErrorDeTransaccion("Saldo insuficiente");
		}
	}
	@Override
	public String toString() {
		return "02,"+this.alias+","+this.saldo;
	}
	//Post: Se devuelve el descubierto de la cuenta//
	public double getDescubierto() {
		return descubierto;
	}
	
}
