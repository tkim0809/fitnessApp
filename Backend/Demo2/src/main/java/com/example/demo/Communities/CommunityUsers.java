package com.example.demo.Communities;

import com.example.demo.appuser.AppUser;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Table;
import org.springframework.core.style.ToStringCreator;
import javax.persistence.*;

@Entity
public class CommunityUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;

    @Column(name = "Community_name")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private Communities Community_id;

    @Column(name = "UserId")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AppUser UserId;

    //constructor
    public CommunityUsers() {
    }

    public CommunityUsers(Communities CommunityId, AppUser UserId) {
        this.Community_id = CommunityId;
        this.UserId = UserId;
    }

    //getters and setters

     public AppUser getUserId() {
        return UserId;
    }

    public void setUserName(AppUser UserId) {
        this.UserId = UserId;
    }

    public Communities getCommunityId() {
        return Community_id;
    }

    public void setCommunityId(Communities CommunityId) {
        this.Community_id = CommunityId;
    }

}
