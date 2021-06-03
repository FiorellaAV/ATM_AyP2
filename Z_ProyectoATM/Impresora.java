import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Impresora {
	
	private SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private Date date = new Date(System.currentTimeMillis());
	
	public Impresora() {}
	//Post: Se crea un tiquet en la carpeta de "tikets" indicando el deposito que se realizo
	public void tiquetDeposito(Cuenta cuentaOrigen, double monto) throws IOException {
		FileWriter impresor = new FileWriter("tikets\\tiketDeposito.txt");
		impresor.write("******************************************");impresor.append("\n");
		impresor.append("\n");
		impresor.write("Fecha y Hora: "+formatter.format(date));impresor.append("\n");
		impresor.append("\n");
		impresor.write("Cuenta: "+cuentaOrigen.getAlias());impresor.append("\n");
		impresor.append("\n");
		impresor.write("Deposito de $"+monto);impresor.append("\n");
		impresor.append("\n");
		impresor.write("Saldo restante: $"+cuentaOrigen.getSaldo());impresor.append("\n");
		impresor.write("******************************************");
		impresor.close();
	}
	//Post: Se crea un tiquet en la carpeta de "tikets" indicando el retiro que se realizo
	public void tiquetRetiro(Cuenta cuentaOrigen, int montoInt) throws IOException  {
		FileWriter impresor = new FileWriter("tikets\\tiketRetiro.txt");
		impresor.write("******************************************");impresor.append("\n");
		impresor.write("Fecha y Hora: "+formatter.format(date));impresor.append("\n");
		impresor.write("Cuenta: "+cuentaOrigen.getAlias());impresor.append("\n");
		impresor.write("Retiro de $"+montoInt);impresor.append("\n");
		impresor.write("Saldo restante: $"+cuentaOrigen.getSaldo());impresor.append("\n");
		impresor.write("******************************************");impresor.append("\n");
		impresor.close();
	}
	//Post: Se crea un tiquet en la carpeta de "tikets" indicando la transferencia que se realizo
	public void tiquetTransferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto) throws IOException {
		FileWriter impresor = new FileWriter("tikets\\tiketTransferencia.txt");
		impresor.write("******************************************");impresor.append("\n");
		impresor.write("Fecha y Hora: "+formatter.format(date));impresor.append("\n");
		impresor.write("Cuenta de origen: "+cuentaOrigen.getAlias());impresor.append("\n");
		impresor.write("Cuenta destinataria: "+cuentaDestino.getAlias());impresor.append("\n");
		impresor.write("El monto transferido es de $"+monto);impresor.append("\n");
		impresor.write("Saldo restante: $"+cuentaOrigen.getSaldo());impresor.append("\n");
		impresor.write("******************************************");impresor.append("\n");
		impresor.close();
	}
	//Post: Se crea un tiquet en la carpeta de "tikets" indicando la compra de dolares que realizo
	public void tiquetDolar(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto, double precioDeCompra) throws IOException{
		FileWriter impresor = new FileWriter("tikets\\tiketCompraDolar.txt");
		impresor.write("******************************************");impresor.append("\n");
		impresor.write("Fecha y Hora: "+formatter.format(date));impresor.append("\n");
		impresor.write("Cuenta de origen: "+cuentaOrigen.getAlias());impresor.append("\n");
		impresor.write("Cuenta destinataria: "+cuentaDestino.getAlias());impresor.append("\n");
		impresor.write("Se compro U$D"+monto+" a $"+precioDeCompra);impresor.append("\n");
		impresor.write("Saldo restante: $"+cuentaOrigen.getSaldo());impresor.append("\n");
		impresor.write("Nuevo saldo de cuenta: $"+cuentaOrigen.getSaldo());impresor.append("\n");
		impresor.write("******************************************");impresor.append("\n");
		impresor.close();
	}
}