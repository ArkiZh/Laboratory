package com.arki.laboratory.snippet.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtil {
    /**
     * 使用Dom4j获取xml元素中的键值对
     * @param element
     * @return
     */
    public static Map<String, String> getValueMapFromDom4jElement(Element element) {
        Map<String, String> valueMap = new HashMap<>();
        List<Element> elementList = element.elements();
        if (elementList.size() == 0) {
            valueMap.put(element.getName(), element.getText());
            return valueMap;
        }
        for (Element e : elementList) {
            Map<String, String> tempValueMap = getValueMapFromDom4jElement(e);
            valueMap.putAll(tempValueMap);
        }
        return valueMap;
    }

    /**
     * 将Html中的特殊字符转回来
     * @param htmlStr
     * @return
     */
    public static String convertHtmlSpecialCharacter(String htmlStr) {
        return htmlStr.replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">")
                .replaceAll("&amp;", "&")
                .replaceAll("&nbsp;", " ");
    }

    public static void main(String[] args) throws DocumentException {
        String xmlExample = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<soap11env:Envelope xmlns:soap11env=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tns=\"http://tempuri.org/\"><soap11env:Body><tns:SendResponse><tns:SendResult>&lt;Document&gt;&lt;Status&gt;False&lt;/Status&gt;&lt;Remark&gt;AgencyCode&amp;#38169;&amp;#35823;&amp;#65281;&amp;#38750;&amp;#27861;&amp;#30340;&amp;#31532;&amp;#19977;&amp;#26041;&amp;#26426;&amp;#26500;&amp;#65281;&lt;/Remark&gt;&lt;DocId/&gt;&lt;/Document&gt;</tns:SendResult></tns:SendResponse></soap11env:Body></soap11env:Envelope>";
        xmlExample = convertHtmlSpecialCharacter(xmlExample);
        Document document = DocumentHelper.parseText(xmlExample);
        List<Element> elementList = document.selectNodes("//Document");
        System.out.println(getValueMapFromDom4jElement(elementList.get(0)));

    }
}
