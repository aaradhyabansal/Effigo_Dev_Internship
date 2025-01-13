package com.springFrameWork.Learning.Spring.Framework.examples.f1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
class SomeClass{
    private SomeDependency someDependency;

    public SomeClass(SomeDependency someDependency) {
        super();
        this.someDependency=someDependency;
        System.out.println("All Dependencies are Ready");
    }
    @PostConstruct
    public void Initialize()
    {
        someDependency.getReady();
    }

    @PreDestroy
    public void Destroy()
    {
        someDependency.getDestroy();
    }
};

@Component
class SomeDependency{

    public void getReady() {
        System.out.println("All Dependencies are Readyyyyy");
    }

    public void getDestroy() {
        System.out.println("All Dependencies are Destroy");
    }
};


@Configuration
@ComponentScan
public class PrePostAnnotationLauncherApplication {

    public static void main(String[] args) {

        try(var context=new AnnotationConfigApplicationContext(PrePostAnnotationLauncherApplication.class)){
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);
        }
    }
}
