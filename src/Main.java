import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //Instancier la Factory qui permet d'accéder à un parser (appelé DocumentBuilder)
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ArrayList<int[]> donneesChemin = new ArrayList<>();

        try {
            //Récupérer le parser
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Parser le fichier XML
            Document doc = builder.parse("src\\configuration.xml");

            //Récupération d'un ensemble d'éléments ayant le même nom
            NodeList listeChemins = doc.getElementsByTagName("simulation").item(0).getChildNodes();;
            for(int i=0; i<listeChemins.getLength(); i++){
                Node chemins = listeChemins.item(i);
                if(chemins.getNodeType()==Node.ELEMENT_NODE && chemins.getNodeName()=="chemins"){
                    NodeList chemin = chemins.getChildNodes();
                    for (int j=0;j<chemin.getLength();j++){
                        Node donnees = chemin.item(j);
                        if(donnees.getNodeType()==Node.ELEMENT_NODE){
                            Element elementDonnee = (Element) donnees;
                            donneesChemin.add(new int[]{Integer.parseInt(elementDonnee.getAttribute("de")),Integer.parseInt(elementDonnee.getAttribute("vers"))});
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