package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает счет пользователя.
 * @author Evgeny Chemagin
 * @version 1.0
 */
public class Account {
    /**
     * Строка хранящая номер счета пользователя.
     */
    private String requisite;

    /**
     * Число хранящее количество средств на счету.
     */
    private double balance;

    /**
     * Конструктор объекта Account.
     * @param requisite Строка хранящая номер счета пользователя.
     * @param balance Число хранящее количество средств на счету.
     */
    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * @return возвращает номер счета.
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     * @param requisite номер счета пользователя.
     */
    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * @return возвращает количество средств на счету.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance колличество средств на счету.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Метод позволяющий сравнить два счета пользователя по номеру счета.
     * @param o сравниваемый счет.
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
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
