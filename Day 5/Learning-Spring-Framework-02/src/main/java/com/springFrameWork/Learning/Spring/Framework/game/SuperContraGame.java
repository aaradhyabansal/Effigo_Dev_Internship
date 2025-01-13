package com.springFrameWork.Learning.Spring.Framework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("contra")
public class SuperContraGame implements GamingConsole {
    public void up()
    {
        System.out.println("Up");
    }
    public void down()
    {
        System.out.println("Down");
    }
    public void left()
    {
        System.out.println("Back");
    }
    public void right()
    {
        System.out.println("Shoot a bullet");
    }
}
