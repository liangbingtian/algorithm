package com.liang.argorithm.argorithmquestion.aboutchar;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串的常规操作
 *
 * @author liangbingtian
 * @date 2022/01/19 下午7:42
 */
public class CharArrayCommon {

  /**
   * Z型变换
   * 一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
   *
   * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
   *
   * P   A   H   N
   * A P L S I I G
   * Y   I   R
   * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
   *
   * 来源：力扣（LeetCode）
   * 链接：https://leetcode-cn.com/problems/zigzag-conversion
   * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public String convert(String s, int numRows) {
    //将字符串s还原成z型
    if (numRows==1) {
      return s;
    }
    List<StringBuilder> zStr = new ArrayList<>();
    for (int i=1;i<=Math.min(s.length(), numRows);++i) {
      zStr.add(new StringBuilder());
    }
    int currRow = 0;
    boolean goDown = false;
    for (char ch : s.toCharArray()) {
      zStr.get(currRow).append(ch);
      if (currRow==0||currRow==numRows-1) {
        goDown = !goDown;
      }
      currRow+= goDown?1:-1;
    }
    StringBuilder result = new StringBuilder();
    for (StringBuilder sb :zStr) {
      result.append(sb);
    }
    return result.toString();
  }

}
