package com.example.demo.Communities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CommunitiesUserRepository extends JpaRepository<CommunityUsers, Integer>{
    List<CommunityUsers> findByCommunityId(int id);

    List<CommunityUsers> findByUserId(String userName);
}
