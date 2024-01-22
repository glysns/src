package digytal.backend.api.infra.email;

import lombok.Data;

@Data
public class Message {
    private String from;
    private String to;
    private String title;
    private String body;

}
