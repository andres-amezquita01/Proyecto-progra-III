package exceptions;
/**
 * 
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *manejo exepcion
 */
public class OnlyNumbersException extends Exception{
	private static final long serialVersionUID = 1L;

	public OnlyNumbersException() {
		super("Por Favor, Solo Ingrese Numeros.");
	}
}

