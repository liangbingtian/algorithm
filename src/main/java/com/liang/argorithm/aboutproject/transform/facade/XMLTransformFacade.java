package com.liang.argorithm.aboutproject.transform.facade;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liang.argorithm.aboutproject.transform.json.Sax2JSONWithAttrHandler;
import com.liang.argorithm.aboutproject.transform.json.Sax2JSONWithoutAttrHandler;
import com.liang.argorithm.aboutproject.transform.rebuild.AbstractRebuildStrategy;
import com.liang.argorithm.aboutproject.transform.rebuild.XMLReBuilder;
import com.liang.argorithm.aboutproject.transform.rebuild.rowtocol.XMLRowToColRebuildStrategy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * XML转换相关
 *
 * @author liangbingtian
 * @date 2022/03/26 下午6:59
 */
public class XMLTransformFacade {

  private static final String CHARSET = "UTF-8";

  private static final String ELEM = "elem";


  public static String getColToRowXmlStr(InputStream xmlInputStream, String colToRowPath) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    writeColToRowStr(xmlInputStream, byteArrayOutputStream, colToRowPath);
    try {
      return byteArrayOutputStream.toString(CHARSET);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }

  public static void writeColToRowStr(InputStream xmlInputStream, OutputStream xmlOutputStream,
      String colToRowPath) {
    writeColToRowStrWithSpecificMap(xmlInputStream, xmlOutputStream, colToRowPath, null);
  }


