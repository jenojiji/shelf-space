package com.jeno.shelf_space_system.service.book;

import com.jeno.shelf_space_system.model.book.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> withOwnerId(Integer ownerId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("createdBy"), ownerId);
    }
}
