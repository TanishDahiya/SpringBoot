package com.module1.springbootdemo.h4.repository;

import com.module1.springbootdemo.h4.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = """
    SELECT
        id,
        rev,
        revtype,
        title,
        created_by,
        modified_by
    FROM post_aud
    WHERE id = :id
    ORDER BY rev
    """,
            nativeQuery = true)
    List<Object[]> getPostAuditHistory(Long id);
}
