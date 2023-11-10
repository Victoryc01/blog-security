package com.samjayworldwide.blogTaskWithSecurity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponse<T> {
    private String responseMessage;
    private boolean responseStatus;
    private T responseData;
    private final String responseTime = LocalDateTime.now().toString();
}
