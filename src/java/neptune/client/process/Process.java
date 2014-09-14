package neptune.client.process;

public interface Process {
	
	void runProcess() throws ProcessException;
	Object getResult();

}
