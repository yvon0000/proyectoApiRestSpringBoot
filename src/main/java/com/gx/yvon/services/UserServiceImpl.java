
package com.gx.yvon.services;

import com.gx.yvon.entity.User;
import com.gx.yvon.repositoty.UserDao;
import java.awt.print.Pageable;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
return userDao.findAll();
    }

    

    @Override
        @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
return userDao.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return  userDao.save(user);
       
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
userDao.deleteById(id);
    }

    @Override
     @Transactional(readOnly = true)
    public Page<User> findAll(org.springframework.data.domain.Pageable pageable) {
            return userDao.findAll(pageable);
  
    }
    
}
