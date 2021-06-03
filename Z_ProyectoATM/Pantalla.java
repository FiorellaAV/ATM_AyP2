import java.io.IOException;

public interface Pantalla {

	//Post: Muestra en pantalla el menu principal
	public void cargarPantallaPrincipal()  throws IOException, ErrorDeTransaccion;
	//Post: Muestra en pantalla el menu de consultas
	public void consultar() throws IOException, ErrorDeTransaccion;
	//Post: Muestra en pantalla el menu principal
	public void extraccionOadelanto()throws IOException, ErrorDeTransaccion;
	//Post: Muestra en pantalla el menu de transferencias
	public void transferenciaOdeposito() throws IOException, ErrorDeTransaccion;
	//Post: Selecciona la cuenta con la que se va a trabajar
	public void elegirCuenta()  throws IOException, ErrorDeTransaccion;

}

