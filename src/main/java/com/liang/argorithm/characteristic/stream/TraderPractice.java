package com.liang.argorithm.characteristic.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liangbingtian
 * @date 2023/06/06 上午11:28
 */
public class TraderPractice {

  public static void main(String[] args) {
    final List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());
    final Stream<Transaction> sorted = transactions.stream().filter(i -> i.getYear() == 2011)
        .sorted(Comparator.comparing(Transaction::getValue));
    final List<Trader> collect = transactions.stream().map(Transaction::getTrader).distinct()
        .collect(Collectors.toList());
    final List<Trader> abc = transactions.stream().map(Transaction::getTrader)
        .filter(a -> a.getCity().equals("abc")).distinct().sorted(Comparator.comparing(Trader::getName))
        .collect(Collectors.toList());
    //返回一个字符串，多个字符串合并成一个
    final String reduce = transactions.stream().map(a -> a.getTrader().getName()).sorted()
        .reduce("", (name1, name2) -> name1 + name2);
    final boolean milan = transactions.stream()
        .allMatch(a -> a.getTrader().getCity().equals("milan"));
    final boolean milan1 = transactions.stream().map(Transaction::getTrader)
        .anyMatch(a -> a.getCity().equals("milan"));
    //transaction的最高value
    final Optional<Integer> reduce1 = transactions.stream().map(Transaction::getValue)
        .reduce(Integer::max);
  }

}
