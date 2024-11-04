package com.jeno.shelf_space_system.repository;

import com.jeno.shelf_space_system.model.book.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    @Query("""
            SELECT feedback
            FROM Feedback feedback
            WHERE feedback.book.id=:bookId
            """)
    Page<Feedback> findAllFeedbacksByBookId(Integer bookId, Pageable pageable);
}
