package com.arki.laboratory.snippet.encode;

import org.apache.commons.text.StringEscapeUtils;

public class HtmlEntityDecode {
    public static void main(String[] args) {
        //<Remark>错误！参数不足！</Remark>
        String htmlEntity = "&lt;Remark&gt;&amp;#38169;&amp;#35823;&amp;#65281;&amp;#21442;&amp;#25968;&amp;#19981;&amp;#36275;&amp;#65281;&lt;/Remark&gt;";

        //Deprecated as of 3.6, use commons-text StringEscapeUtils instead
        String s = org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4(htmlEntity);
        System.out.println(s);

        s = org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4(s);
        System.out.println(s);

        //Need two steps:
        //First, decode the basic notation, since the words have been encoded twice.
        String s1 = StringEscapeUtils.unescapeHtml4(htmlEntity);
        System.out.println(s1); //<Remark>&#38169;&#35823;&#65281;&#21442;&#25968;&#19981;&#36275;&#65281;</Remark>
        //Second, decode the words.
        s1 = StringEscapeUtils.unescapeHtml4(s1);
        System.out.println(s1); //<Remark>错误！参数不足！</Remark>

        //Try encoding back to html entity.
        //Fist, encode the tags.
        String s2 = StringEscapeUtils.escapeHtml4(s1);
        System.out.println(s2); //&lt;Remark&gt;错误！参数不足！&lt;/Remark&gt;
        //Second, encode the words.
        s2 = StringEscapeUtils.escapeHtml4(s2);
        System.out.println(s2); //&amp;lt;Remark&amp;gt;错误！参数不足！&amp;lt;/Remark&amp;gt;
        //Failed to encode the words.

    }
}
