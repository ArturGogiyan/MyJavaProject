
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Comparator;


/**
 * Created by edvanchi on 10.04.2017.
 */
public class ReadTails implements Runnable  {
    String address;
    static private String exception ="";
    static private int value=0;
    static public boolean flag=false;
    public ReadTails(String address){
        this.address=address;
    }

    public void run() {
        try {
            exception="";
            String color="nope";
            int length=0;
            value=0;
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(address);

            Node root = document.getDocumentElement();
            NodeList tails = root.getChildNodes();
            for (int i = 0; i < tails.getLength(); i++) {

                Node book = tails.item(i);
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                    for (int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            switch (bookProp.getNodeName()) {
                                case "Color":
                                    color = bookProp.getChildNodes().item(0).getTextContent();
                                    break;
                                case "Length":
                                    length = Integer.parseInt(bookProp.getChildNodes().item(0).getTextContent());
                                    break;
                            }
                        }
                        }
                    value++;
                    Main.tails.add(new MTsTail(color,length));
                    }
                }

        } catch (Exception e) {
            exception=e.getMessage();
        }
        flag = true;
    }
    public static String readException(){
       return exception;
    }
    public static int readValue(){
        return value;
    }
}