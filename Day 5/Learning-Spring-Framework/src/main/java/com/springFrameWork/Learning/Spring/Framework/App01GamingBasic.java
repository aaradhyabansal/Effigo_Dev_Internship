package com.springFrameWork.Learning.Spring.Framework;

import com.springFrameWork.Learning.Spring.Framework.game.GameRunner;
import com.springFrameWork.Learning.Spring.Framework.game.PacManGamee;

public class App01GamingBasic {

    public static void main(String[] args) {

//        var game=new MarioGame();
//        var game=new SuperContraGame();
        var game=new PacManGamee();
        var gameRunner=new GameRunner(game);
        gameRunner.run();
    }
}
