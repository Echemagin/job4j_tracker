package ru.job4j.bank;

import java.util.*;

/**
 * Класс описывает работу простой банковской системы.
 * @author Evgeny Chemagin
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение пользователей осуществляется в коллекции типа HashMap.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет пользователя в систему.
     * @param user Объект типа User который добавляется в систему.
     */
    public void addUser(User user) {
            users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод добавляет счет пользователю по номеру паспорта.
     * @param passport Строка с номером паспорта пользователя.
     * @param account Объект типа Account который добавляется пользователю.
     */
    public void addAccount(String passport, Account account) {
        var user = findByPassport(passport);
        if (user.isPresent()) {
            if (!users.get(user.get()).contains(account)) {
                users.get(user.get()).add(account);
            }
        }
    }

    /**
     * Метод позволяет получить пользователя по номеру паспорта.
     * @param passport Строка с номером паспорта пользователя.
     * @return возвращает объект Optional<User> искомого пользователя.
     */
    public Optional<User> findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * Метод позволяет получить счет пользователя по номеру паспорта.
     * @param passport Строка с номером паспорта пользователя.
     * @param requisite Строка с номером счета пользователя.
     * @return возвращает объект Optional<Account> искомого пользователя.
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        return findByPassport(passport)
                .flatMap(value -> users.get(value)
                        .stream()
                        .filter(account -> account.getRequisite().equals(requisite))
                        .findFirst()
                );
    }

    /**
     * Метод позволяет осуществить перевод средств между счетами пользователей.
     * @param srcPassport Строка с номером паспорта пользователя со счета которого будут списаны средства.
     * @param srcRequisite Строка с номером счета пользователя со счета которого будут списаны средства.
     * @param destPassport Строка с номером паспорта пользователя на счет которого будут зачислены средства.
     * @param destRequisite Строка с номером счета пользователя на счет которого будут зачислены средства.
     * @param amount количество переводимых средств.
     * @return возвращает статус перевода.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        var srcAccount = findByRequisite(srcPassport, srcRequisite);
        var destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount.isPresent() && destAccount.isPresent() && srcAccount.get().getBalance() >= amount) {
            srcAccount.get().setBalance(srcAccount.get().getBalance() - amount);
            destAccount.get().setBalance(destAccount.get().getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
