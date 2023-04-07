package com.example.demo.Leaderboards;

import com.example.demo.Leaderboards.Leaderboards;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LeaderboardsRepository implements JpaRepository<Leaderboards, Integer> {

    @Override
    public List<Leaderboards> findAll() {
        return null;
    }

    @Override
    public List<Leaderboards> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Leaderboards> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Leaderboards> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Leaderboards leaderboards) {

    }

    @Override
    public void deleteAll(Iterable<? extends Leaderboards> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Leaderboards> S save(S s) {
        return null;
    }

    @Override
    public <S extends Leaderboards> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Leaderboards> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Leaderboards> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Leaderboards> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Leaderboards getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Leaderboards> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Leaderboards> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Leaderboards> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Leaderboards> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Leaderboards> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Leaderboards> boolean exists(Example<S> example) {
        return false;
    }
}
