import java.io.IOException;


public class CompraDeDolares extends Transaccion {
	
	private double precioDolar;
	public CompraDeDolares(Cuenta cuenta, Cuenta cuenta2, double monto, Cajero cajero){
		super(cuenta, cuenta2, monto, "Compra de dolares", cajero);
		this.precioDolar = 71.17;

	}

	//Post: Se realiza la compra de Dolares
	@Override
	public void ejecutar()throws ErrorDeTransaccion {
		try {
			double precioDeCompra = monto*precioDolar*1.30;
			if(cuentaOrigen.getClass().equals(CajaAhorroDolares.class)) {
				throw new ErrorDeTransaccion("La caja de ahorro en dolares no permite este tipo de operaciones");
			}else {
				cuentaOrigen.reducirSaldo(precioDeCompra);
				System.out.println("Confirme la compra de U$D"+monto+"      (Y/N) ");
				if(teclado.nextLine().toLowerCase().equals("y")) {
					cuentaDestino.incrementarSaldo(monto);
					setHistorialDeMovimientos(precioDeCompra);
					impresora.tiquetDolar(cuentaOrigen, cuentaDestino, monto, precioDeCompra);
				}else {
					cuentaOrigen.incrementarSaldo(precioDeCompra);
				}
			}
		}catch(IOException e) {
			System.out.print(e.getCause());
		}
	}
}
