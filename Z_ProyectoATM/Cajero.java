import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.Date;

public class Cajero implements Pantalla{

	private int billetesDe100 = 500;
	private int billetesDe500 = 500;
	private int billetesDe1000 = 500;
	private Scanner teclado;
	private List<Tarjeta> listaDeTarjetas = new ArrayList<Tarjeta>();
	private List<Cuenta> listaDeCuentas = new ArrayList<Cuenta>();
	private Stack<Movimiento> listaDeMovimientos = new Stack<Movimiento>();
	public List<Cliente> listaDeClientes = new ArrayList<Cliente>();
	private Cliente currentCliente;
	private Cuenta currentCuenta;
	private int menuChoice = -1;
	private Transaccion operacion;

	//Post: Se inicializa el cajero //
	public static void main(String[] arg) throws IOException, ErrorDeTransaccion, ErrorDeValidacion{
		
		try {
			Cajero cajero = new Cajero();
			cajero.teclado = new Scanner(System.in);
			cajero.cargarDatos();
			System.out.println("Bienvenido");
			if(cajero.ingresarTarjeta()) {
				cajero.elegirCuenta();
			}


		}catch(IOException e) {
			System.out.println(e.getCause());
		}catch(ErrorDeTransaccion e) {
			System.out.println(e.obtenerError());
		}catch(ErrorDeValidacion e) {
			System.out.println(e.obtenerError());
		}
	}

	//----------------Validación-----------------------------------

	//Post: Se ingresa el numero de tarjeta y se comprueba que este en la base de datos//
	private boolean ingresarTarjeta() throws ErrorDeValidacion {
		System.out.println("\nPor favor introduzca su número de tarjeta para continuar");
		String numeroDeTarjeta = teclado.nextLine();
		try {
			if(!comparadorDeTarjeta(numeroDeTarjeta)) {
				throw new ErrorDeValidacion("El numero introducido de tarjeta no se encuentra en nuestra base de datos");
			}else { return true;
			}
		}catch(ErrorDeValidacion e) {
			System.out.println(e.obtenerError());
			System.out.println("Por favor, retire su tarjeta e intente otra vez");
			return ingresarTarjeta();
		}

	}
	/*Post: Se comprueba que el numero de tarjeta este en la lista de tarjetas,
	si se encuentra se le procede a pedir la clave al usuario para proceder
	a la pantalla principal*/
	private boolean comparadorDeTarjeta(String numeroDeTarjeta) throws ErrorDeValidacion{

		String tarjetaNumero = null;
		int	intentos = 0;
		for (int i = 0 ; i<listaDeTarjetas.size(); i++){

			Tarjeta tarjeta = listaDeTarjetas.get(i);
			tarjetaNumero = tarjeta.getNumero();
			String tarjetaPin = tarjeta.getPin();

			if (tarjetaNumero.equals( numeroDeTarjeta)){

				System.out.println("Por favor ingrese su clave ");
				while (intentos <= 3){

					String clave = teclado.nextLine();
					if(clave.equals( tarjetaPin)){	
						String aliasCliente = getAliasCliente(tarjeta.getCuit());
						currentCliente = buscarClientePorAlias(aliasCliente);
						return true;

					}else{
						try {
							throw new ErrorDeValidacion("Clave incorrecta");	
						}catch(ErrorDeValidacion e) {
							System.out.println(e.obtenerError());
						}
					}
					intentos++;
				}
				if(intentos == 4){
					try {
						throw new ErrorDeValidacion("Tarjeta bloqueada, comuniquese con su banco emisor ");	
					}catch(ErrorDeValidacion e) {
						System.out.println(e.obtenerError());
					}		
				}return false;
			}	
		}
		return false;	
	}
	//Post: Se chequea que el cajero cuente con la cantidad necesaria de billetes para relizar la operacion de retiro//
	public boolean chequeoDeBilletes(int monto) throws ErrorDeTransaccion{

		if(monto%100.00!=0.00)
			throw new ErrorDeTransaccion("El monto debe ser múltiplo de 100");

		else {
			int resto;

			int	cantidadDe1000 = monto/1000;
			resto = monto%1000*1000;
			int cantidadDe500 = resto/500;
			resto = resto%500*500;
			int cantidadDe100 = resto/100;
		
			if(billetesDe1000>=cantidadDe1000) {
				billetesDe1000 -=cantidadDe1000;
			}else {
				cantidadDe500 += cantidadDe1000*2;
			}
			if(billetesDe500>=cantidadDe500) {
				billetesDe500 -=cantidadDe500;
			}else {
				cantidadDe100 += cantidadDe500*5;
			}
			if(billetesDe100>=cantidadDe100) {
				billetesDe100 -=cantidadDe100;
			}else {
				return false;
			}

			return true;
		}

	}
	//Post: Detecta si el cliente posee el tipo de cuenta que se le pregunte
	private Cuenta poseeCuenta(String tipoCuenta){
		Cuenta cuentafinal=null;
		for(Cuenta cuenta: currentCliente.getCuentas()){
			switch(tipoCuenta){
			case("pesos"):
				if(cuenta.getClass().equals(CajaAhorroPesos.class))
					cuentafinal = cuenta;
			break;
			case("dolares"):
				if(cuenta.getClass().equals(CajaAhorroDolares.class))
					cuentafinal = cuenta;
			break;
			case("corriente"):
				if(cuenta.getClass().equals(CuentaCorriente.class))
					cuentafinal = cuenta;
			break;
			}
			if(cuentafinal!=null)return cuentafinal;
		}
		return null;
	}

