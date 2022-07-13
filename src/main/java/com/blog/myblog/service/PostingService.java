package com.blog.myblog.service;

import com.blog.myblog.model.Posting;
import com.blog.myblog.repository.PostingRepository;
import com.blog.myblog.DTO.PostingRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostingService {
    private final PostingRepository postingRepository;

    @Transactional
    public Long delete(Long id, String pw) {
        Posting posting = postingRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (posting.getPw().equals(pw)) postingRepository.deleteById(id);
        else return 0L;
        return posting.getId();
    }

    @Transactional
    public Long update(Long id, String pw, PostingRequestDTO requestDTO) {
        Posting posting = postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (posting.getPw().equals(pw)) posting.update(requestDTO);
        else return 0L;
        return posting.getId();
    }
}
