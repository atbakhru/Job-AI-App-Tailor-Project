package com.jobapptailor.repository;

import com.jobapptailor.model.Match;
import com.jobapptailor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    
    List<Match> findByUser(User user);
    
    List<Match> findByUserOrderByCreatedAtDesc(User user);
}
