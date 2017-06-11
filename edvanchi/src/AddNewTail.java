

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by edvanchi on 12.04.2017.
 */
public class AddNewTail {
    public static void addNewTail(Document document, int len, String color) throws TransformerFactoryConfigurationError, DOMException {
        Node root = document.getDocumentElement();
        Element tail = document.createElement("Tail");
        Element ccolor = document.createElement("Color");
        ccolor.setTextContent(color);
        Element length = document.createElement("Length");
        length.setTextContent(Integer.toString(len));
        tail.appendChild(ccolor);
        tail.appendChild(length);
        root.appendChild(tail);
        writeDocument(document,len);
    }

    public static void writeDocument(Document document, int length) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("TailCatalogue.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);

        }
    }
}