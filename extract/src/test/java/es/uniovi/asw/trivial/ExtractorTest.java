package es.uniovi.asw.trivial;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;

import org.junit.Test;

import es.uniovi.asw.trivial.Extractor;

public class ExtractorTest {
	
	@Test
	public void emptyExtractor() throws IOException {
		String args[] = {};
		Extractor ext = new Extractor();
	    assertThat(ext.run(args)).isEqualTo(0);
	  }

}
