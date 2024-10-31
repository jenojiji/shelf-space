package com.jeno.shelf_space_system.repository;

import com.jeno.shelf_space_system.model.book.BookTransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {
    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.user.userId:userId
            """)
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, Integer userId);

    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.book.owner.id:userId
            """)
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable pageable, Integer userId);
}
