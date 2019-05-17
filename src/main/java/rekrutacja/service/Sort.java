package rekrutacja.service;

import rekrutacja.domain.Account;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sort {

    public List<Account> sortByNameAsc(List<Account> accounts) {

        List<Account> accountList = accounts.stream()
                .sorted(Comparator.comparing(Account::getName))
                .collect(Collectors.toList());
        return accountList;
    }
}
