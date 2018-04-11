
package com.arki.laboratory.snippet.webservice.phonenumberlocation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MobileCodeWSSoap", targetNamespace = "http://WebXml.com.cn/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MobileCodeWSSoap {


    /**
     * <br /><h3>��ù����ֻ����������ʡ�ݡ��������ֻ���������Ϣ</h3><p>���������mobileCode = �ַ������ֻ����룬����ǰ7λ���֣���userID = �ַ�������ҵ�û�ID�� ����û�Ϊ���ַ������������ݣ��ַ������ֻ����룺ʡ�� ���� �ֻ������ͣ���</p><br />
     * 
     * @param userID
     * @param mobileCode
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://WebXml.com.cn/getMobileCodeInfo")
    @WebResult(name = "getMobileCodeInfoResult", targetNamespace = "http://WebXml.com.cn/")
    @RequestWrapper(localName = "getMobileCodeInfo", targetNamespace = "http://WebXml.com.cn/", className = "com.arki.laboratory.snippet.webservice.phonenumberlocation.GetMobileCodeInfo")
    @ResponseWrapper(localName = "getMobileCodeInfoResponse", targetNamespace = "http://WebXml.com.cn/", className = "com.arki.laboratory.snippet.webservice.phonenumberlocation.GetMobileCodeInfoResponse")
    public String getMobileCodeInfo(
            @WebParam(name = "mobileCode", targetNamespace = "http://WebXml.com.cn/")
                    String mobileCode,
            @WebParam(name = "userID", targetNamespace = "http://WebXml.com.cn/")
                    String userID);

    /**
     * <br /><h3>��ù����ֻ�������������ݿ���Ϣ</h3><p>����������ޣ��������ݣ�һά�ַ������飨ʡ�� ���� ��¼��������</p><br />
     * 
     * @return
     *     returns com.arki.laboratory.snippet.webservice.phonenumberlocation.ArrayOfString
     */
    @WebMethod(action = "http://WebXml.com.cn/getDatabaseInfo")
    @WebResult(name = "getDatabaseInfoResult", targetNamespace = "http://WebXml.com.cn/")
    @RequestWrapper(localName = "getDatabaseInfo", targetNamespace = "http://WebXml.com.cn/", className = "com.arki.laboratory.snippet.webservice.phonenumberlocation.GetDatabaseInfo")
    @ResponseWrapper(localName = "getDatabaseInfoResponse", targetNamespace = "http://WebXml.com.cn/", className = "com.arki.laboratory.snippet.webservice.phonenumberlocation.GetDatabaseInfoResponse")
    public ArrayOfString getDatabaseInfo();

}
