package com.springFrameWork.Learning.Spring.Framework;

import com.springFrameWork.Learning.Spring.Framework.game.GameRunner;
import com.springFrameWork.Learning.Spring.Framework.game.GamingConsole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App03GamingSpringBeans {
    public static void main(String[] args) {

        try(var context=new AnnotationConfigApplicationContext(GamingConfiguration.class)){
//            context.getBean(GamingConsole.class).up();
//            ((GamingConsole) context.getBean("game")).down();
            context.getBean((GameRunner.class)).run();
        }
    }
}
