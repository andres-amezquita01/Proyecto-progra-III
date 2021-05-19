package exceptions;

public class OnlyNumbersException extends Exception{
	private static final long serialVersionUID = 1L;

	public OnlyNumbersException() {
		super("Por Favor, Solo Ingrese Numeros.");
	}
}

