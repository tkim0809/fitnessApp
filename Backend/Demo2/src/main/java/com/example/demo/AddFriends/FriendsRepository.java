package com.example.demo.AddFriends;
//
////import java.sql.SQLException;
//
//import com.example.demo.AddFriends.Friends;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class FriendsRepository implements JpaRepository<Friends, Integer> {
//
//    @Override
//    public List<Friends> findAll() {
//        return null;
//    }
//
//    @Override
//    public List<Friends> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<Friends> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public List<Friends> findAllById(Iterable<Integer> iterable) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Integer integer) {
//
//    }
//
//    @Override
//    public void delete(Friends friends) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Friends> iterable) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public <S extends Friends> S save(S s) {
//        return null;
//    }
//
//    @Override
//    public <S extends Friends> List<S> saveAll(Iterable<S> iterable) {
//        return null;
//    }
//
//    @Override
//    public Optional<Friends> findById(Integer integer) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Integer integer) {
//        return false;
//    }
//
//    @Override
//    public void flush() {
//
//    }
//
//    @Override
//    public <S extends Friends> S saveAndFlush(S s) {
//        return null;
//    }
//
//    @Override
//    public void deleteInBatch(Iterable<Friends> iterable) {
//
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//
//    }
//
//    @Override
//    public Friends getOne(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public <S extends Friends> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends Friends> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends Friends> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends Friends> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends Friends> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends Friends> boolean exists(Example<S> example) {
//        return false;
//    }
//}
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long> {

    @Query("SELECT f.friendId FROM Friends f WHERE f.user.email = ?1")
    List<Long> findFriendIdsByEmail(String email);

    @Query("SELECT COUNT(f) > 0 FROM Friends f WHERE f.user.email = ?1 AND f.friendId = ?2")
    boolean existsByEmailAndFriendId(String email, Long friendId);
}

