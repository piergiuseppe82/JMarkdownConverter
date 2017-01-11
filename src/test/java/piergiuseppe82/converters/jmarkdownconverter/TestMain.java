package piergiuseppe82.converters.jmarkdownconverter;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import piergiuseppe82.converters.jmarkdownconverter.Main;

/**
 * @author https://github.com/piergiuseppe82
 *
 */
public class TestMain {
	private static final String EXAMPLE_MD = "/CheatsheetExample.md";
	private File pdfFileOutput = new File("CheatsheetExample.pdf");
	private File htmlFileOutput = new File("CheatsheetExample.html");
	private File stdFileOut = new File("output.txt");
	private URL fileInput = TestMain.class.getResource( EXAMPLE_MD );	
	
	@Before
	public void init() {
		pdfFileOutput.deleteOnExit();	
		htmlFileOutput.deleteOnExit();	
		stdFileOut.deleteOnExit();

	}
	
	@Test
	public void mainTestPdf() throws Exception{
		String[] args = new String[]{fileInput.getPath(),pdfFileOutput.getAbsolutePath()};
		Main.main(args);
		Assert.assertTrue(pdfFileOutput.exists());
	}
	
	@Test
	public void mainTestHtml() throws Exception{
		String[] args = new String[]{fileInput.getPath(),htmlFileOutput.getAbsolutePath()};
		Main.main(args);
		Assert.assertTrue(htmlFileOutput.exists());
	}
	
	@Test
	public void mainTestHelp() throws Exception{
		String[] args = new String[]{"--help"};
		PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(stdFileOut.getAbsoluteFile())));
		System.setOut(out);
		Main.main(args);
		out.flush();
		out.close();
		Assert.assertTrue(stdFileOut.exists());
		BufferedReader br = null;
		FileReader fr = null;
		boolean found = false;
		try {
			fr = new FileReader(stdFileOut);
			br = new BufferedReader(fr);
			String sCurrentLine;			
			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.contains("java -jar JMarkdownConverter-[VERSION].jar --help")){
					found = true;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		Assert.assertTrue(found);
	}
	
	@Test
	public void mainTestHtmlToSTDOUT() throws Exception{
		String[] args = new String[]{fileInput.getPath()};
		PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(stdFileOut.getAbsoluteFile())));
		System.setOut(out);
		Main.main(args);
		out.flush();
		out.close();
		Assert.assertTrue(stdFileOut.exists());
		BufferedReader br = null;
		FileReader fr = null;
		boolean found = false;
		try {
			fr = new FileReader(stdFileOut);
			br = new BufferedReader(fr);
			String sCurrentLine;			
			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.contains("</html>")){
					found = true;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		Assert.assertTrue(found);
	}
	
	@Test
	public void mainTestInputFileNotExist() throws Exception{
		String[] args = new String[]{"ghostFile.md",pdfFileOutput.getAbsolutePath()};
		boolean cat = false;
		try {
			Main.main(args);
		} catch (Exception e) {
			cat = true;
			Assert.assertEquals("File ghostFile.md not exists", e.getMessage());
		}
		Assert.assertTrue(cat);
		
	}
	
	@Test
	public void mainTestOutputPathNotExist() throws Exception{
		String[] args = new String[]{fileInput.getPath(),"ghostPath/ghostFile.pdf"};
		boolean cat = false;
		try {
			Main.main(args);
		} catch (Exception e) {
			cat = true;
			Assert.assertEquals("Path ghostPath/ghostFile.pdf unreacheable", e.getMessage());
		}		
		Assert.assertTrue(cat);
	}
	
	@Test
	public void mainTestNoArgumnts() throws Exception{
		String[] args = new String[]{};
		boolean cat = false;
		try {
			Main.main(args);
		} catch (Exception e) {
			cat = true;
			Assert.assertEquals("No arguments found.", e.getMessage());
		}		
		Assert.assertTrue(cat);
	}

	@After
	public void end() {
		pdfFileOutput.deleteOnExit();	
		htmlFileOutput.deleteOnExit();	
		stdFileOut.deleteOnExit();
	}
}
