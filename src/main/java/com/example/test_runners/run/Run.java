package com.example.test_runners.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty String title,
        LocalDateTime startOn,
        LocalDateTime completeOn,
        @Positive Integer miles,
        Location location
) {
    public Run{
        if(!completeOn.isAfter(startOn)){
            throw new IllegalArgumentException("Completed On Must Be After Started On");
        }
    }
}
