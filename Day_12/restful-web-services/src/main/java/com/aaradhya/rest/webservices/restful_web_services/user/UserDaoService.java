package com.aaradhya.rest.webservices.restful_web_services.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int cntId=0;
    static{
        users.add(new User(++cntId,"Adam", LocalDate.now().minusYears(30)));
                users.add(new User(++cntId,"Apple", LocalDate.now().minusYears(34)));
                users.add(new User(++cntId,"Eve", LocalDate.now().minusYears(27)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {
        User user= users.stream().filter(use->use.getId()==id).findFirst().orElse(null);

//        if(user==null){
//            UserNotFoundException exp=new UserNotFoundException("id");
//            throw exp;
//        }

        return user;
    }

    public ResponseEntity<User> save(User user) {

        users.add(new User(++cntId,user.getUsername(),user.getBirthdate()));

        return ResponseEntity.created(null).build();
    }

    public void deleteById(int id) {
        users.removeIf(use->use.getId()==id);
    }
}
