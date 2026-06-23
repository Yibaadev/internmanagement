package co.kozao.internanagement.exception;

public class DaoExecption  extends RuntimeException{
    
	public DaoExecption(String message) {
		super(message);
	}
	
	public DaoExecption( String message, Throwable cause ) {
		super( message, cause );
		}
	
	public DaoExecption( Throwable cause ) {
		super( cause );
		}
}
