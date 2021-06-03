
@SuppressWarnings("serial")
public class ErrorDeValidacion extends Exception {

	private String error;

	public ErrorDeValidacion (String error){
		this.error = error;
	}

	public String obtenerError() {
		return error;
	}
}
