package ru.egorov.electroniclibrary.models.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.egorov.electroniclibrary.dao.BookDAO;
import ru.egorov.electroniclibrary.models.Book;

import java.time.LocalDate;

@Component
public class BookValidator implements Validator {

    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Book.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if(book.getYearPublication() > LocalDate.now().getYear())
            errors.rejectValue("yearPublication", "", "Неправильный год выпуска книги");
        if(bookDAO.getBookByISBN(book.getIsbn()).isPresent()){
            errors.rejectValue("isbn", "", "Книга с данным ISBN уже есть в базе данных");
        }
    }
}
