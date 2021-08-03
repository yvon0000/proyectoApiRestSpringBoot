
package com.gx.yvon.controller;

import com.gx.yvon.entity.User;
import com.gx.yvon.services.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserControllador {
    
    @Autowired
    private UserService userService;
    //agregar user
    @PostMapping
    public ResponseEntity<?> create (@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
      
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> read (@PathVariable(value="id") Long userId){
        Optional<User> oUser=userService.findById(userId);
        if(!oUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oUser);
    }
    //update un usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> update( @RequestBody User userDetails,@PathVariable (value="id") long idUser){
       Optional<User> user=userService.findById(idUser);
       if(!user.isPresent()){
           return ResponseEntity.notFound().build();
       }
       user.get().setName(userDetails.getName());
       user.get().setSurname(userDetails.getSurname());
       user.get().setEmail(userDetails.getEmail());
       user.get().setEnebled(userDetails.getEnebled());
    
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
        
    }
    //para borrar 
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete( @PathVariable (value ="id") Long idUser){
        if(!userService.findById(idUser).isPresent()){
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(idUser);
        
        return ResponseEntity.ok().build();
    }
    //listar Todos
    @GetMapping
    public List<User> readAll(){
        List<User>users=StreamSupport
                .stream(userService.findAll().spliterator(), false)
                .collect(Collectors.toList())
                ;
        return users;
    }
    
}
