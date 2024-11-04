package com.jeno.shelf_space_system.mapper;

import com.jeno.shelf_space_system.dto.feedback.FeedbackRequest;
import com.jeno.shelf_space_system.dto.feedback.FeedbackResponse;
import com.jeno.shelf_space_system.model.book.Book;
import com.jeno.shelf_space_system.model.book.Feedback;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeedbackMapper {

    public Feedback toFeedback(@Valid FeedbackRequest request) {
        return Feedback.builder()
                .note(request.note())
                .comment(request.comment())
                .book(Book.builder()
                        .id(request.bookId())
                        .build())
                .build();
    }

    public FeedbackResponse toFeedbackResponse(Feedback feedback, Integer id) {
        return FeedbackResponse.builder()
                .note(feedback.getNote())
                .comment(feedback.getComment())
                .ownFeedback(Objects.equals(feedback.getCreatedBy(), id))
                .build();
    }
}
