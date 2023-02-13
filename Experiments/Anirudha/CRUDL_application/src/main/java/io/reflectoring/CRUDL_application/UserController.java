package io.reflectoring.CRUDL_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {
    
    @GetMapping("/get/all")
    public List<UserStat> getAll(){
        return null;
    };
}
