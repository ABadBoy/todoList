package com.example.demo.repository;

import com.example.demo.entity.UserRecord;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserRecord,Integer>
{
    UserRecord findByName(String name);

    UserRecord findByNameAndPassword(String name,String password);
}
