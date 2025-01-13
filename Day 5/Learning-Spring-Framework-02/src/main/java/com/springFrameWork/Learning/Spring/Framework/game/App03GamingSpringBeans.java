package com.springFrameWork.Learning.Spring.Framework.game;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.springFrameWork.Learning.Spring.Framework.game")
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
public class App03GamingSpringBeans {
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

        try(var context=new AnnotationConfigApplicationContext(App03GamingSpringBeans.class)){
//            context.getBean(GamingConsole.class).up();
//            ((GamingConsole) context.getBean("game")).down();
            context.getBean((GameRunner.class)).run();
//            context.getBean((MarioGame.class));
        }
    }
}
