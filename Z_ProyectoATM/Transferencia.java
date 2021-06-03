import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Transferencia extends Transaccion{

	public Transferencia(Cuenta cuenta, Cuenta cuenta2, double monto, String motivo, Cajero cajero){
		super(cuenta, cuenta2, monto, motivo, cajero);

	}


	//Post: Se transfiere dinero de una cuenta a otra
	//usar el buscar por alias antes de usar este metodo
	@Override
	public void ejecutar()throws ErrorDeTransaccion{
		try {
			if(cuentaOrigen.getClass().equals(CajaAhorroDolares.class)) {
				throw new ErrorDeTransaccion("La caja de ahorro en dolares no permite este tipo de operaciones");
			}else{
				cuentaOrigen.reducirSaldo(monto);
				cuentaDestino.incrementarSaldo(monto);
				setHistorialDeMovimientos(2);
				impresora.tiquetTransferencia(cuentaOrigen, cuentaDestino, monto);
				System.out.println("Desea revertir la operación?     (Y/N)");
				if(teclado.nextLine().toLowerCase().equals("y")) {
					revertir();
				}
				
			}
		}catch(IOException e) {
			System.out.print(e.getCause());
		}
	}
	@Override
	//Post: se revierte la ultima transferencia realizada
	public void revertir() throws ErrorDeTransaccion{
		cuentaDestino.reducirSaldo(monto);
		cuentaOrigen.incrementarSaldo(monto);
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		Movimiento movimientoRevertido = new Movimiento(formatter.format(date), "Cancelación de transferencia", cuentaOrigen.getAlias(), monto);
		cajero.setMovimiento(movimientoRevertido);
		cuentaOrigen.setMovimiento(movimientoRevertido);
	}
}