package neptune.client.process;

public class ProcessException extends Exception{

	public ProcessException() {
		super("ProcessException");
	}
	
	public ProcessException(String message){
		super(message);
	}

}
