package com.liang.argorithm.pattern.behavioral.observer;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liangbingtian
 * @date 2022/02/25 下午1:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.liang.argorithm.ArgorithmApplication.class)
public class ObserverTest {

  @Test
  public void testObserver() {
    Course course = new Course("设计模式");
    Teacher teacher = new Teacher("梁");

    course.addObserver(teacher);

    Question question = new Question();
    question.setUserName("炳田");
    question.setQuestionContent("如何编写");
    course.produceQuestion(course, question);
  }
}