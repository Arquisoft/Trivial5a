package es.uniovi.asw.extractor.parser;

public interface TipoDocumento {
	
	public String[] transform(String[] leido);
	
	public boolean verify (String[] leido);

}
