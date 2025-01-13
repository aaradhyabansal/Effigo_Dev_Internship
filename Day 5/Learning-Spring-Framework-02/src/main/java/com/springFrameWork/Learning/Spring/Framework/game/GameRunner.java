package com.springFrameWork.Learning.Spring.Framework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
//    MarioGame game;
    private GamingConsole game;
    public GameRunner(@Qualifier("mario") GamingConsole game) {
        this.game=game;

    }

    public void run() {
        System.out.println("Running Game"+game);
        game.up();
        game.down();
        game.left();
        game.right();
    }
}
