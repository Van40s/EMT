package mk.ukim.finki.library.model.event;


import lombok.Getter;
import mk.ukim.finki.library.model.Book;
import org.springframework.context.ApplicationEvent;


@Getter
public class BookCreatedEvent extends ApplicationEvent {
    public BookCreatedEvent(Book source) {
        super(source);
    }
}