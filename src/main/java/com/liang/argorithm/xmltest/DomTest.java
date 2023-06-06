package com.liang.argorithm.xmltest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.BeanUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author liangbingtian
 * @date 2023/04/12 下午4:58
 */
public class DomTest {

  public static Document getDocument(String xmlPath)
      throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
    return documentBuilder.parse(xmlPath);
  }

  public static List<Student> getStudents(String xmlPath)
      throws IOException, SAXException, ParserConfigurationException {
    Document document = getDocument(xmlPath);
    List<Student> students = new ArrayList<>();
    NodeList nodeList = document.getElementsByTagName("student");
    for (int i=0;i<nodeList.getLength();++i) {
      Element stuElement = (Element) nodeList.item(i);
      Student student = new Student();
      student.setName(stuElement.getElementsByTagName("name").item(0).getTextContent());
      student.setAge(Integer.parseInt(stuElement.getElementsByTagName("age").item(0).getTextContent()));
      students.add(student);
    }
    return students;
  }


  public static void main(String[] args)
      throws ParserConfigurationException, SAXException, IOException {
    Student student = BeanUtils.instantiateClass(Student.class);
    getStudents("/Users/liangbingtian/Desktop/algorithm/src/main/resources/test/student.xml");
  }

}
