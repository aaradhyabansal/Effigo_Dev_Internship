package com.springFrameWork.Learning.Spring.Framework;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

record Person(String name, int age,Address address) {};
record Address(String address, String city) {};

@Configuration
public class HelloWorldSpringConfiguration {
    @Bean
    public String name()
    {
        return "Aaradhya";
    }
    @Bean
    public int age()
    {
        return 21;
    }

    @Bean
    public Person person()
    {
        return new Person("Aaradhya",21,new Address("San Francisco","London"));
    }

    @Bean
    public Person person2MethodCall()
    {
        return new Person(name(),age(),address());
    }

    @Bean
    public Person person2Parameters(String name, int age, Address address2)
    {
        return new Person(name,age,address2);
    }
    @Bean(name="address2")
    public Address address()
    {
        return new Address("12 Arbour Road","London");
    }
}
