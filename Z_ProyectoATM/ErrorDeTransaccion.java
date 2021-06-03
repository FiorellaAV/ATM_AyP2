
@SuppressWarnings("serial")
public class ErrorDeTransaccion extends Exception{

	private String error;
	
	public ErrorDeTransaccion (String error) {

		this.error = error;
	}
	public String obtenerError() {
		return error;
	}
}
