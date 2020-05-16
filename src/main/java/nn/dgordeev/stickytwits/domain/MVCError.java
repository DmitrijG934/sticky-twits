package nn.dgordeev.stickytwits.domain;

import java.time.LocalDateTime;

public class MVCError {
    private String message;
    private String path;
    private String method;
    private LocalDateTime timestamp;

    public MVCError(String message, String path, String method, LocalDateTime timestamp) {
        this.message = message;
        this.path = path;
        this.method = method;
        this.timestamp = timestamp;
    }

    public MVCError() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
