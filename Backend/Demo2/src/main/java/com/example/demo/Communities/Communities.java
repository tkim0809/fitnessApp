package com.example.demo.Communities;


import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Table;
import org.springframework.core.style.ToStringCreator;
import javax.persistence.*;

@Entity
public class Communities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;

    @Column(name = "Community_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String Community_name;

    //constructor
    public Communities() {
    }

    public Communities(String Community_name) {
        this.Community_name = Community_name;
    }

    //getters and setters

     public String getCommunityName() {
        return Community_name;
    }

    public void setCommunityName(String Community_name) {
        this.Community_name = Community_name;
    }


}
