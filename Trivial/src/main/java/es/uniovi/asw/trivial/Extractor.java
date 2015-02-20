package es.uniovi.asw.trivial;

import java.io.IOException;

import Parser.Parser;

public class Extractor {
	
	public void usage() {
		System.out.println("Wellcome to Trivial Extractor");
	}
	public int run(String[] args) throws IOException {
		
		
		usage();
		
		
		Parser p = new Parser();
		p.reader();
		if(p.verificarFormato())
		p.transform();
		
		
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
        new Extractor().run(args);
    }	
}
