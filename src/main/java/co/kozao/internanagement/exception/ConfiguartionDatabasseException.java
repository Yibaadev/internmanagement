package co.kozao.internanagement.exception;

public class ConfiguartionDatabasseException extends RuntimeException {
   
	public ConfiguartionDatabasseException(String message) {
		super(message);
	}
	
	public ConfiguartionDatabasseException( String message, Throwable cause ) {
		super( message, cause );
		}
	
	public ConfiguartionDatabasseException( Throwable cause ) {
		super( cause );
		}
	
}
