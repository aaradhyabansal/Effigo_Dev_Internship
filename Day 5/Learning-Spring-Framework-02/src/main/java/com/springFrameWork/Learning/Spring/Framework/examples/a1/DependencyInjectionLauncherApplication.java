//package com.springFrameWork.Learning.Spring.Framework.examples.a1;
//
//import com.springFrameWork.Learning.Spring.Framework.game.GameRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//
//@Component
//class YourBusinessClass{
//
//    public static void main(String[] args) {
//
//        try(var context=new AnnotationConfigApplicationContext(DependencyInjectionLauncherApplication.class)){
////            context.getBean(GamingConsole.class).up();
////            ((GamingConsole) context.getBean("game")).down();
////            context.getBean((GameRunner.class)).run();
////            context.getBean((MarioGame.class));
//            Arrays.stream(context.getBeanDefinitionNames())
//                    .forEach(System.out::println);
//
//            System.out.println(context.getBean(YourBusinessClass.class));
//
//        }
//    }
//}
