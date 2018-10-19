package piergiuseppe82.converters.jmarkdownconverter.plugins;

import java.util.List;
import java.util.Map;

import org.markdown4j.Plugin;

/**
 * @author https://github.com/piergiuseppe82
 *
 */
public class JavaCodePlugin extends Plugin {
	private static final String separator = " =;(){}+-/\\><!%*:";
	private static final String[] keywords = new String[]{
		//FIXME KEYWORDS LIST IS NOT COMPLETE!!!!!!
		"abstract",
		"assert",
		"boolean",
		"break",
		"byte",
		"case",
		"catch",
		"char",
		"class",
		"const",
		"continue",
		"default",
		"do",
		"double",
		"else",
		"enum",
		"extends",
		"final",
		"finally",
		"float",
		"for",
		"goto",
		"if",
		"implements",
		"import",
		"instanceof",
		"int",
		"interface",
		"long",
		"native",
		"new",
		"package",
		"private",
		"protected",
		"public",
		"return",
		"short",
		"static",
		"strictfp",
		"super",
		"switch",
		"synchronized",
		"this",
		"throw",
		"throws",
		"transient",
		"try",
		"void",
		"volatile",
		"while",
		"true",
		"false",
		"null"	
	}; 
	

	public JavaCodePlugin() {
		super("java");
	}
	
	@Override
	public void emit(StringBuilder out, List<String> lines,
			Map<String, String> params) {
		if(lines == null) return;
		StringBuilder myst = new StringBuilder();
		myst.append("<div style=\"width=100%; font-family:monospace; background-color: #cccccc; color:black;\">");
		boolean comment = false;
		for (String string : lines) {
				String newStr = "";
//				string = replaceHtmlSpecial(string);
			 	if(string.trim().startsWith("//")){
			 		//IS COMMENT
			 		newStr = "<span style=\"color:green;margin:0;padding:0;\">"+replaceHtmlSpecial(string).replaceAll(" ", "&nbsp;")+"</span>";
			 	}else if(string.trim().startsWith("/*")){
			 		//IS COMMENT
			 		comment = true;
			 		newStr = "<span style=\"color:green;margin:0;padding:0;\">"+replaceHtmlSpecial(string).replaceAll(" ", "&nbsp;")+"</span>";
			 	}else if(string.trim().endsWith("*/")){
			 		//IS COMMENT
			 		comment = false;
			 		newStr = "<span style=\"color:green;margin:0;padding:0;\">"+replaceHtmlSpecial(string).replaceAll(" ", "&nbsp;")+"</span>";
			 	}else if(comment){
			 		//IS COMMENT
			 		newStr = "<span style=\"color:green;margin:0;padding:0;\">"+replaceHtmlSpecial(string).replaceAll(" ", "&nbsp;")+"</span>";
			 	}else{
			 		char[] charArray = string.toCharArray();
			 		String word = "";
			 		boolean isString = false;
			 		boolean isInlineComment = false;
			 		for (char c : charArray) {
			 			if(c == '"' && !isString && !isInlineComment){
			 				isString = true;
			 				word = word +c;
			 			}else if(c == '"' && isString && !isInlineComment){
				 				isString = false;
				 				word = word +c;	
				 				newStr = newStr + "<span style=\"color:magenta;margin:0;padding:0;\">"+replaceHtmlSpecial(word)+"</span>";	
				 				word = "";
			 			}else if(!isInlineComment && !isString && (separator.contains(""+c))){	
			 				for (String keyword : keywords) {
								if(word.equals(keyword)){
									//IS KEYWORD
									word = word.replaceAll(keyword, "<span style=\"color:red;margin:0;padding:0;\">"+replaceHtmlSpecial(keyword)+"</span>");
									newStr = newStr + word;
									word = "";
								}																	
							}	
			 				if(word.toUpperCase().matches("\\d+\\.\\d+")){
					 			//IS DECIMAL
								word = word.toUpperCase().replaceAll("\\d+\\.\\d+", "<span style=\"color:blue;margin:0;padding:0;\">"+replaceHtmlSpecial(word)+"</span>");
								newStr = newStr + word;	
								word = "";
							}
					 		if(word.toUpperCase().matches("\\d+")){
					 			//IS INTEGER
								word = word.toUpperCase().replaceAll("\\d+", "<span style=\"color:blue;margin:0;padding:0;\">"+replaceHtmlSpecial(word)+"</span>");
								newStr = newStr + word;	
								word = "";
							}					 		
					 		if(c == '-' && word.equals("-")){
					 			isInlineComment = true;
					 			word = word +c;	
					 		}else if(c != '-'){	
					 			if(!word.isEmpty()){
						 			newStr = newStr + word;
						 			word = "";
						 		}
						 		newStr = newStr + (c==' '?"&nbsp;":c);
					 		}else{
					 			word = word +c;	
					 		}
						}else{
							word = word +c;	
							if(!isInlineComment && !isString && (word.startsWith("--") || word.startsWith("#"))){
								isInlineComment = true;
							}
						}
					}
			 		//END OF LINE
			 		if(!word.isEmpty()){
			 			if(isInlineComment){
			 				//IS COMMENT
			 				newStr = newStr + "<span style=\"color:green;margin:0;padding:0;\">"+replaceHtmlSpecial(word).replaceAll(" ", "&nbsp;")+"</span>";
			 				word = "";
			 			}else{
			 				//IS KEYWORD
			 				for (String keyword : keywords) {
								if(word.equals(keyword)){
									word = word.replaceAll(keyword, "<span style=\"color:red;margin:0;padding:0;\">"+replaceHtmlSpecial(keyword)+"</span>");
									newStr = newStr + word;
									word = "";
								}																
							}
			 				if(word.toUpperCase().matches("\\d+\\.\\d+")){
					 			//IS DECIMAL
								word = word.toUpperCase().replaceAll("\\d+\\.\\d+", "<span style=\"color:blue;margin:0;padding:0;\">"+replaceHtmlSpecial(word)+"</span>");
								newStr = newStr + word;	
								word = "";
							}
					 		if(word.toUpperCase().matches("\\d+")){
					 			//IS INTEGER
								word = word.toUpperCase().replaceAll("\\d+", "<span style=\"color:blue;margin:0;padding:0;\">"+replaceHtmlSpecial(word)+"</span>");
								newStr = newStr + word;	
								word = "";
							}
			 				newStr = newStr + word;
				 			word = "";
			 			}			 			
			 			
			 		}
			 	}
							
				myst.append(newStr);
				myst.append("<br/>");
		}
		myst.append("</div>\n");
		out.append(myst.toString());
	}

	private String replaceHtmlSpecial(String string) {
		if(string == null) return string;
		
		return string.replaceAll("<", "&lt;")
				.replaceAll(">", "&rt;");
	}

}
