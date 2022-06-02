package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        User user = findByPassport(passport);
        if (user != null) {
            if (!users.get(user).contains(account)) {
                users.get(user).add(account);
            }
        }
    }

    /**
     * Метод позволяет получить пользователя по номеру паспорта.
     * @param passport Строка с номером паспорта пользователя.
     * @return возвращает объект User искомого пользователя.
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод позволяет получить счет пользователя по номеру паспорта.
     * @param passport Строка с номером паспорта пользователя.
     * @param requisite Строка с номером счета пользователя.
     * @return возвращает объект Account искомого пользователя.
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> list = users.get(user);
            return list.stream()
                    .filter(account -> account.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
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
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
