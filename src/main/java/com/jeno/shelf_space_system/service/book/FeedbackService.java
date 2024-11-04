package com.jeno.shelf_space_system.service.book;

import com.jeno.shelf_space_system.dto.common.PageResponse;
import com.jeno.shelf_space_system.dto.feedback.FeedbackRequest;
import com.jeno.shelf_space_system.dto.feedback.FeedbackResponse;
import com.jeno.shelf_space_system.mapper.FeedbackMapper;
import com.jeno.shelf_space_system.model.book.Book;
import com.jeno.shelf_space_system.model.book.Feedback;
import com.jeno.shelf_space_system.model.user.User;
import com.jeno.shelf_space_system.repository.BookRepository;
import com.jeno.shelf_space_system.repository.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final BookRepository bookRepository;
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    public Integer saveFeedback(@Valid FeedbackRequest request, Authentication connectedUser) throws OperationNotSupportedException {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID :" + request.bookId()));
        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotSupportedException("You cannot add feedback to this book");
        }

        User user = (User) connectedUser.getPrincipal();
        if (Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotSupportedException("You cannot give a feedback to your own book");
        }
        Feedback feedback = feedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }

    public PageResponse<FeedbackResponse> getAllFeedbacksByBook(Integer bookId, int pageNo, int pageSize, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        User user = (User) connectedUser.getPrincipal();
        Page<Feedback> feedbacks = feedbackRepository.findAllFeedbacksByBookId(bookId, pageable);
        List<FeedbackResponse> feedbackResponses = feedbacks.stream()
                .map(feedback -> feedbackMapper.toFeedbackResponse(feedback, user.getId()))
                .toList();
        return new PageResponse<>(
                feedbackResponses,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );
    }
}
