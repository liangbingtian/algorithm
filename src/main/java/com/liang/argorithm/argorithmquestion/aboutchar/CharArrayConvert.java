package com.liang.argorithm.argorithmquestion.aboutchar;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串转换相关的问题
 *
 * @author liangbingtian
 * @date 2022/01/21 下午6:26
 */
public class CharArrayConvert {

  /**
   * 字符串转换整数 (atoi)
   */
  public int myAtoi(String str) {
    Automation myAutomation = new Automation();
    for (int i=0;i<str.length();++i) {
      myAutomation.get(str.charAt(i));
    }
    return (int)myAutomation.ans*myAutomation.sign;
  }

  private static class Automation {

    //1表示正数0表示负数
    private int sign = 1;
    //数的总数和
    private long ans = 0;
    private String state = "start";
    private Map<String, String[]> table = new HashMap<String, String[]>() {
      {
        put("start", new String[]{"start", "signed", "int_number", "end"});
        put("signed", new String[]{"end", "end", "int_number", "end"});
        put("int_number", new String[]{"end", "end", "int_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
      }
    };

    //由于溢出问题，所以一定要用long,否则溢出的话就会出现值的错误。
    public void get(char c) {
      state = table.get(state)[get_col(c)];
      if (state.equals("int_number")) {
        ans = ans*10+c-'0';
        ans = (sign==1)?Math.min(ans, (long)Integer.MAX_VALUE) : Math.min(-(long)Integer.MIN_VALUE, ans);
      }else if (state.equals("signed")) {
        sign = (c=='+')?1:-1;
      }
    }

    private int get_col(char ch) {
      if (ch == ' ') {
        return 0;
      } else if (ch == '+' || ch == '-') {
        return 1;
      } else if (Character.isDigit(ch)) {
        return 2;
      } else {
        return 3;
      }
    }
  }

  public static void main(String[] args) {
    new CharArrayConvert().myAtoi(" -42");
  }


}
