package com.springFrameWork.Learning.Spring.Framework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mario")
public class MarioGame implements GamingConsole{
    public void up()
    {
        System.out.println("Jump");
    }
    public void down()
    {
        System.out.println("Down");
    }
    public void left()
    {
        System.out.println("go back");
    }
    public void right()
    {
        System.out.println("go forward");
    }
}
