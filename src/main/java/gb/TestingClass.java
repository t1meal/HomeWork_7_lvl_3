package gb;

import gb.Annotations.AfterSuite;
import gb.Annotations.BeforeSuite;
import gb.Annotations.Test;

public class TestingClass {

    @BeforeSuite
    public void before(){
        System.out.println("BeforeSuite...");
    }
    @Test(priority = 1)
    public void testMethod1() {
        System.out.println("Test priority = 1");
    }

    @Test(priority = 4)
    public void testMethod2() {
        System.out.println("Test priority = 4");
    }

    @Test(priority = 8)
    public void testMethod3(){
        System.out.println("Test priority = 8");
    }

    @Test(priority = 6)
    private void testMethod4() {
        System.out.println("Test priority = 6 (private)");
    }

    @Test
    public void testMethod5() {
        System.out.println("Test priority default");
    }

    @AfterSuite
    public void after(){
        System.out.println("AfterSuite...");
    }

}
