package com.jeno.shelf_space_system.dto.feedback;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackResponse {

    private double note;
    private String comment;
    private boolean ownFeedback;
}
