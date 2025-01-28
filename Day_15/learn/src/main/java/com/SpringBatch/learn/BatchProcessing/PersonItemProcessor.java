package com.SpringBatch.learn.BatchProcessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static Logger log= LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(Person person) throws Exception {
        final String firstName=person.firstName().toUpperCase();
        final String lastName=person.lastName().toUpperCase();
        Person tPerson=new Person(firstName,lastName);
        log.info("Transforming ({}) to ({})",person,tPerson);
        return tPerson;
    }
}
