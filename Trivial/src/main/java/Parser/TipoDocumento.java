package main.java.Parser;

public interface TipoDocumento {
	
	public String[] transform(String[] leido);
	
	public boolean verify (String[] leido);

}
