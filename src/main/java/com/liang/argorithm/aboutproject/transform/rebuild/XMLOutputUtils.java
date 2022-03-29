package com.liang.argorithm.aboutproject.transform.rebuild;

import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * XML输出的util
 *
 * @author liangbingtian
 * @date 2022/03/26 下午8:07
 */
public class XMLOutputUtils {


  public static void writeXMLToOutputStream(XMLTreeNode treeNode, OutputStream outputStream) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.newDocument();
      document.setXmlStandalone(true);
      xmlIter(document, treeNode, null);
      TransformerFactory transFactory = TransformerFactory.newInstance();
      Transformer transformer = transFactory.newTransformer();
      transformer.setOutputProperty(
          OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource domSource = new DOMSource(document);
      transformer.transform(domSource, new StreamResult(outputStream));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  private static void xmlIter(Document document, XMLTreeNode treeNode, Element element) {
    Element element1 = document.createElement(treeNode.getKey());
    if (treeNode.getSonNodeList().size() == 0) {
      if (treeNode.getValue() == null) {
        element1.setTextContent("");
      } else {
        element1.setTextContent(treeNode.getValue());
      }
    }
    if (element == null) {
      document.appendChild(element1);
    } else {
      element.appendChild(element1);
    }
    for (XMLTreeNode subXMLTreeNode : treeNode.getSonNodeList()) {
      xmlIter(document, subXMLTreeNode, element1);
    }
  }


}
