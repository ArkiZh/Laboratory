package com.arki.laboratory.snippet.webservice;

import com.arki.laboratory.snippet.webservice.phonenumberlocation.ArrayOfString;
import com.arki.laboratory.snippet.webservice.phonenumberlocation.MobileCodeWS;
import com.arki.laboratory.snippet.webservice.phonenumberlocation.MobileCodeWSSoap;

public class PhoneNumberLocationTest {
    public static void main(String[] args) {
        MobileCodeWS mobileCodeWS = new MobileCodeWS();
        MobileCodeWSSoap mobileCodeWSSoap = mobileCodeWS.getMobileCodeWSSoap();
        ArrayOfString databaseInfo = mobileCodeWSSoap.getDatabaseInfo();
        System.out.println(databaseInfo.getString());
        String mobileCodeInfo = mobileCodeWSSoap.getMobileCodeInfo("13321158888", null);
        System.out.println(mobileCodeInfo);
    }
}
