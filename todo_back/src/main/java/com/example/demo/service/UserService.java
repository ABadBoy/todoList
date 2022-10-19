package com.example.demo.service;

import com.example.demo.entity.UserRecord;
import com.example.demo.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<UserRecord> getAllUsers()
    {
        List<UserRecord> userRecords = new ArrayList<>();
        userRepository.findAll().forEach(userRecords::add);
        return userRecords;
    }

    public void addUser(UserRecord userRecord)
    {
        userRepository.save(userRecord);
    }

    public void updateUser(UserRecord userRecord)
    {
        Optional<UserRecord> userRecordOptional = userRepository.findById(userRecord.getId());
        if ( userRecordOptional.isPresent() )
        {
            UserRecord userRecordFind = userRecordOptional.get();
            userRecordFind.setName(userRecord.getName());
            userRecordFind.setEmail(userRecord.getEmail());
            userRepository.save(userRecordFind);
        }
    }

    public void deleteUser(Integer id)
    {
        userRepository.deleteById(id);
    }

    public UserRecord findByName(String name)
    {
       return userRepository.findByName(name);
    }

    public UserRecord findByNameAndPassword(String name,String password)
    {
        return userRepository.findByNameAndPassword(name,password);
    }
}
