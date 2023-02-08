import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        NodeList configurationList = null;

        //Instancier la Factory qui permet d'accéder à un parser (appelé DocumentBuilder)
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            //Récupérer le parser
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Parser le fichier XML
            Document doc = builder.parse("src\\configuration.xml");

            //Récupération d'un ensemble d'éléments ayant le même nom
            configurationList = doc.getElementsByTagName("configuration");
            Node config = configurationList.item(0);
            if(config.getNodeType()==Node.ELEMENT_NODE){
                NodeList metaSim = config.getChildNodes();
                for (int j=0;j< metaSim.getLength();j++){
                    Node meta = metaSim.item(j);
                    if(meta.getNodeType()==Node.ELEMENT_NODE){
                        NodeList usines = meta.getChildNodes();
                        for(int i=0;i< usines.getLength();i++){
                            Node usine = usines.item(i);
                            System.out.println(usine.getNodeName());
                        }
                    }
                }
            }

        }   catch(ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        /*
        doc.getDocumentElement().normalize();
        //accès à la racine du document
        doc.getDocumentElement();
         */
    }
}