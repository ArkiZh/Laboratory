package com.arki.laboratory.snippet.pmml;

import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;
import org.jpmml.model.PMMLUtil;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BuildModelFromPMML{
    public static void main(String[] args) throws JAXBException, SAXException {
        InputStream inputStream = BuildModelFromPMML.class.getClassLoader().getResourceAsStream("model.pmml");
        PMML pmml = PMMLUtil.unmarshal(inputStream);

        ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
        ModelEvaluator<?> modelEvaluator = modelEvaluatorFactory.newModelEvaluator(pmml);
        Evaluator evaluator = modelEvaluator;

        Map<String,Object> paramMap =new HashMap<>();

        paramMap.put("para1", "1");
        paramMap.put("para2", 6);
        paramMap.put("para3", "2");

        //构建模型输入数据
        List<InputField> inputFields = evaluator.getInputFields();
        Map<FieldName, FieldValue> arguments = new LinkedHashMap<>();
        for (InputField inputField : inputFields) {
            FieldName inputFieldName = inputField.getName();
            Object rawValue = paramMap.get(inputFieldName.getValue());
            FieldValue inputFieldValue = inputField.prepare(rawValue);
            arguments.put(inputFieldName, inputFieldValue);
        }

        Map<FieldName, ?> results = evaluator.evaluate(arguments);
        List<TargetField> targetFields = evaluator.getTargetFields();
        //对于分类问题有多个输出
        for (TargetField targetField : targetFields) {
            FieldName targetFieldName = targetField.getName();
            Object targetFieldValue = results.get(targetFieldName);
            System.out.println("Target: "+targetFieldName.getValue()+" Value: "+targetFieldValue);
        }
    }
}