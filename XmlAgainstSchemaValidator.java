import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class XmlAgainstSchemaValidator {

    private static final String FILES_DIRECTORY = "/home/nibals/Documents/xmlsToValidate";
    private static URL schemaFile;

    static {
        try {
            schemaFile = new URL("file://" + FILES_DIRECTORY + "/logistics2.xsd");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        Files.list(Paths.get(FILES_DIRECTORY))
                .filter(path -> path.toString().contains(".xml"))
                .forEach(XmlAgainstSchemaValidator::validate);
    }

    private static void validate(Path path) {
        Source xmlFile = new StreamSource(path.toFile());
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
        } catch (SAXException e) {
            System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
