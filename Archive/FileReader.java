package Archive;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * The class is used to load the .xml file, return the root element of the file.
 * @author Tann Chen
 */
public class FileReader {

    public static Element fileReader(String filePath){
        SAXReader reader = new SAXReader();
        Document document= null;
        try{
            document=reader.read(filePath);
        }
        catch (DocumentException e){
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        return root;
    }

}
