package es.uniovi.asw.Consola;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

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
		if(args.length==2) 		// La salida siempre es en formato JSON
		{
			
		Document archivoEntrada = new Document("src/main/java/es/uniovi/asw/data/"+args[0]);
		//String nombreArchivoEntrada = ConsolaUtils.getFileName(archivoEntrada.getName());
		//String entradaExtension = ConsolaUtils.getFileExtension(archivoEntrada);
		String nombreArchivoSalida = args[1]; //Sin extension JSON
		
		ConsolaParser parser = new ConsolaParser(archivoEntrada);
		System.out.println("Extension del archivo correcta ");
		System.out.println("Leyendo fichero...");
		parser.reader();
		if(!parser.verificarFormato()) throw new Exception("Formato de entrada no valido");
		else System.out.println("Formato del fichero correcto");
		parser.transform();
		System.out.println("Transformando a JSON");
		System.out.println("-------------Visualizacion de JSON--------------------------");
		parser.depurar();
		ConsolaUtils.saveFile(parser.getTransformado(), nombreArchivoSalida);
		System.out.println("Transformacion completada");
		
		parser.persist();

		}
		
		//Formato: nombreArchivoDeEntrada tipoEntrada nombreArchivoDeSalida tipoArchivoDeSalida
		else if(args.length==4) // Se puede elegir el formato de salida
		{			
			Document archivoEntrada = new Document("src/main/java/es/uniovi/asw/data/"+args[0]);
			String entradaExtension = args[1];
			String salidaExtension = args[3].toUpperCase();
			
			ConsolaParser parser = new ConsolaParser(archivoEntrada, entradaExtension);
			System.out.println("Extension del archivo correcta ");
			System.out.println("Leyendo fichero...");
			parser.reader();
			if(!parser.verificarFormato()) throw new Exception("Formato de entrada no valido");
			else System.out.println("Formato del fichero correcto");
			ConsolaUtils.transform(parser, salidaExtension);
			System.out.println("Transformando a "+salidaExtension);
			System.out.println("-------------Visualizacion de "+salidaExtension+"--------------------------");
			parser.depurar();
			ConsolaUtils.saveFile(parser.getTransformado(),args[2],salidaExtension);
			System.out.println();
			
			parser.persist();
			
		}
		else if(args.length==0)
		{	
			System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000000");
			InputStream file =Consola.class.getResourceAsStream("preguntas.gift");
			System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000000");
			OutputStream out = new FileOutputStream(new File(System.getProperty("user.home")+"\\preguntas.gift"));
			System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000000");
			int read = 0;
			System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000000");
			byte[] bytes = new byte[1024];
			System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000000");
			while ((read = file.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000000");
			Document archivoEntrada = new Document(System.getProperty("user.home")+"\\preguntas.gift");
			System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000000");
			ConsolaParser parser = new ConsolaParser(archivoEntrada);
			System.out.println("Extension del archivo correcta ");
			System.out.println("Leyendo fichero...");
			parser.reader();
			if(!parser.verificarFormato()) throw new Exception("Formato de entrada no valido");
			else System.out.println("Formato del fichero correcto");
			parser.transform();
			System.out.println("Transformando a JSON");
			System.out.println("-------------Visualizacion de JSON--------------------------");
			//parser.depurar();
			//ConsolaUtils.saveFile(parser.getTransformado(), "JSON");
			System.out.println("Transformacion completada");
			
			parser.persist();
		}
		else throw new Exception("Comando mal introducido ");
	}

}
