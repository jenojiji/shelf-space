package com.jeno.shelf_space_system.controller.book;

import com.jeno.shelf_space_system.dto.feedback.FeedbackRequest;
import com.jeno.shelf_space_system.dto.common.PageResponse;
import com.jeno.shelf_space_system.service.book.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;

@RestController
@RequestMapping("feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Integer> saveFeedback(
            @Valid @RequestBody FeedbackRequest request,
            Authentication connectedUser
    ) throws OperationNotSupportedException {
        return ResponseEntity.ok(feedbackService.saveFeedback(request,connectedUser));
    }

    @GetMapping("/book/{book_id}")
    public ResponseEntity<PageResponse<FeedbackResponse>> getAllFeedbacksByBook(
            @PathVariable("book_id") Integer bookId,
            @RequestParam(name = "pageNo" ,defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "pageSize" ,defaultValue = "10",required = false) int pageSize,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(feedbackService.getAllFeedbacksByBook(bookId,pageNo,pageSize,connectedUser));
    }
}
