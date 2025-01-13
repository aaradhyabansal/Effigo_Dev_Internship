package com.springFrameWork.Learning.Spring.Framework;

import com.springFrameWork.Learning.Spring.Framework.game.GameRunner;
import com.springFrameWork.Learning.Spring.Framework.game.GamingConsole;
import com.springFrameWork.Learning.Spring.Framework.game.PacManGamee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GamingConfiguration {
//    var game=new MarioGame();
    //        var game=new SuperContraGame();
//    var game=new PacManGamee();
//    var gameRunner=new GameRunner(game);
//        gameRunner.run();
@Bean
public GamingConsole game(){
var game =new PacManGamee();
return game;
}

@Bean
    public GameRunner gameRunner(GamingConsole game){
    var gameRunner=new GameRunner(game);
    return gameRunner;
}
}
//}
