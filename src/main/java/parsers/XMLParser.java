package parsers;

import classes.Column;
import classes.Page;
import classes.Settings;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XMLParser {


    public Settings loadSettingsFromFile(String path) throws ParserConfigurationException, IOException, SAXException {

        Page page = new Page();
        List<Column> columnList = new ArrayList<>();
        File file = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        NodeList nodeList = document.getChildNodes();
        NodeList settingsNode = nodeList.item(0).getChildNodes();
        for (int i = 0; i < settingsNode.getLength(); i++) {
            NodeList listChild = settingsNode.item(i).getChildNodes();
            for (int j = 0; j < listChild.getLength(); j++) {
                switch (listChild.item(j).getNodeName()) {
                    case "width":
                        page.setWidth(Integer.parseInt(listChild.item(j).getTextContent()));
                        break;
                    case "height":
                        page.setHeight(Integer.parseInt(listChild.item(j).getTextContent()));
                        break;
                    case "column":
                        Column column = new Column();
                        for (int l = 0; l < listChild.item(j).getChildNodes().getLength(); l++) {
                            switch (listChild.item(j).getChildNodes().item(l).getNodeName()) {
                                case "title":
                                    column.setTitle(listChild.item(j).getChildNodes().item(l).getTextContent());
                                    break;
                                case "width":
                                    column.setWidth(Integer.parseInt(listChild.item(j).getChildNodes().item(l).getTextContent()));
                                default:
                                    continue;
                            }
                        }
                        column.setNumberOfColumn(columnList.size() + 1);
                        columnList.add(column);
                }
            }
        }
        Settings set = new Settings(page,columnList);
        return set;

    }
}
