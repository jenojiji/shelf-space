package com.jeno.shelf_space_system.dto.feedback;

import jakarta.validation.constraints.*;

public record FeedbackRequest(
        @NotNull(message = "200")
        @Min(value = 0, message = "201")
        @Max(value = 5, message = "202")
        double note,
        @NotNull(message = "204")
        @NotEmpty(message = "204")
        @NotBlank(message = "204")
        String comment,
        @NotNull(message = "205")
        Integer bookId
) {
}
