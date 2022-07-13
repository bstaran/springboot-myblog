package com.blog.myblog.repository;

import com.blog.myblog.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {
    List<Posting> findAllByOrderByModifiedAtDesc();
}
