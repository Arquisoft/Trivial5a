package main.java.Consola;

import java.io.File;

import main.java.Parser.Document;

public class Consola {

	public static void main(String[] args) throws Exception {
		//Formato: nombreArchivoDeEntrada.tipoDeArchivoDeEntrada nombreArchivoDeSalida
		Document archivoEntrada = new Document("data/"+args[0]);
		String nombreArchivoEntrada = ConsolaUtils.getFileName(archivoEntrada.getName());
		String entradaExtensión = ConsolaUtils.getFileExtension(archivoEntrada);
		String nombreArchivoSalida = args[1]; //Sin extensión JSON
		
		ConsolaParser parser = new ConsolaParser(archivoEntrada);
		System.out.println("Extensión del archivo correcta ");
		System.out.println("Leyendo fichero...");
		parser.reader();
		if(!parser.verificarFormato()) throw new Exception("Formato de entrada no valido");
		else System.out.println("Formato del fichero correcto");
		parser.transform();
		System.out.println("Transformando a JSON");
		ConsolaUtils.saveFile(parser.getTransformado(), nombreArchivoSalida);
		System.out.println("Transformación completada");
	}

}
