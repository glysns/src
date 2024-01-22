package digytal.backend.api.infra.email;

import lombok.Data;

@Data
public class Message {
    private String title;
    private String body;
    private String from;
    private String to;
}
