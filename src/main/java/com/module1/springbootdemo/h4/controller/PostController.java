package com.module1.springbootdemo.h4.controller;

import com.module1.springbootdemo.h4.dto.RequestPostDto;
import com.module1.springbootdemo.h4.dto.ResponsePostDto;
import com.module1.springbootdemo.h4.repository.PostRepository;
import com.module1.springbootdemo.h4.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/createPost")
    public ResponseEntity<ResponsePostDto> createBlogPost(@RequestBody RequestPostDto requestPostDto){
        ResponsePostDto responsePostDto = postService.createPost(requestPostDto);
        return new ResponseEntity<>(responsePostDto, HttpStatus.OK);
    }

    @PutMapping("/updatePost/{id}")
        public ResponseEntity<ResponsePostDto> updateBlogPost(@RequestBody RequestPostDto requestPost, @PathVariable Long id){
        ResponsePostDto responsePostDto = postService.updatePost(requestPost, id);
        return new ResponseEntity<>(responsePostDto, HttpStatus.OK);
    }
}
