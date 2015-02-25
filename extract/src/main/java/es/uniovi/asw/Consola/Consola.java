package es.uniovi.asw.Consola;

import es.uniovi.asw.Parser.Document;

public class Consola {

	/**
	 * EJECUTA LA CONSOLA DE LA APLICACION.
	 * LEE EL FICHERO, SU EXTENSION, Y LLAMA A LAS CAPAS INFERIORES PARA 
	 * PARSEO Y PERSISTENCIA
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Wellcome to Trivial Extractor");
		//Formato: nombreArchivoDeEntrada.tipoDeArchivoDeEntrada nombreArchivoDeSalida
		if(args.length!=2)
			throw new Exception("Comando mal introducido ");
		Document archivoEntrada = new Document("data/"+args[0]);
		String nombreArchivoEntrada = ConsolaUtils.getFileName(archivoEntrada.getName());
		String entradaExtension = ConsolaUtils.getFileExtension(archivoEntrada);
		String nombreArchivoSalida = args[1]; //Sin extensin JSON
		
		ConsolaParser parser = new ConsolaParser(archivoEntrada);
		System.out.println("Extensi�n del archivo correcta ");
		System.out.println("Leyendo fichero...");
		parser.reader();
		if(!parser.verificarFormato()) throw new Exception("Formato de entrada no valido");
		else System.out.println("Formato del fichero correcto");
		parser.transform();
		System.out.println("Transformando a JSON");
		System.out.println("-------------Visualizacion de JSON--------------------------");
		parser.depurarJSON();
		ConsolaUtils.saveFile(parser.getTransformado(), nombreArchivoSalida);
		System.out.println("Transformaci�n completada");
	}

}
