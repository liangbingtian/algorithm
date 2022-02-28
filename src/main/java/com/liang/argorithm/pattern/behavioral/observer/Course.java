package com.liang.argorithm.pattern.behavioral.observer;

import java.util.Observable;

/**
 * 课程，被观察对象
 *
 * @author liangbingtian
 * @date 2022/02/25 下午12:41
 */
public class Course extends Observable {

  private String courseName;

  public Course(String courseName) {
    this.courseName = courseName;
  }

  public String getCourseName() {
    return courseName;
  }

  public void produceQuestion(Course course, Question question) {
    System.out.println(question.getUserName()+"在"+course.getCourseName()+"提交了一个问题");
    setChanged();
    notifyObservers(question);
  }
}
