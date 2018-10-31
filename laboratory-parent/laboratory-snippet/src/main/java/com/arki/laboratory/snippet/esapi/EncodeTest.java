package com.arki.laboratory.snippet.esapi;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.codecs.OracleCodec;

public class EncodeTest {
    public static void main(String[] args) {
        Encoder encoder = ESAPI.encoder();
        //Encode sql
        System.out.println("=============== Encode sql:");
        String conSql = "select * from ( select  A,B from T t where t.user_name='hello' and (select SYS.DATAbase_NAME from dual) = 'SWDB'  order by PARA_CD asc ) where rownum <= 100;";
        System.out.println(conSql);
        String s = encoder.encodeForSQL(new OracleCodec(), conSql);
        System.out.println(s);

        //Encode JS
        System.out.println("=============== Encode js:");
        String js = "&=<script>alert()</script>";
        System.out.println(js);
        String s1 = encoder.encodeForJavaScript(js);
        System.out.println(s1);

        //Encode HTML
        System.out.println("=============== Encode html:");
        String html = "&=<script>alert()</script>";
        System.out.println(html);
        String s2 = encoder.encodeForHTML(html);
        System.out.println(s2);
    }
}
