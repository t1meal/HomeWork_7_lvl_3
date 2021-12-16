package gb;

import gb.Annotations.AfterSuite;
import gb.Annotations.BeforeSuite;
import gb.Annotations.Test;

import java.lang.reflect.Method;

public class App {

    // 1. Создать класс, который может выполнять «тесты», в качестве тестов выступают классы с наборами методов с аннотациями @Test.
    // Для этого у него должен быть статический метод start(), которому в качестве параметра передается или объект типа Class, или имя класса.
    // Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется, далее запущены методы с аннотациями @Test,
    // а по завершению всех тестов – метод с аннотацией @AfterSuite.
    // К каждому тесту необходимо также добавить приоритеты (int числа от 1 до 10),
    // в соответствии с которыми будет выбираться порядок их выполнения, если приоритет одинаковый, то порядок не имеет значения.
    // Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре,
    // иначе необходимо бросить RuntimeException при запуске «тестирования».

    public static void main(String[] args) {

        start("gb.TestingClass");
        start(TestingClass.class);
    }

    private static void start(String className) {
        try {
            Class class1 = Class.forName(className);
            start(class1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void start(Class classObject) {
        Method[] methods = classObject.getDeclaredMethods();
        try {
            int count1 = 0;
            for (Method o : methods) {

                if (o.getAnnotation(BeforeSuite.class) != null) {
                    if (count1 == 1) throw new RuntimeException();
                    System.out.println(o);
                    count1++;
                }
            }
            for (int i = 1; i < 11; i++) {
                for (Method o : methods) {
                    if (o.getAnnotation(Test.class) != null) {
                        Test test =
                                o.getAnnotation(Test.class);
                        if (test.priority() == i) {
                            System.out.println(o);
                            System.out.println("value: " + test.priority());
                        }
                    }
                }
            }
            int count2 = 0;
            for (Method o : methods) {
                if (o.getAnnotation(AfterSuite.class) != null) {
                    if (count2 == 1) throw new RuntimeException();
                    System.out.println(o);
                    count2++;
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
