package digytal.desktop.util.utils.http;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseStatus {
    boolean success;
    String message;
    Serializable code;
    String suggestion;
}
