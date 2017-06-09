/*
 * This java program takes two arguments and validates the XML File aganist XML File. 
 * Arguments => 1) XML File 2) XSD
 *
 * void printHelp = Prints help for user
 *
 * boolean validation = validates the XML file aganist XSD schema,
 * 						if file is valid, returns true
 * 						otherwise, returns false
 *
 */

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import java.util.*;

public class xmlvalidate {

	public static void main(String[] args) {
		
		// Check for the arguments length
		if(args.length != 2){
			printHelp();
		} else if (args.length ==2){
			boolean result = validation(args[0],args[1]);

			if(result){
				System.out.println("Valid XML File");
			} else{
				System.out.println("Invalid XML File");
			}
		}
	}
	
	// Prints the Help
	public static void printHelp(){
		System.out.println("Please pass the <XMLFile> space <XSDFile> and press Enter");
	}


	// Validates the xml file from xsd
	public static boolean validation(String xmlPath, String xsdPath){
		try{
			// SchemaFactory pulls the XML stands format
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			// Reading and saving the schema from the xsd file
			Schema schema = factory.newSchema(new File(xsdPath));
			// Created a validator for the xml file
			Validator isValid = schema.newValidator();
			// Check the XML file aganist XSD schema
			isValid.validate(new StreamSource(new File(xmlPath)));
		} catch(IOException io){
			System.out.println(io.getMessage());
			return false;
		} catch(SAXException e){
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}
	
}