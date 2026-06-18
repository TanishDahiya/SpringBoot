package com.module1.springbootdemo.h4.controller;

import com.module1.springbootdemo.h4.dto.PostAuditDto;
import com.module1.springbootdemo.h4.entity.Post;
import com.module1.springbootdemo.h4.repository.PostRepository;
import com.module1.springbootdemo.h4.service.PostService;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/audit")
public class AuditController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private final PostService postService;


    //1st Method using internal API of Hibernate envs that is Audit Reader class
    @GetMapping("/postsrevision/{id}")
    public List<Post> getPostRevision(@PathVariable Long id) {
        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revisions =  reader.getRevisions(Post.class, id);
        return revisions.stream().map(revision -> reader.find(Post.class, id, revision)).toList();
    }


    //second method using native query to fetch the revision history of the post
    @GetMapping("/postsrevision/native/{id}")
    public ResponseEntity<List<PostAuditDto>> getPostAuditHistory(@PathVariable Long id) {

        List<PostAuditDto> history = postService.getHistory(id);

        return ResponseEntity.ok(history);
    }
}
