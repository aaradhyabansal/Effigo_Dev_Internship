package com.springFrameWork.Learning.Spring.Framework.HelloWorld;

import com.springFrameWork.Learning.Spring.Framework.HelloWorld.Address;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App02HelloWorldSpring {
    public static void main(String[] args) {
       try (var context = new AnnotationConfigApplicationContext(HelloWorldSpringConfiguration.class)) {
           System.out.println(context.getBean("name"));
           System.out.println(context.getBean("person"));
           System.out.println(context.getBean("person2MethodCall"));
           System.out.println(context.getBean("address2"));
           System.out.println(context.getBean(Address.class));
           System.out.println(context.getBean("person2Parameters"));
           Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
           System.out.println(context.getBean(Address.class));
           System.out.println(context.getBean("person3Qualifier"));

       }

    }
}