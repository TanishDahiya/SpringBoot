package com.module1.springbootdemo.h4.service;

import com.module1.springbootdemo.h4.dto.PostAuditDto;
import com.module1.springbootdemo.h4.dto.RequestPostDto;
import com.module1.springbootdemo.h4.dto.ResponsePostDto;
import com.module1.springbootdemo.h4.entity.Post;
import com.module1.springbootdemo.h4.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public ResponsePostDto createPost(RequestPostDto requestPostDto) {
        Post post = modelMapper.map(requestPostDto, Post.class);
        Post postSaved =  postRepository.save(post);
        ResponsePostDto responsePostDto = modelMapper.map(postSaved, ResponsePostDto.class);
        return responsePostDto;
    }

    public ResponsePostDto updatePost(RequestPostDto requestPost, Long id) {

        Post existingPost = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));

        existingPost.setTitle(requestPost.getTitle());
        existingPost.setDescription(requestPost.getDescription());

        Post updatedPost = postRepository.save(existingPost);

        return modelMapper.map(updatedPost, ResponsePostDto.class);
    }

    // fetching revision rows using native query
    public List<PostAuditDto> getHistory(Long id) {

        List<Object[]> rows = postRepository.getPostAuditHistory(id);

        return rows.stream()
                .map(row -> new PostAuditDto(
                        ((Number) row[0]).longValue(),
                        ((Number) row[1]).intValue(),
                        ((Number) row[2]).intValue(),
                        (String) row[3],
                        (String) row[4],
                        (String) row[5]
                ))
                .toList();
    }
}
