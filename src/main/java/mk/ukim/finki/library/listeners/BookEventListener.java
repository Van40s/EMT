package mk.ukim.finki.library.listeners;

import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.event.BookCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventListener {


    @EventListener
    public void onProductCreated(BookCreatedEvent event) {
        Book book = (Book) event.getSource();
        System.out.println("Book created " + book.getName());
    }
}
