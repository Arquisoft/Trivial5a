package es.uniovi.asw.trivial;

import java.io.IOException;

import es.uniovi.asw.Parser.Parser;
import es.uniovi.asw.conf.Driver;

public class Extractor {
	
	public void usage() {
		System.out.println("Wellcome to Trivial Extractor");
	}
	public int run(String[] args) throws IOException {
		
		
		usage();
		
		
		Parser p = new Parser();
		Driver d = new Driver();
		p.reader();
		if(p.verificarFormato())
		p.transform();
		d.save(p.getTransformado());
		
		
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
        new Extractor().run(args);
    }	
}
