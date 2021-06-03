import java.io.IOException;

public class Deposito extends Transaccion {

	public Deposito(Cuenta cuenta, double monto, Cajero cajero) {
		super(cuenta, monto, "Deposito", cajero);
	}

	//Post: Se realiza un deposito //
	@Override
	public void ejecutar() throws ErrorDeTransaccion{
		try {
			System.out.println("Desea agregar mas dinero?   (Y/N)");
			if(teclado.nextLine().toLowerCase().equals("y")) {
				monto+=getDoubleInput();
				ejecutar();
			}else {
				cuentaOrigen.incrementarSaldo(monto);
				setHistorialDeMovimientos(1);
				impresora.tiquetDeposito(cuentaOrigen, monto);
				

			}
		}catch(IOException e) {
			System.out.print(e.getCause());
		}
	}
}
