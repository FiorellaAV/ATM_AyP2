import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class Transaccion implements Revertible {
	
	protected Cajero cajero;
	protected Cuenta cuentaOrigen;
	protected Cuenta cuentaDestino;
	protected double monto;
	protected String motivo;
	protected Impresora impresora = new Impresora();
	Scanner teclado = new Scanner(System.in);
	
	public Transaccion(Cuenta cuenta, Cuenta cuenta2, double monto, String motivo, Cajero cajero){
		this.cajero = cajero;
		this.cuentaOrigen = cuenta;
		this.cuentaDestino = cuenta2;
		this.monto = monto;
		this.motivo = motivo;
	}
	
	public Transaccion(Cuenta cuenta, double monto, String motivo, Cajero cajero){
		this.cajero = cajero;
		this.cuentaOrigen = cuenta;
		this.monto = monto;
		this.motivo = motivo;
	}
	public void ejecutar()throws ErrorDeTransaccion{
		
	}
	public void revertir()throws ErrorDeTransaccion{
		
	}
	//Post: Recibe una respuesta del tipo int
	protected int getIntInput(){
		int numero = -1;
		while(numero<0) {
			try {
				numero = Integer.parseInt(teclado.nextLine());
			}
			catch(NumberFormatException e) {
				System.out.println("Opción invalida. Intente nuevamente");
			}
		}
		return numero;
	}
	//Post: Recibe una respuesta del tipo double
	protected double getDoubleInput(){
		double numero = -1;
		while(numero<0) {
			try {
				numero = Double.valueOf(teclado.nextLine());
			}
			catch(NumberFormatException e) {
				System.out.println("Opción invalida. Intente nuevamente");
			}
		}
		return numero;
	}
	//Post: Se utiliza en casos de una transaccion de una sola cuenta.
	// Agrega el movimiento a la lista de movimientos y al historial de la cuenta
	protected void setHistorialDeMovimientos(int tipo) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		Movimiento movimientoCuenta = new Movimiento(formatter.format(date), motivo, cuentaOrigen.getAlias(), monto);
		switch(tipo) {
		case 1:
			cajero.setMovimiento(movimientoCuenta);
			cuentaOrigen.setMovimiento(movimientoCuenta);
			break;
		case 2:
			cajero.setMovimiento(movimientoCuenta);
			cuentaOrigen.setMovimiento(movimientoCuenta);
			Movimiento movimientoCuentaDestinataria = new Movimiento(formatter.format(date), motivo, cuentaDestino.getAlias(), monto);
			cajero.setMovimiento(movimientoCuentaDestinataria);
			cuentaDestino.setMovimiento(movimientoCuentaDestinataria);
			break;
		
		}	
	}
	//Post: Se utiliza en casos de una compra de dolares
	protected void setHistorialDeMovimientos(double precioDeCompra) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		Movimiento movimientoCuenta = new Movimiento(formatter.format(date), motivo, cuentaOrigen.getAlias(), precioDeCompra);
		cajero.setMovimiento(movimientoCuenta);
		cuentaOrigen.setMovimiento(movimientoCuenta);
		Movimiento movimientoCuentaDestinataria = new Movimiento(formatter.format(date), motivo, cuentaDestino.getAlias(), monto);
		cajero.setMovimiento(movimientoCuentaDestinataria);
		cuentaDestino.setMovimiento(movimientoCuentaDestinataria);
	}
}