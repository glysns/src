package digytal.backend.api.infra.email;

public interface SendEmail {
    void send(Message message);
}