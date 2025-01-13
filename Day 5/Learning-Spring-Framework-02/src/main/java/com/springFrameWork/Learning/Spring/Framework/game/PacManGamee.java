package com.springFrameWork.Learning.Spring.Framework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("pacman")
public class PacManGamee implements GamingConsole {
    public void up()
    {
        System.out.println("Go Up");
    }
    public void down()
    {
        System.out.println("Go Down");
    }
    public void left()
    {
        System.out.println("go left");
    }
    public void right()
    {
        System.out.println("go right");
    }
}
