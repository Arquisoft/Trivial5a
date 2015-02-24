package es.uniovi.asw.Parser;

public interface TipoDocumento {
	
	public String[] transform(String[] leido);
	
	public boolean verify (String[] leido);

}
