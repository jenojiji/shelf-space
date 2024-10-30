package com.jeno.shelf_space_system.model.book;

import com.jeno.shelf_space_system.model.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback extends BaseEntity {

    private String note;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}