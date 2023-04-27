package com.academy.mortgage.model.api.request;

import com.academy.mortgage.model.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ApplicationStatusUpdateRequest {
    @NotNull
    Long applicationId;
    @NotNull
    ApplicationStatus applicationStatus;
}
