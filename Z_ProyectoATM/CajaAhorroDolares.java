import java.util.Stack;

public class CajaAhorroDolares extends Cuenta{
    //Post: Se inicializa la caja de ahorro en dolares//
	public CajaAhorroDolares(String alias, double saldo, Stack<Movimiento> historialDeMovimientos){
		super(alias, saldo, historialDeMovimientos);
		this.tipoToString = "caja de ahorro en dólares";
	}
	
	@Override
	public String toString() {
		return "03,"+this.alias+","+this.saldo;
	}
}