	//----------------Carga de datos a memoria---------------------

	//Post: Se cargargan los datos a la base de datos 
	private void cargarDatos() throws IOException{
		cargarTarjetas();
		cargarMovimientos();
		cargarCuentas();
		cargarClientes();
	}
	//Post: Se cargan a la lista de tarjetas el archivo de tarjetas//
	private void cargarTarjetas() throws IOException{
		//Carga los datos del archivo de tarjetas.
		BufferedReader lector = new BufferedReader(new FileReader("datos/tarjetas.txt"));
		try {

			String linea;

			while ((linea=lector.readLine()) != null) {

				if (!linea.isEmpty()) {
					String[] datosTarjeta = linea.split(",");
					listaDeTarjetas.add(new Tarjeta(datosTarjeta[0],datosTarjeta[1],datosTarjeta[2]));
				}
			}

		} finally {

			lector.close();
		}


	}
	//Post: Se cargan a la lista de cuentas el archivo de cuentas//
	private void cargarCuentas() throws IOException{
		BufferedReader lector = new BufferedReader(new FileReader("datos/cuentas.txt"));
		try{

			String linea;

			while ((linea=lector.readLine()) != null) {

				if (!linea.isEmpty()) {
					String[] datosCuenta = linea.split(",");
					switch(datosCuenta[0]){
					case "01":
						listaDeCuentas.add(new CajaAhorroPesos(datosCuenta[1], Double.valueOf(datosCuenta[2]), agregarMovimientos(datosCuenta[1])));
						break;
					case "02":
						listaDeCuentas.add(new CuentaCorriente(datosCuenta[1], 
								Double.valueOf(datosCuenta[2]), Double.valueOf(datosCuenta[3]),agregarMovimientos(datosCuenta[1])));
						break;
					case "03":
						listaDeCuentas.add(new CajaAhorroDolares(datosCuenta[1], Double.valueOf(datosCuenta[2]), agregarMovimientos(datosCuenta[1])));	
						break;
					}
				}
			}

		} finally {

			lector.close();
		}

	}
	//Post: Se cargan a la lista de clientes el archivo de clientes//
	private void cargarClientes() throws IOException{
		BufferedReader lector = new BufferedReader(new FileReader("datos/clientes.txt"));
		try {

			String linea;

			while ((linea=lector.readLine()) != null) {

				if (!linea.isEmpty()) {
					String[] datosCliente = linea.split(",");
					Cliente nuevoCliente;
					listaDeClientes.add(nuevoCliente = new Cliente(datosCliente[0],datosCliente[1],
							buscarTarjetaPorCuit(datosCliente[0]), buscarCuentasPorAlias(datosCliente[1])));

				}
			}

		} finally {

			lector.close();
		}
	}
	//Post: Se cargan a la lista de clientes el archivo de clientes//	
	private void cargarMovimientos() throws IOException{
		BufferedReader lector = new BufferedReader(new FileReader("datos/movimientos.txt"));
		try {

			String linea;

			while ((linea=lector.readLine()) != null) {

				if (!linea.isEmpty()) {
					String[] datosMovimientos = linea.split(",");
					listaDeMovimientos.add(new Movimiento(datosMovimientos[0],datosMovimientos[1],datosMovimientos[2],Double.valueOf(datosMovimientos[3])));
				}
			}

		} finally {

			lector.close();
		}


	}

