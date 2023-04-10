package com.example.demo.AddFriends;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.demo.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long> {

    @Query("SELECT f.friendId FROM Friends f WHERE f.user.email = ?1")
    List<Long> findFriendIdsByEmail(String email);

    @Query("SELECT COUNT(f) > 0 FROM Friends f WHERE f.user.email = ?1 AND f.friendId = ?2")
    boolean existsByEmailAndFriendId(String email, Long friendId);

    List<Friends> findByIdIn(Collection<Long> ids);

    @Query("SELECT f.friendId FROM Friends f WHERE f.friendId = ?1")
    List<Long> findFriendIdsByFriendId(Long id);
}

