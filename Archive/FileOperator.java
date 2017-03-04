package archive;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.swing.*;
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
        File readFile=new File(filePath);
        if(readFile.exists()) {
            SAXReader reader = new SAXReader();
            Document document = null;
            try {
                document = reader.read(readFile);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            Element root = document.getRootElement();
            return root;
        }
        else {
            System.out.println("You don't have any records, create one");
            return null;
        }
    }

    /**
     * The method is uesd to write the data to a specific file
     * @param file where to be wrote the data
     * @param doc the document to write
     */
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


    /**
     * The method is used to open a File chooser, select a file, read the file
     * @return rootElement of the xml file
     */
    public static Element fileChooser(){
        JFileChooser fileChooser =new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        return root;
    }
}

