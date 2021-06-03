
import java.util.Stack;

public class CajaAhorroPesos extends Cuenta{
    //Post: Se inicializa la caja de ahorro en pesos//
	public CajaAhorroPesos(String alias, double saldo, Stack<Movimiento> historialDeMovimientos){
		super( alias, saldo, historialDeMovimientos);
		this.tipoToString = "caja de ahorro en pesos";
	}
	
	@Override
	public String toString() {
		return "01,"+this.alias+","+this.saldo;
	}
	
}
