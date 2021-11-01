package com.bar.restfullwebservice.config;

import com.bar.restfullwebservice.entity.User;
import com.bar.restfullwebservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Htay Hlaing Aung on 9/21/2021
 */
@Component
@RequiredArgsConstructor
public class ApplicationListenerImpl implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        prepareUser();
    }

    private void prepareUser(){
        List<User> users = new ArrayList<>();
        User user1 = new User(1L,"AAAA", new Date());
        User user2 = new User(2L,"BBBB", new Date());
        users.add(user1);users.add(user2);
        this.userRepository.saveAll(users);
    }
}
