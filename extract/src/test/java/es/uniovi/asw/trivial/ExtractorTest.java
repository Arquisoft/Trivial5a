package es.uniovi.asw.trivial;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ExtractorTest {
	
	@Test
	public void emptyExtractor() throws Exception {
		String args[] = {};
		Extractor ext = new Extractor();
	    assertThat(ext.run(args)).isEqualTo(0);
	  }

}
