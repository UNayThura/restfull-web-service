package com.bar.restfullwebservice.repository;

import com.bar.restfullwebservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Htay Hlaing Aung on 9/21/2021
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
