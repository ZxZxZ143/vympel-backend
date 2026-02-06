package com.shop.vympel.security;

import com.shop.vympel.dtos.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest req
    ) {
        Map<String, String> details = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            details.put(fe.getField(), fe.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiErrorResponse.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .code("VALIDATION_ERROR")
                        .message("Invalid request data.")
                        .path(req.getRequestURI())
                        .details(details)
                        .build()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentials(
            BadCredentialsException ex,
            HttpServletRequest req
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ApiErrorResponse.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .code("BAD_CREDENTIALS")
                        .message("Invalid credentials.")
                        .path(req.getRequestURI())
                        .details(null)
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleInternal(
            Exception ex,
            HttpServletRequest req
    ) {
        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiErrorResponse.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .code("INTERNAL_ERROR")
                        .message("Unexpected server error.")
                        .path(req.getRequestURI())
                        .details(null)
                        .build()
        );
    }

    public static AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) ->
                writeSecurityError(response, request, HttpStatus.UNAUTHORIZED,
                        "UNAUTHORIZED", "Authentication required.");
    }

    public static AccessDeniedHandler accessDeniedHandlerAs401() {
        return (request, response, accessDeniedException) ->
                writeSecurityError(response, request, HttpStatus.UNAUTHORIZED,
                        "UNAUTHORIZED", "Authentication required.");
    }

    private static void writeSecurityError(
            HttpServletResponse response,
            HttpServletRequest request,
            HttpStatus status,
            String code,
            String message
    ) throws IOException {

        ApiErrorResponse body = ApiErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .code(code)
                .message(message)
                .path(request.getRequestURI())
                .details(null)
                .build();

        String json = toJson(body);

        response.setStatus(status.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(json);
    }

    private static String toJson(ApiErrorResponse e) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"timestamp\":\"").append(e.getTimestamp()).append("\",");
        sb.append("\"status\":").append(e.getStatus()).append(",");
        sb.append("\"code\":\"").append(escape(e.getCode())).append("\",");
        sb.append("\"message\":\"").append(escape(e.getMessage())).append("\",");
        sb.append("\"path\":\"").append(escape(e.getPath())).append("\"");
        if (e.getDetails() != null && !e.getDetails().isEmpty()) {
            sb.append(",\"details\":{");
            boolean first = true;
            for (var entry : e.getDetails().entrySet()) {
                if (!first) sb.append(",");
                first = false;
                sb.append("\"").append(escape(entry.getKey())).append("\":");
                sb.append("\"").append(escape(entry.getValue())).append("\"");
            }
            sb.append("}");
        }
        sb.append("}");
        return sb.toString();
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
