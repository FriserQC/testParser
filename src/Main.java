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
        ArrayList<ArrayList<String[]>> donneesUsines = new ArrayList<>();

        try {
            //Récupérer le parser
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Parser le fichier XML
            Document doc = builder.parse("src\\configuration.xml");

            //Récupération d'un ensemble d'éléments ayant le même nom
            NodeList usines = doc.getElementsByTagName("metadonnees").item(0).getChildNodes();
            for(int i=0; i<usines.getLength(); i++){
                Node usine = usines.item(i);
                if(usine.getNodeType()==Node.ELEMENT_NODE && usine.getNodeName()=="usine"){
                    ArrayList<String[]> u = new ArrayList<>();
                    donneesUsines.add(u);
                    NodeList tUsine = usine.getChildNodes();
                    Element typeUsine = (Element) usine;
                    //System.out.println(typeUsine.getAttribute("type"));
                    for(int j=0; j<tUsine.getLength();j++){
                        Node donnees = tUsine.item(j);
                        if(donnees.getNodeType()==Node.ELEMENT_NODE && donnees.getNodeName()=="icones"){
                            NodeList icones = donnees.getChildNodes();
                            //System.out.println(donnees.getNodeName());
                            for(int k=0; k<icones.getLength();k++){
                                Node icone = icones.item(k);
                                if(icone.getNodeType()==Node.ELEMENT_NODE){
                                    Element anIcone = (Element) icone;
                                    //System.out.println(anIcone.getAttribute("type"));
                                    //System.out.println(anIcone.getAttribute("path"));
                                    u.add(new String[]{anIcone.getAttribute("type"), anIcone.getAttribute("path")});
                                }
                            }
                        } if (donnees.getNodeType()==Node.ELEMENT_NODE && donnees.getNodeName()=="entree") {
                            Element entree = (Element) donnees;
                            //System.out.println(entree.getAttribute("type"));
                            if(entree.getAttribute("capacite")==""){
                                //System.out.println(entree.getAttribute("quantite"));
                                u.add(new String[]{entree.getAttribute("type"),entree.getAttribute("quantite")});
                            }
                            else{
                                //System.out.println(entree.getAttribute("capacite"));
                                u.add(new String[]{entree.getAttribute("type"),entree.getAttribute("capacite")});
                            }
                        } if (donnees.getNodeType()==Node.ELEMENT_NODE && donnees.getNodeName()=="sortie") {
                            Element sortie = (Element) donnees;
                            //System.out.println(sortie.getAttribute("type"));
                            u.add(new String[]{sortie.getAttribute("type")});
                        } if(donnees.getNodeType()==Node.ELEMENT_NODE && donnees.getNodeName()=="interval-production"){
                            //System.out.println("interval-production " + donnees.getTextContent());
                            u.add(new String[]{donnees.getTextContent()});
                        }
                    }
                }
            }

            for(int i=0; i<donneesUsines.size();i++){
                for(int j=0; j<donneesUsines.get(i).size();j++){
                    for(int k=0;k<donneesUsines.get(i).get(j).length;k++){
                        System.out.println(donneesUsines.get(i).get(j)[k]);
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