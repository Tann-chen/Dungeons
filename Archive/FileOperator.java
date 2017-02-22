package Archive;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The class is used to operate the .xml file, including reading data from file and saving data to file.
 * @author Tann Chen
 */
public class FileOperator {

    /**
     * The method is used to read the data from a file
     * @param filePath the path name of the file
     * @return root element of the file
     */
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

    /**
     * The method is uesd to write the data to a specific file
     * @param file where to be wrote the data
     * @param doc the document to write
     */
    //TODO:dom4j是否会直接覆盖文档内容？若不会覆盖，需要先清空文档内容
    public static void fileWriter(File file,Document doc){


        XMLWriter writer = null;
        try{
            writer = new XMLWriter(new FileWriter(file));
            writer.write(doc);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            if(writer!=null){
                try{
                    writer.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

}
