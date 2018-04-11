wsdl地址：http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl
1、打开地址，把wsdl文件下载下来
2、将这两行<s:element ref="s:schema" /><s:any />全部替换为<s:any minOccurs="2" maxOccurs="2"/>。
    一共有三处需要修改，建议查找<s:element ref="s:schema" />，修改时把<s:any />也要删掉。
3、执行命令:
wsimport -s c:\webservice -p com.arki.laboratory.snippet.webservice.weather -keep -verbose c:\Users\k\Desktop\WeatherWS.xml