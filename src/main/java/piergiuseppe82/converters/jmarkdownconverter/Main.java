package piergiuseppe82.converters.jmarkdownconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.markdown4j.JSONCodePlugin;
import org.markdown4j.JavaCodePlugin;
import org.markdown4j.Markdown4jProcessor;
import org.markdown4j.PropertiesCodePlugin;
import org.markdown4j.SqlCodePlugin;
import org.xhtmlrenderer.pdf.ITextRenderer;



/**
 * @author https://github.com/piergiuseppe82
 *
 */
public class Main 
{
    public static void main( String[] args ) throws Exception
    {  
    	if(args == null || args.length ==0){
    		throw new Exception("No arguments found.");
    	}
    	if(args[0].equalsIgnoreCase("--help")){
			printHelp();return;
		}
		if(!new File(args[0]).exists()){
			throw new Exception("File "+args[0]+" not exists");
		}
    	byte[] encoded = Files.readAllBytes(Paths.get(args[0]));
        Markdown4jProcessor paDownProcessor = new Markdown4jProcessor();
        paDownProcessor.registerPlugins(new PropertiesCodePlugin());
        paDownProcessor.registerPlugins(new SqlCodePlugin());
        paDownProcessor.registerPlugins(new JSONCodePlugin());
        paDownProcessor.registerPlugins(new JavaCodePlugin());
        String markdownToHtml = paDownProcessor.process( new String(encoded, "UTF-8"));
		 
        if(args.length > 1 && args[1] != null && args[1].endsWith(".html")){
        	 testPath( args[1]);
        	 FileWriter out = new FileWriter(args[1]);
             out.write(markdownToHtml);
             out.flush();
             out.close();
        }else if(args.length > 1 && args[1] != null && args[1].endsWith(".pdf")){
        	testPath( args[1]);
        	OutputStream os = new FileOutputStream(new File(args[1]));
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(markdownToHtml);
            renderer.layout();
            renderer.createPDF(os);
            os.close();
        }else{
        	System.out.println(markdownToHtml);
        }
       
        
        
    }

	private static void printHelp() {
		InputStreamReader isReader=   new InputStreamReader(Main.class.getResourceAsStream("/help.txt"));
		BufferedReader br = new BufferedReader(isReader);  
		try {
			String sCurrentLine;			
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}		
	}

	private static void testPath(String string) throws Exception {
		File file = new File(string);
		String parent = file.getParent();
		if(parent != null && !new File(parent).isDirectory()){
			throw new Exception("Path "+string+" unreacheable");
		}
		
	}
}
