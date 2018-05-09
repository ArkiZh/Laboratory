package com.arki.laboratory.snippet.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

/**
 * 将xml字符串转为实体
 */
public class XMLParser {

    private static String xmlExample = "<document><Status>False</Status><Remark>HMAC-SHA256</Remark></document>";

    public static void main(String[] args) throws Exception {
        parseByJdk(xmlExample);
        parseByDom4j(xmlExample);
        parseByJDom(xmlExample);
        parseByXStream(xmlExample);
    }

    public static void parseByXStream(String xmlStr) {
        XStream xStream = new XStream();

        //Initialize security framework of XStream.
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypesByWildcard(new String[]{"com.arki.laboratory.snippet.xml.**"});

        xStream.processAnnotations(XMLEntityExample.class);
        XMLEntityExample o = (XMLEntityExample) xStream.fromXML(xmlStr);
        System.out.println(o.toString());
    }

    public static void parseByJDom(String xmlStr) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        org.jdom.Document document = builder.build(new InputSource(new StringReader(xmlExample)));
        org.jdom.Element element = document.getRootElement();
        String status = element.getChild("Status").getText();
        System.out.println(status);
    }

    /**
     * Using dom4j.
     * @param xmlStr
     * @throws DocumentException
     */
    public static void parseByDom4j(String xmlStr) throws DocumentException {
        org.dom4j.Document document = DocumentHelper.parseText(xmlStr);
        Element element = document.getRootElement();
        String status = element.element("Status").getText();
        System.out.println(status);
    }

    /**
     * Using jdk.
     * @param xmlStr
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void parseByJdk(String xmlStr) throws ParserConfigurationException, IOException, SAXException {
        StringReader sr = new StringReader(xmlStr);
        InputSource is = new InputSource(sr);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(is);
        NodeList statusList = document.getElementsByTagName("Status");
        Node item = statusList.item(0);
        System.out.println(item.getTextContent());
    }

    @XStreamAlias("document")
    private class XMLEntityExample {
        private String Status;
        private String Remark;

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }

        @Override
        public String toString() {
            return "XMLEntityExample{" +
                    "Status='" + Status + '\'' +
                    ", Remark='" + Remark + '\'' +
                    '}';
        }
    }


}
