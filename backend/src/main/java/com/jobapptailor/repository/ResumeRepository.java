package com.jobapptailor.repository;

import com.jobapptailor.model.Resume;
import com.jobapptailor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    
    List<Resume> findByUser(User user);
    
    List<Resume> findByUserOrderByUpdatedAtDesc(User user);
}
