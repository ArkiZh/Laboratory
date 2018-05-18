package com.arki.laboratory.snippet.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 将实体序列化为xml字符串
 */
public class XMLFormatter {
    public static void main(String[] args) throws JAXBException {
        formatByJAXB();
    }


    /**
     * 使用jdk的javax包下的JAXB(Java Architecture for XML Binding)将实体序列化为xml
     *
     */
    public static void formatByJAXB() throws JAXBException {
        //封装数据 school套classroom套student
        School school = new School();
        //学校下面两个班
        Classroom classroom1 = new Classroom();
        Classroom classroom2 = new Classroom();
        List<Classroom> classrooms = new ArrayList<>();
        classrooms.add(classroom1);
        classrooms.add(classroom2);
        school.setClassrooms(classrooms);
        //每个班下面两个学生
        Student student1_1 = new Student();
        Student student1_2 = new Student();
        Student student2_1 = new Student();
        Student student2_2 = new Student();
        List<Student> students1 = new ArrayList<>();
        List<Student> students2 = new ArrayList<>();
        students1.add(student1_1);
        students1.add(student1_2);
        students2.add(student2_1);
        students2.add(student2_2);
        classroom1.setStudents(students1);
        classroom2.setStudents(students2);
        //学校详细信息
        school.setAddress("北京");
        school.setClassRoomCounts(100);
        school.setSchoolId(2);
        school.setSchoolName("北京二中");
        //班级详细信息
        classroom1.setGrade("一年级");
        classroom2.setGrade("二年级");
        //学生详细信息
        student1_1.setName("张一");
        student1_1.setGender("男");
        student1_1.setAddress("第一胡同");
        student1_2.setName("王二");
        student1_2.setGender("女");
        student1_2.setAddress("第二胡同");

        //开始转换xml
        JAXBContext jaxbContext = JAXBContext.newInstance(School.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        //设置序列号格式
        //编码格式
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        //格式化输出的xml
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //在root标签中添加xsi:noNamespaceSchemaLocation属性：<school xsi:noNamespaceSchemaLocation="abc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
        //marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "abc");
        //在root标签中添加xsi:schemaLocation属性：<school xsi:schemaLocation="def" xsi:noNamespaceSchemaLocation="abc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
        //marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "def");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);//去掉xml文件头：<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
        //添加监听器，使为null的属性值设为空值，字符串类型的改为"",对象类型的改为newInstance(),以解决为null时不生成标签的问题
        marshaller.setListener(new DealNullValueListener());
        StringWriter writer = new StringWriter();
        marshaller.marshal(school, writer);
        System.out.println(writer.toString());

    }


    @XmlRootElement(name = "SCHOOL")
    //指定由java对象生成xml文件时对java对象属性的访问方式。
    @XmlAccessorType(XmlAccessType.PROPERTY)
    //设置xml节点生成顺序，按成员变量名称（不是重命名后的节点名称）排序，必须包含所有成员变量。
    @XmlType(propOrder = {"schoolId","schoolName","address"/*,"classRoomCounts"*/,"classrooms"})
    private static class School {
        //@XmlElement(name = "SCHOOL_ID")
        // 重命名节点名称时，注解放到get方法上，放到成员变量上会报错：
        // Class has two properties of the same name "schoolId"。
        // XMLParser$School.getSchoolId()与XMLParser$School.schoolId冲突
        private int schoolId;
        private String schoolName;
        private String address;
        private int classRoomCounts;
        private List<Classroom> classrooms;
        @XmlElement(name = "SCHOOL_ID")
        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @XmlTransient   //忽略classRoomCounts
        public int getClassRoomCounts() {
            return classRoomCounts;
        }

        public void setClassRoomCounts(int classRoomCounts) {
            this.classRoomCounts = classRoomCounts;
        }
        @XmlElement(name = "CLASSROOM")
        @XmlElementWrapper(name="CLASSROOMS") //不加wrapper时，CLASSROOM直接在SCHOOL节点下,即SCHOOL->多个CLASSROOM。加上后，SCHOOL->CLASSROOMS->多个CLASSROOM.
        //list中元素按照list中原有顺序排序
        public List<Classroom> getClassrooms() {
            return classrooms;
        }

        public void setClassrooms(List<Classroom> classrooms) {
            this.classrooms = classrooms;
        }
    }
    @XmlType(propOrder = {"grade","classRoomId","studentCounts","students"})
    private static class Classroom {
        private int classRoomId;
        private String grade;
        private int studentCounts;
        private List<Student> students;

        public int getClassRoomId() {
            return classRoomId;
        }

        public void setClassRoomId(int classRoomId) {
            this.classRoomId = classRoomId;
        }

        @XmlElement
        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getStudentCounts() {
            return studentCounts;
        }

        public void setStudentCounts(int studentCounts) {
            this.studentCounts = studentCounts;
        }

        @XmlElement(name="STUDENT")
        @XmlElementWrapper(name="STUDENTS")
        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }
    }

    @XmlType(propOrder = {"name","gender","address"})
    private static class Student {
        private String name;
        private String gender;
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

    }

    private static class DealNullValueListener extends Marshaller.Listener {
        /**
         * <p/>
         * Callback method invoked before marshalling from <tt>source</tt> to XML.
         * <p/>
         * <p/>
         * This method is invoked just before marshalling process starts to marshal <tt>source</tt>.
         * Note that if the class of <tt>source</tt> defines its own <tt>beforeMarshal</tt> method,
         * the class specific callback method is invoked just before this method is invoked.
         *
         * @param source instance of JAXB mapped class prior to marshalling from it.
         */
        @Override
        public void beforeMarshal(Object source) {
            super.beforeMarshal(source);
            Class<?> clazz = source.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                Field declaredField = declaredFields[i];
                declaredField.setAccessible(true);
                try {
                    if (declaredField.get(source) == null) {
                        Class<?> type = declaredField.getType();
                        Object o = type.newInstance();
                        declaredField.set(source, o);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * <p/>
         * Callback method invoked after marshalling <tt>source</tt> to XML.
         * <p/>
         * <p/>
         * This method is invoked after <tt>source</tt> and all its descendants have been marshalled.
         * Note that if the class of <tt>source</tt> defines its own <tt>afterMarshal</tt> method,
         * the class specific callback method is invoked just before this method is invoked.
         *
         * @param source instance of JAXB mapped class after marshalling it.
         */
        @Override
        public void afterMarshal(Object source) {
            super.afterMarshal(source);
        }
    }

}
