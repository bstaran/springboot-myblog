package com.blog.myblog.Controller;

import com.blog.myblog.domain.Posting;
import com.blog.myblog.domain.PostingRepository;
import com.blog.myblog.domain.PostingRequestDTO;
import com.blog.myblog.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostingController {
    private final PostingRepository postingRepository;
    private final PostingService postingService;

    @PostMapping("/api/postings")
    public Posting createPosting(@RequestBody PostingRequestDTO requestDTO) {
        Posting posting = new Posting(requestDTO);
        return postingRepository.save(posting);
    }

    @GetMapping("/api/postings")
    public List<Posting> getPosting() {
        return postingRepository.findAllByOrderByModifiedAtDesc();
    }

    @DeleteMapping("/api/postings/{id}/{pw}")
    public Long deletePosting(@PathVariable Long id, @PathVariable String pw) {
        return postingService.delete(id, pw);
    }

    @PutMapping("/api/postings/{id}/{pw}")
    public Long updatePosting(@PathVariable Long id, @PathVariable String pw, @RequestBody PostingRequestDTO requestDTO) {
        return postingService.update(id, pw, requestDTO);
    }
}
