package com.example.test_runners.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Run(
        @Id Integer id,
        @NotEmpty String title,
        LocalDateTime startOn,
        LocalDateTime completeOn,
        @Positive Integer miles,
        Location location,
        @Version Integer version
) {
    public Run{
        if(!completeOn.isAfter(startOn)){
            throw new IllegalArgumentException("Completed On Must Be After Started On");
        }
    }
}
