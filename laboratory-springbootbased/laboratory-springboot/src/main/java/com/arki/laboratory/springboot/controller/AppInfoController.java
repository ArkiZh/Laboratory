package com.arki.laboratory.springboot.controller;

import com.arki.laboratory.springboot.bo.ApplicationInfo;
import com.arki.laboratory.springboot.bo.ApplicationProfile;
import com.arki.laboratory.springboot.util.FileUtil;
import com.arki.laboratory.springboot.util.SpringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class AppInfoController {


    @RequestMapping
    public String readMe() {
        StringBuilder sb = new StringBuilder();
        sb.append("Read me:").append(System.lineSeparator());
        String readMePath = this.getClass().getClassLoader().getResource("README.md").getPath();
        sb.append(FileUtil.readFileAsString(new File(readMePath)));
        return sb.toString();
    }

    @RequestMapping("/appinfo")
    public ApplicationInfo getAppInfo() {
        return SpringUtil.getBean(ApplicationInfo.class);
    }

    @RequestMapping("/profile")
    public ApplicationProfile getAppProfile() {
        return SpringUtil.getBean(ApplicationProfile.class);
    }
}
