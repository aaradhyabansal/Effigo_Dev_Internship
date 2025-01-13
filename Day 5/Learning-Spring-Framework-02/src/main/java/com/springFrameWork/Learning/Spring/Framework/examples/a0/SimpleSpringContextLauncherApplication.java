package com.springFrameWork.Learning.Spring.Framework.examples.a0;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
//@ComponentScan("com.springFrameWork.Learning.Spring.Framework.examples.a1")not needed as ComponentScan by default performs the scan on the current package/file
//class GamingConfiguration {
//    //    var game=new MarioGame();
//    //        var game=new SuperContraGame();
////    var game=new PacManGamee();
////    var gameRunner=new GameRunner(game);
////        gameRunner.run();
//    @Bean
//    public GamingConsole game(){
//        var game =new PacManGamee();
//        return game;
//    }
//
//    @Bean
//    public GameRunner gameRunner(GamingConsole game){
//        var gameRunner=new GameRunner(game);
//        return gameRunner;
//    }
//}
public class SimpleSpringContextLauncherApplication {
//    @Bean
//    public GamingConsole game(){
//        var game =new PacManGamee();
//        return game;
//    }

//    @Bean
//    public GameRunner gameRunner(GamingConsole game){
//        var gameRunner=new GameRunner(game);
//        return gameRunner;
//    }
    public static void main(String[] args) {

        try(var context=new AnnotationConfigApplicationContext(SimpleSpringContextLauncherApplication.class)){
//            context.getBean(GamingConsole.class).up();
//            ((GamingConsole) context.getBean("game")).down();
//            context.getBean((GameRunner.class)).run();
//            context.getBean((MarioGame.class));
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);
        }
    }
}
