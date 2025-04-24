package com.example.bookapi.service;

import com.example.bookapi.model.Book;
import com.example.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private static final Pattern ALPHANUMERIC_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]+$");

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        validateBook(book);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(String author, Boolean published) {
        if (author != null && published != null) {
            return bookRepository.findByAuthorAndPublished(author, published);
        } else if (author != null) {
            return bookRepository.findByAuthor(author);
        } else if (published != null) {
            return bookRepository.findByPublished(published);
        }
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private void validateBook(Book book) {
        if (!ALPHANUMERIC_PATTERN.matcher(book.getTitle()).matches()) {
            throw new ValidationException("Title must be alphanumeric.");
        }
        if (!ALPHANUMERIC_PATTERN.matcher(book.getAuthor()).matches()) {
            throw new ValidationException("Author must be alphanumeric.");
        }
    }
}