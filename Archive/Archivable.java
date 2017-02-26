package archive;

import org.dom4j.Element;

/**
 * This interface will be implemented for every class that needs decode and enode from/to an element in XML file
 * @author Tann chen
 */
public interface Archivable {
    /**
     *the method will be override to decode an element to the data in system.
     */
    public abstract void decode(Element element);

    /**
     *the method will be override to encode the data of an instance to an element which will be saved in xml file
     */
    public abstract Element encode();
}
