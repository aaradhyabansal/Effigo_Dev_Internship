package com.springFrameWork.Learning.Spring.Framework.examples.d1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class ClassA{

}
@Component
class ClassB{
    private ClassA classA;
    public ClassB(ClassA classA)
    {
        System.out.println("Some initilization Logic");
       this.classA=classA;
    }
    public void HelloWorld()
    {
        System.out.println("Hello World");
    }
}

@Configuration
@ComponentScan
public class LazyInitializertLauncherApplication {

    public static void main(String[] args) {

        try(var context=new AnnotationConfigApplicationContext(LazyInitializertLauncherApplication.class)){
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);
            System.out.println(("Initilization is Completed"));
            context.getBean(ClassB.class).HelloWorld();
        }
    }
}
