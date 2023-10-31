package com.liang.argorithm.aboutproject.cache;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * @author liangbingtian
 * @date 2023/06/16 下午6:26
 */
public class Test1 {

  public static void main(String[] args) {
    String appKey = "288d69c7c94a4149b8f9ca1a2be8dbe4";
    String appSecret = "cd961102fb9b4fe7abc86c7d67353af8";
    String host = "https://tilake.wenge.com/saas-gateway";
    testPost(appKey, appSecret, host);

  }


  /***
   * 请求签名鉴权-POST请求
   *
   * @param
   * @param appKey
   * @param host
   */
  public static void testPost(String appKey, String appSecret, String host) {
    // 个人创建账号接口地址
    String generateApi = "/53c28500b45302ceb4f5bbaab300f4fb/generate";
    // 个人创建账号接口请求地址
    String generateApipath = host + generateApi;

    try {
      List<Map<String, String>> list = new ArrayList();
      Map<String, String> messageMap = new HashMap();
      messageMap.put("role", "user");
      messageMap.put("content", "你是chatgpt吗");
      list.add(messageMap);
      // 构建请求Body体
      JSONObject reqBodyObj = new JSONObject();
      reqBodyObj.put("id", "123");
      reqBodyObj.put("model", "yayi");
      reqBodyObj.put("messages", list);
      reqBodyObj.put("max_new_tokens", 452);
      reqBodyObj.put("do_sample", true);
      reqBodyObj.put("temperature", 0.8);
      reqBodyObj.put("repetition_penalty", 1);

      // 请求Body体数据
      String reqBodyData = reqBodyObj.toString();
      // 对请求Body体内的数据计算ContentMD5
      String contentMD5 = doContentMD5(reqBodyData);

      // 构建待签名字符串
      String method = "POST";
      String accept = "*/*";
      String contentType = "application/json";
      String path = generateApi;
      String date = null;

      StringBuffer sb = new StringBuffer();
      sb.append(method).append("\n").append(accept).append("\n").append(contentType).append("\n").append(date).append("\n").append(path);


      // 构建参与请求签名计算的明文
      String stringToSign = sb.toString();

      Mac hmacSha256 = Mac.getInstance("HmacSHA256");
      byte[] appSecretBytes = appSecret.getBytes(Charset.forName("UTF-8"));
      hmacSha256.init(new SecretKeySpec(appSecretBytes, 0, appSecretBytes.length, "HmacSHA256"));
      byte[] md5Result = hmacSha256.doFinal(stringToSign.getBytes(Charset.forName("UTF-8")));
      String signature = Base64.encodeBase64String(md5Result);

      // 获取时间戳(精确到毫秒)
      long timeStamp = timeStamp();

      // 构建请求头
      LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
      header.put("x-tilake-app-key", appKey);
      header.put("x-tilake-ca-signature-method", "HmacSHA256");
      header.put("x-tilake-ca-timestamp", String.valueOf(timeStamp));
      header.put("Accept", accept);
      header.put("Content-Type", contentType);
      header.put("x-tilake-ca-signature", signature);
      header.put("x-tilake-ca-nonce", IdUtil.fastSimpleUUID());

      // 发送POST请求
      String result = HttpHelper.sendPOST(generateApipath, reqBodyData, header, "UTF-8");
      System.out.println("请求返回信息： " + result);
    } catch (Exception e) {
      e.printStackTrace();
      String msg = MessageFormat.format("请求签名鉴权方式调用接口出现异常: {0}", e.getMessage());
      System.out.println(msg);
    }
  }


  /***
   *
   * @param str 待计算的消息
   * @return MD5计算后摘要值的Base64编码(ContentMD5)
   * @throws Exception 加密过程中的异常信息
   */
  public static String doContentMD5(String str) throws Exception {
    byte[] md5Bytes = null;
    MessageDigest md5 = null;
    String contentMD5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
      // 计算md5函数
      md5.update(str.getBytes("UTF-8"));
      // 获取文件MD5的二进制数组（128位）
      md5Bytes = md5.digest();
      // 把MD5摘要后的二进制数组md5Bytes使用Base64进行编码（而不是对32位的16进制字符串进行编码）
      contentMD5 = new String(Base64.encodeBase64(md5Bytes), "UTF-8");
    } catch (NoSuchAlgorithmException e) {
      String msg = MessageFormat.format("不支持此算法: {0}", e.getMessage());
      Exception ex = new Exception(msg);
      ex.initCause(e);
      throw ex;
    } catch (UnsupportedEncodingException e) {
      String msg = MessageFormat.format("不支持的字符编码: {0}", e.getMessage());
      Exception ex = new Exception(msg);
      ex.initCause(e);
      throw ex;
    }
    return contentMD5;
  }

  /***
   * 计算请求签名值
   *
   * @param message 待计算的消息
   * @param secret 密钥
   * @return HmacSHA256计算后摘要值的Base64编码
   * @throws Exception 加密过程中的异常信息
   */
  public static String doSignatureBase64(String message, String secret) throws Exception {
    String algorithm = "HmacSHA256";
    Mac hmacSha256;
    String digestBase64 = null;
    try {
      hmacSha256 = Mac.getInstance(algorithm);
      byte[] keyBytes = secret.getBytes("UTF-8");
      byte[] messageBytes = message.getBytes("UTF-8");
      hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm));
      // 使用HmacSHA256对二进制数据消息Bytes计算摘要
      byte[] digestBytes = hmacSha256.doFinal(messageBytes);
      // 把摘要后的结果digestBytes转换成十六进制的字符串
      // String digestBase64 = Hex.encodeHexString(digestBytes);
      // 把摘要后的结果digestBytes使用Base64进行编码
      digestBase64 = new String(Base64.encodeBase64(digestBytes), "UTF-8");
    } catch (NoSuchAlgorithmException e) {
      String msg = MessageFormat.format("不支持此算法: {0}", e.getMessage());
      Exception ex = new Exception(msg);
      ex.initCause(e);
      throw ex;
    } catch (UnsupportedEncodingException e) {
      String msg = MessageFormat.format("不支持的字符编码: {0}", e.getMessage());
      Exception ex = new Exception(msg);
      ex.initCause(e);
      throw ex;
    } catch (InvalidKeyException e) {
      String msg = MessageFormat.format("无效的密钥规范: {0}", e.getMessage());
      Exception ex = new Exception(msg);
      ex.initCause(e);
      throw ex;
    }
    return digestBase64;
  }

  /***
   * 获取时间戳(毫秒级)
   *
   * @return 毫秒级时间戳, 如 1578446909000
   */
  public static long timeStamp() {
    long timeStamp = System.currentTimeMillis();
    return timeStamp;
  }

}
