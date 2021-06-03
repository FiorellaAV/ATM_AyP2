import java.io.IOException;

public class Retiro extends Transaccion {

	public Retiro(Cuenta cuenta, double monto, Cajero cajero) {
		super(cuenta, monto, "Retiro", cajero);
	}

	//Post: Se retira efectivo de la cuenta ingresada y con el monto ingresado//
	@Override
	public void ejecutar()throws ErrorDeTransaccion{
		try {
			int montoInt = (int) monto;
			if(cuentaOrigen.getClass().equals(CajaAhorroDolares.class)) {
				throw new ErrorDeTransaccion("La caja de ahorro en dolares no permite este tipo de operaciones");
			}else {
				if(!cajero.chequeoDeBilletes(montoInt)) {
					cuentaOrigen.reducirSaldo(montoInt);
					System.out.println("Confirma el retiro de $"+montoInt+"     (Y/N)");
					if(teclado.nextLine().toLowerCase().equals("y")) {
						setHistorialDeMovimientos(1);
						impresora.tiquetRetiro(cuentaOrigen, montoInt);
						devolverBilletes((int) monto);
						
					}else {
						cuentaOrigen.incrementarSaldo(montoInt);
					}
				}				
			}
		}catch(IOException e) {
			System.out.print(e.getCause());
		}
	}
	//Post: Devuelve la cantidad de billetes que se extrajo en la operacion de retiro
	private void devolverBilletes(int monto){

		int cantidadDeBilletes1000 = monto / 1000 ;
		int cantidadDeBilletes500 = (monto -(cantidadDeBilletes1000*1000)) / 500;
		int cantidadDeBilletes100 = ((monto -(cantidadDeBilletes1000*1000))-cantidadDeBilletes500*500) / 100;
		System.out.println("Por favor retire los billetes entregados: ");

		if(cantidadDeBilletes100!=0)
			System.out.println(cantidadDeBilletes100 + " billetes de $100");
		if(cantidadDeBilletes500!=0)
			System.out.println(cantidadDeBilletes500 +" billetes de $500");
		if(cantidadDeBilletes1000!=0)
			System.out.println(cantidadDeBilletes1000 +" billetes de $1000");

	}
}