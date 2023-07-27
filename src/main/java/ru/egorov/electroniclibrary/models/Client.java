package ru.egorov.electroniclibrary.models;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Client {
    private int id;
    @NotBlank(message = "Имя не должно быть пустое")
    @Size(min = 2, max = 50, message = "Длина имени должна быть в промежутке от 2 до 50 символов")
    @Pattern(regexp = "\\D{2,50}", message = "Имя должно состоять из букв")
    private String name;
    @NotBlank(message = "Фамилия не должна быть пустой")
    @Size(min = 2, max = 50, message = "Длина фамилии должна быть в промежутке от 2 до 50 символов")
    @Pattern(regexp = "\\D{2,50}", message = "Фамилия должна состоять из букв")
    private String surname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Дата не должна быть пустой")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Телефон не должен быть пустым")
    @Pattern(regexp = "\\d{1}\\s{1}[(]{1}\\d{3}[)]{1}\\s{1}\\d{3}[-]{1}\\d{2}[-]{1}\\d{2}",
            message = "Телефон должен соответствовать формату: \"x (xxx) xxx-xx-xx\"")
    private String phone;

    public Client() {
    }

    public Client(int id, String name, String surname, LocalDate date_of_birth, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = date_of_birth;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

//    public LocalDate getDateOfBirthByDate(){
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//        return LocalDate.parse(dateOfBirth, df);
//    }

    public void setDateOfBirth(LocalDate date_of_birth) {
        this.dateOfBirth = date_of_birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
