package es.uniovi.asw.extractor.parser;

import java.io.File;

public class Document extends File {
	
	private static final long serialVersionUID = 1L;
	private TipoDocumento tipo;

	public Document(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	
	public void transform()
	{
		
		
	}


	public TipoDocumento getTipo() {
		return tipo;
	}


	public void setTipo(TipoDocumento tipo) {
		this.tipo = tipo;
	}
	
	

}
