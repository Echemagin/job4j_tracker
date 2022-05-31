package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает объект пользователя.
 * @author Evgeny Chemagin
 * @version 1.0
 */
public class User {
    /**
     * Строка хранящая номер паспорта пользователя.
     */
    private String passport;

    /**
     * Строка хранящая логин пользователя.
     */
    private String username;

    /**
     * Конструктор объекта User.
     * @param passport Строка хранящая номер паспорта пользователя.
     * @param username Строка хранящая логин пользователя.
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * @return возвращает номер паспорта.
     */
    public String getPassport() {
        return passport;
    }

    /**
     * @param passport номер паспорта.
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * @return возвращает логин пользователя.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username логин пользователя.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Метод позволяющий сравнить двух пользователей по номеру паспорта.
     * @param o сравниваемый пользователь.
     * @return результат сравнения.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
