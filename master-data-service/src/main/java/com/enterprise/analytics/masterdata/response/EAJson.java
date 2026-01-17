package com.enterprise.analytics.masterdata.response;

import com.enterprise.analytics.masterdata.domain.entity.StatusEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class EAJson<T> {
    private final boolean success;
    private final Object data;
    private final String message;
    private final LocalDateTime timestamp;

    public static<T> EAJson<T> success(T data , String message) {
        return EAJson.<T>builder()
                .success(true)
                .data(data)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static EAJson error(String message) {
        return EAJson.<Object>builder()
                .success(false)
                .data(null)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