    public static String getXmlStrWithTransformStrategy(InputStream xmlInputStream, List<AbstractRebuildStrategy> rebuildStrategyList) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    writeXMLWithTransformStrategy(xmlInputStream, byteArrayOutputStream, rebuildStrategyList);
    try {
      return byteArrayOutputStream.toString(CHARSET);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 如果map的key为xml标签，则value插入到该标签下
   */
  public static void writeColToRowStrWithSpecificMap(InputStream xmlInputStream,
      OutputStream xmlOutputStream,
      String colToRowPath, Map<String, Map<String, String>> specificMap) {
    AbstractRebuildStrategy strategy = null;
    if (specificMap != null) {

    } else {
      strategy = new XMLRowToColRebuildStrategy(colToRowPath);
    }
    writeXMLWithTransformStrategy(xmlInputStream, xmlOutputStream, Collections.singletonList(strategy));
  }

  public static void writeXMLWithTransformStrategy(InputStream xmlInputStream,
      OutputStream xmlOutputStream, List<AbstractRebuildStrategy> rebuildStrategyList) {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    try {
      factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
      factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
      SAXParser parser = factory.newSAXParser();
      XMLReBuilder reBuilder = new XMLReBuilder();
      reBuilder.addAllRebuildStrategyList(rebuildStrategyList);
      parser.parse(xmlInputStream, reBuilder);
      reBuilder.writeToOutputStream(xmlOutputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * xml字符串转JSONObject（不包含xml标签内属性）
   *
   * @param xmlStr xml字符串
   * @return JSONObject
   */
  public static JSONObject getJSONObjectFromXMLWithoutAttr(String xmlStr) {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
        xmlStr.getBytes(Charset.forName(CHARSET)));
    return getJSONObjectFromXMLWithoutAttr(byteArrayInputStream);
  }

  /**
   * xml输入流转JSONObject（不包含xml标签内属性）
   *
   * @param xmlInputStream xml输入流
   * @return JSONObject
   */
  public static JSONObject getJSONObjectFromXMLWithoutAttr(InputStream xmlInputStream) {
    return getJSONObjectFromXMLWithoutAttr(xmlInputStream, CHARSET);
  }

  /**
   * xml输入流转JSONObject（不包含xml标签内属性）
   *
   * @param xmlInputStream xml输入流
   * @return JSONObject
   */
  public static JSONObject getJSONObjectFromXMLWithoutAttr(InputStream xmlInputStream, String charset) {
    return getJSONObjectFromXMLWithoutAttr(xmlInputStream, charset, null);
  }

  /**
   * xml字符串根据指定的json数组路径，转换JSONObject（不包含xml标签内属性）
   *
   * @param xmlStr xml字符串
   * @param arraySet json数组路径。xml中符合该路径的元素，都会转化为json数组
   * @return JSONObject
   */
  public static JSONObject getJSONObjectFromXMLWithoutAttr(String xmlStr, Set<String> arraySet) {
    return getJSONObjectFromXMLWithoutAttr(xmlStr, CHARSET, arraySet);
  }

  /**
   * xml字符串根据指定的json数组路径，转换JSONObject（不包含xml标签内属性）
   *
   * @param xmlStr xml字符串
   * @param arraySet json数组路径。xml中符合该路径的元素，都会转化为json数组
   * @return JSONObject
   */
  public static JSONObject getJSONObjectFromXMLWithoutAttr(String xmlStr, String charset, Set<String> arraySet) {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
        xmlStr.getBytes(Charset.forName(charset)));
    return getJSONObjectFromXMLWithoutAttr(byteArrayInputStream, charset, arraySet);
  }

  /**
   * xml输入流根据指定的json数组路径，转换JSONObject（不包含xml标签内属性）
   *
   * @param xmlInputStream xml输入流
   * @param arraySet json数组路径。xml中符合该路径的元素，都会转化为json数组
   * @return JSONObject
   */
  public static JSONObject getJSONObjectFromXMLWithoutAttr(InputStream xmlInputStream,
      Set<String> arraySet) {
    return getJSONObjectFromXMLWithoutAttr(xmlInputStream, CHARSET, arraySet);
  }

  /**
   * xml输入流根据指定的json数组路径，转换JSONObject（不包含xml标签内属性）
   *
   * @param xmlInputStream xml输入流
   * @param arraySet json数组路径。xml中符合该路径的元素，都会转化为json数组
   * @return JSONObject
   */
  public static JSONObject getJSONObjectFromXMLWithoutAttr(InputStream xmlInputStream, String charset,
      Set<String> arraySet) {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    try {
      //2020-03-23 12:32:40 add by gaotx 解决代码扫描发现的XXE问题
      factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
      factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
      SAXParser parser = factory.newSAXParser();
      Sax2JSONWithoutAttrHandler handler = new Sax2JSONWithoutAttrHandler();
      if (arraySet != null) {
        handler.setArrayPathSet(arraySet);
      }
      InputSource inputSource = new InputSource(xmlInputStream);
      inputSource.setEncoding(charset);
      parser.parse(inputSource, handler);
      return handler.getRoot();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new JSONObject();
  }



  /**
   * 将json对象转换为xml字符串。兼容包含标签/不包含标签的xml转换后json格式
   * @param jsonObject
   * @return
   */
  public static String getXMLStrFromJSONObject(JSONObject jsonObject) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    writeXMLToOutputStreamFromJSONObject(jsonObject, byteArrayOutputStream);
    try {
      return byteArrayOutputStream.toString(CHARSET);
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 将json对象转换为xml字符串。兼容包含标签/不包含标签的xml转换后json格式
   * @param jsonObject
   * @return
   */
  public static String getXMLStrFromJSONObject(JSONObject jsonObject, String charset) {
    byte[] bytes = getXmlByteArrayFromJSONObject(jsonObject, charset);
    try {
      return new String(bytes, charset);
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 将jsonObject按照字符集生成特定编码的xml
   * @param jsonObject
   * @param charset
   * @return
   */
  public static byte[] getXmlByteArrayFromJSONObject(JSONObject jsonObject, String charset) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    writeXMLToOutputStreamFromJSONObject(jsonObject, byteArrayOutputStream, charset);
    return byteArrayOutputStream.toByteArray();
  }

  public static void writeXMLToOutputStreamFromJSONObject(JSONObject jsonObject, OutputStream outputStream) {
    writeXMLToOutputStreamFromJSONObject(jsonObject, outputStream, CHARSET);
  }

  public static void writeXMLToOutputStreamFromJSONObject(JSONObject jsonObject, OutputStream outputStream, String charset) {
    SAXTransformerFactory stf=(SAXTransformerFactory) SAXTransformerFactory.newInstance();
    try {
      TransformerHandler handler = stf.newTransformerHandler();
      Transformer transformer = handler.getTransformer();
      transformer.setOutputProperty(
          OutputKeys.INDENT, "yes");
      transformer.setOutputProperty(OutputKeys.ENCODING, charset);
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
      StreamResult result = new StreamResult(outputStream);
      handler.setResult(result);
      handler.startDocument();
      handler.characters("\n".toCharArray(), 0, 1);
      writeJSONObject(jsonObject, handler, 0);
      handler.endDocument();
    }
    catch (TransformerConfigurationException e) {
      e.printStackTrace();
    }
    catch (SAXException e) {
      e.printStackTrace();
    }
  }

  private static void writeJSONObject(JSONObject jsonObject, TransformerHandler handler, int indent) throws SAXException {
    Iterator<Entry<String, Object>> entryIterator = jsonObject.entrySet().iterator();
    while (entryIterator.hasNext()) {
      Map.Entry<String, Object> entry = entryIterator.next();
      if(entry.getValue() instanceof JSONObject) {
        JSONObject subJsonObject = (JSONObject) entry.getValue();
        if(subJsonObject.containsKey(Sax2JSONWithAttrHandler.ATTR)) {
          AttributesImpl attributes = new AttributesImpl();
          JSONObject attrs = subJsonObject.getJSONObject(
              Sax2JSONWithAttrHandler.ATTR);
          subJsonObject.remove(Sax2JSONWithAttrHandler.ATTR);
          Iterator<Map.Entry<String, Object>> iterator = attrs.entrySet().iterator();
          while (iterator.hasNext()) {
            Map.Entry<String, Object> entry1 = iterator.next();
            attributes.addAttribute("","",entry1.getKey(),"",entry1.getValue().toString());
          }
          handler.startElement("","",entry.getKey(), attributes);
        }
        else {
          handler.startElement("","",entry.getKey(), null);
        }
        if(subJsonObject.containsKey(Sax2JSONWithAttrHandler.VALUE)) {
          String value = subJsonObject.getString(Sax2JSONWithAttrHandler.VALUE);
          subJsonObject.remove(Sax2JSONWithAttrHandler.VALUE);
          if(value != null) {
            handler.characters(value.toCharArray(), 0, value.length());
          }
        }
        boolean arrContains = subJsonObject.containsKey(Sax2JSONWithAttrHandler.ARR);
        JSONArray jsonArray = null;
        if(arrContains) {
          jsonArray = subJsonObject.getJSONArray(
              Sax2JSONWithAttrHandler.ARR);
          subJsonObject.remove(Sax2JSONWithAttrHandler.ARR);
        }
        if(!subJsonObject.isEmpty()) {
          writeJSONObject(subJsonObject, handler, indent + 1);
        }
        if(arrContains) {
          Iterator<Object> iterator = jsonArray.iterator();
          while (iterator.hasNext()) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            writeJSONObject(jsonObject1, handler, indent + 1);
          }
        }
      }
      else if(entry.getValue() instanceof JSONArray) {
        if(!Sax2JSONWithAttrHandler.ARR.equals(entry.getKey())) {
          handler.startElement("","",entry.getKey(), null);
          Iterator<Object> iterator = ((JSONArray) entry.getValue()).iterator();
          while (iterator.hasNext()) {
            Object object =  iterator.next();
            if(object instanceof JSONObject) {
              writeJSONObject((JSONObject) object, handler, indent + 1);
            }
            else {
              handler.startElement("","",ELEM, null);
              handler.characters(object.toString().toCharArray(), 0, object.toString().length());
              handler.endElement("","", ELEM);
            }
          }
        }
        else {
          Iterator<Object> iterator = ((JSONArray) entry.getValue()).iterator();
          while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object instanceof JSONObject) {
              writeJSONObject((JSONObject) object, handler, indent + 1);
            }
          }
        }
      }
      else {
        handler.startElement("", "", entry.getKey(), null);
        if (entry.getValue() != null) {
          handler.characters(entry.getValue().toString().toCharArray(), 0,
              entry.getValue().toString().length());
        }
      }
      handler.endElement("","",entry.getKey());
    }
  }

}
