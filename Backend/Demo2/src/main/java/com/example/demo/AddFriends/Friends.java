package com.example.demo.AddFriends;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;


import com.example.demo.appuser.AppUser;

//public class Friends {
//    private int userId;
//    private String friendEmail;
//
//    public Friends(int userId, String friendEmail) {
//        this.userId = userId;
//        this.friendEmail = friendEmail;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public String getFriendEmail() {
//        return friendEmail;
//    }
//}
@Entity
@Table(name = "friends")
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Column(name = "friend_id")
    private Long friendId;

    public Friends() {}

    public Friends(AppUser user, Long friendId) {
        this.user = user;
        this.friendId = friendId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }
}