	//----------------Setters & Getters privados-------------------------

	//Post: Se cargan a la lista de clientes el archivo de clientes//	
	private Stack<Movimiento> agregarMovimientos(String alias){
		Stack<Movimiento> movimientos = new Stack<Movimiento>();

		for(Movimiento movimiento : listaDeMovimientos) {
			if(movimiento.getAlias().equals(alias)) {
				movimientos.add(movimiento);
			}
		}

		return movimientos;
	}
	/*Post: Se busca a la tarjeta por el cuit ingresado
	método utilizado para asiganarle a el cliente la tarjeta*/
	private Tarjeta buscarTarjetaPorCuit(String cuit) {

		for (Tarjeta tarjeta : listaDeTarjetas) {
			if (tarjeta.getCuit().equals(cuit)) {
				return tarjeta;
			}		
		}
		return null;
	}
	//Post: Busca Clientes por el alias
	private Cuenta buscarCuentaPorAlias(String alias) throws ErrorDeTransaccion {
		for (Cuenta cuenta : listaDeCuentas) {
			if (cuenta.getAlias().equals(alias)) {
				return cuenta;
			}
		}
		throw new ErrorDeTransaccion("La cuenta destinataria no existe");
	}
	//Post: Busca una lista de cuentas mediante el alias
	private ArrayList<Cuenta> buscarCuentasPorAlias(String alias) {
		ArrayList<Cuenta> cuentasDelCliente = new ArrayList<Cuenta>();
		String[] aliasA =  alias.split("\\.");
		alias = aliasA[0]+"."+aliasA[1];
		for (Cuenta cuenta: listaDeCuentas){
			if(cuenta.getAlias().contains(alias))
				cuentasDelCliente.add(cuenta);
		}
		return cuentasDelCliente;
	}
	//Post: Busca Clientes por el alias
	private Cliente buscarClientePorAlias(String alias) {
		for (Cliente cliente: listaDeClientes){
			if(cliente.getAlias().equals(alias))
				return cliente;
		}
		return null;
	}
	//Post: Devuelve un alias de un cliente de la lista de clientes
	private String getAliasCliente(String cuit) {

		for (Cliente cliente : listaDeClientes) {
			if (cliente.getCuit().equals(cuit)) {
				return cliente.getAlias();
			}		
		}
		return null;
	}

	//----------------Setters & Getters publicos-------------------------

	public void setMovimiento(Movimiento movimiento) {
		listaDeMovimientos.add(movimiento);
	}

	public Cuenta getCuenta(int indice) throws IOException{
		cargarCuentas();
		return listaDeCuentas.get(indice);
	}

	public Tarjeta getTarjeta(int indice) throws IOException{

		cargarTarjetas();
		return listaDeTarjetas.get(indice);
	}

	public Cliente getCliente(int indice) throws IOException{

		cargarClientes();
		return listaDeClientes.get(indice);
	}

	public void getUltimosMovimientos(Cuenta cuenta) throws IOException{

		consultarUltimosMovimientos(cuenta);
	}

	//----------------UI-----------------------------------------------------------

