
package com.gx.yvon.repositoty;

import com.gx.yvon.entity.User;
import java.awt.print.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao  extends JpaRepository<User,Long>{

    
}