	//Post: Muestra en pantalla el menu principal	
	public void cargarPantallaPrincipal()  throws IOException, ErrorDeTransaccion{

		while(menuChoice!=0){
			System.out.println("\n\n********************************************");
			System.out.println("*  ingrese la operacion que desea realizar *");
			System.out.println("*                                          *");
			System.out.println("* 1- Consultas/solicitudes                 *");
			System.out.println("* 2- Extracciones/adelantos                *");
			System.out.println("* 3- Transferencia/deposito                *");
			System.out.println("* 4- Comprar dólares                       *");
			System.out.println("* 5- Cambiar cuenta actual                 *");
			System.out.println("* 0- Salir                                 *");
			System.out.println("********************************************");
			menuChoice = getIntInput();
			switch(menuChoice){
			case 1:
				consultar();
				break;
			case 2:
				extraccionOadelanto();
				break;
			case 3:
				transferenciaOdeposito();
				break;
			case 4:
				System.out.println("Ingrese el monto a comprar: ");
				int montoAComprar = getIntInput();
				if(poseeCuenta("dolares")!=null) {
					Cuenta cuentaDolares = poseeCuenta("dolares");
					operacion = new CompraDeDolares(currentCuenta, cuentaDolares, montoAComprar, this);
					operacion.ejecutar();
				}else{
					System.out.println("Usted no posee una caja de ahorro en dolares");
				}
				cargarPantallaPrincipal();
				break;
			case 5:
				elegirCuenta();
				break;
			case 0:
				break;
			default:
				System.out.println("Opción inválida. Intente nuevamente");
				break;
			}

		}
	}
	//Post: Muestra en pantalla el menu de consultas
	public void elegirCuenta()  throws IOException, ErrorDeTransaccion{
		try {
			while(menuChoice!=0){
				System.out.println("\n\n********************************************");
				System.out.println("*Elija la cuenta que va a utilizar         *");
				System.out.println("*1- Caja de ahorro en pesos                *");
				System.out.println("*2- Cuenta Corriente                       *");
				System.out.println("*3- Caja de ahorro en dólares              *");
				System.out.println("*0- Salir                                  *");
				System.out.println("********************************************");
				menuChoice = getIntInput();
				switch(menuChoice){
				case 1:
					if(poseeCuenta("pesos")!=null){
						currentCuenta = poseeCuenta("pesos");
						cargarPantallaPrincipal();
					}	
					else System.out.println("No posee ese tipo de cuenta");
					break;
				case 2:
					if(poseeCuenta("corriente")!=null) {
						currentCuenta = poseeCuenta("corriente");
						cargarPantallaPrincipal();
					}
					else System.out.println("No posee ese tipo de cuenta");
					break;
				case 3:
					if(poseeCuenta("dolares")!=null) {
						currentCuenta = poseeCuenta("dolares");
						cargarPantallaPrincipal();
					}
					else System.out.println("No posee ese tipo de cuenta");
					break;
				case 0:
					break;
				default:
					System.out.println("Opción inválida. Intente nuevamente");
					break;
				}

			}
		}catch(ErrorDeTransaccion e) {
			System.out.println(e.obtenerError());
			extraccionOadelanto();
		}
	}
	//Post: Muestra en pantalla el menu de consultas
	public void consultar() throws IOException, ErrorDeTransaccion{
		while(menuChoice!=0){
			System.out.println("\n\n********************************************");
			System.out.println("*¿Qué conocimiento desea adquirir?         *");
			System.out.println("*1- Ver saldo de cuenta                    *");
			System.out.println("*2- Ver alias de cuenta                    *");
			System.out.println("*3- Ver últimos 10 movimientos             *");
			System.out.println("*4- Volver                                 *");
			System.out.println("*0- Salir                                  *");
			System.out.println("********************************************");
			menuChoice = getIntInput();
			switch(menuChoice){
			case 1:
				System.out.println("El saldo de su "+currentCuenta.tipoToString+" es de: " + currentCuenta.getSaldo());
				cargarPantallaPrincipal();
				break;
			case 2:
				System.out.println("El alias de su "+currentCuenta.tipoToString+" es: " + currentCuenta.getAlias());
				cargarPantallaPrincipal();
				break;
			case 3:
				System.out.println("Últimos movimientos de su "+currentCuenta.tipoToString+": ");
				consultarUltimosMovimientos(currentCuenta);
				cargarPantallaPrincipal();
				break;
			case 4:
				cargarPantallaPrincipal();
				break;
			case 0:
				break;
			default:
				System.out.println("Opción inválida. Intente nuevamente");
				break;
			}

		}
	}
	//Post: Muestra en pantalla el menu principal
	public void extraccionOadelanto()throws IOException, ErrorDeTransaccion{
		try {
			System.out.println("\n\n********************************************");
			System.out.println("*  ingrese la operacion que desea realizar *");
			System.out.println("*                                          *");
			System.out.println("* 1- Extracción                            *");
			System.out.println("* 2- Volver                                *");
			System.out.println("* 0- Salir                                 *");
			System.out.println("********************************************");
			while(menuChoice!=0){
				menuChoice = getIntInput();
				switch(menuChoice){
				case 1:
					System.out.println("Ingrese el monto a retirar: ");
					operacion = new Retiro(currentCuenta, getIntInput(), this);
					operacion.ejecutar();
					cargarPantallaPrincipal();
					break;
				case 2:
					cargarPantallaPrincipal();
					break;
				case 4:
					elegirCuenta();
					break;
				case 0:
					break;
				default:
					System.out.println("Opción inválida. Intente nuevamente");
					break;
				}

			}
		}catch(ErrorDeTransaccion e) {
			System.out.println(e.obtenerError());
			extraccionOadelanto();
		}
	}
	//Post: Muestra en pantalla el menu de transferencias
	public void transferenciaOdeposito() throws IOException, ErrorDeTransaccion{
		try {
			System.out.println("\n\n********************************************");
			System.out.println("*           Trasnferencia/Depósito         *");
			System.out.println("*  ingrese la operacion que desea realizar *");
			System.out.println("*                                          *");
			System.out.println("* 1- Transferencia     	                   *");
			System.out.println("* 2- Depósito                              *");
			System.out.println("* 3- Volver                                *");
			System.out.println("* 0- Salir                                 *");
			System.out.println("********************************************");
			while(menuChoice!=0){
				menuChoice = getIntInput();
				switch(menuChoice){
				case 1:
					System.out.println("Ingrese el alias de la cuenta destino: ");
					String alias = teclado.nextLine();
					Cuenta cuentaDestino = buscarCuentaPorAlias(alias);
					System.out.println("Ingrese el monto a transferir: ");
					double montoTransfer = getDoubleInput();
					System.out.println("Ingrese el motivo de la transferencia: ");
					String motivo = teclado.nextLine();
					operacion = new Transferencia( currentCuenta, cuentaDestino, montoTransfer, motivo, this);
					operacion.ejecutar();
					cargarPantallaPrincipal();
					break;
				case 2:
					System.out.println("Ingrese el monto a depositar: ");
					double monto = getDoubleInput();
					operacion = new Deposito(currentCuenta, monto, this);
					operacion.ejecutar();
					cargarPantallaPrincipal();
					break;
				case 3:
					cargarPantallaPrincipal();
				case 0:
					break;
				default:
					System.out.println("Opción inválida. Intente nuevamente");
					break;
				}

			}
		}catch(ErrorDeTransaccion e) {
			System.out.println(e.obtenerError());
			transferenciaOdeposito();
		}
	}

	//Post: Imprime en pantalla y en un tiket de los ultimos movimientos de la cuenta actuva
	private void consultarUltimosMovimientos(Cuenta cuenta) throws IOException{
		FileWriter impresor = new FileWriter("tikets\\tiketUltimosMovimientos.txt");
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		impresor.write("**");impresor.append("\n");
		System.out.println("**");System.out.println();
		System.out.println("Fecha             Motivo                  Monto");System.out.println();
		impresor.write("Fecha y Hora: "+formatter.format(date));impresor.append("\n");
		impresor.write("Cuenta de origen: "+cuenta.getAlias());impresor.append("\n");
		impresor.write("Ultimos movimientos: ");impresor.append("\n");
		for (String string : cuenta.getUltimosMovimientos()) {
			String[] stringA = string.split(",") ;
			System.out.println(stringA[0]+"__"+stringA[1]+"__"+stringA[3]);
			impresor.write("    "+stringA[0]+""+stringA[1]+""+stringA[3]);impresor.append("\n");
		}
		impresor.write("**");
		System.out.println();System.out.println("**");
		impresor.close();
	}
	//Post: Recibe una respuesta del tipo int 
	private int getIntInput(){
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
	private double getDoubleInput(){
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
}