package rekrutacja.service;

import rekrutacja.domain.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Filter {

    public List<Account> filterData(List<Account> accounts) {

        List<Account> accountsResult = new ArrayList<>();
        accounts.stream()
                .forEach(a -> {
                    if (isCurrencyCorrect(a) && isBalanceCorrect(a) && isClosingDateCorrect(a) && isIbanCorrect(a.getIban())) {
                        accountsResult.add(a);
                    }
                });
        return accountsResult;
    }

    private boolean isCurrencyCorrect(Account a) {
        return a.getCurrency().equals("PLN");
    }

    private boolean isBalanceCorrect(Account a) {
        return !(a.getBalance().compareTo(BigDecimal.ZERO) < 0);
    }

    private boolean isClosingDateCorrect(Account a) {
        return a.getClosingDate().after(new Date());
    }

    private boolean isIbanCorrect(String iban) {

        if (isIbanPL(iban) && hasIbanDigits(iban) && hasIbanRightLength(iban)) {
            return true;
        }
        return false;
    }

    private boolean isIbanPL(String iban) {
        return iban.substring(0, 2).equals("PL");
    }

    private boolean hasIbanDigits(String iban) {
        return iban.substring(2).chars().allMatch(Character::isDigit);
    }

    private boolean hasIbanRightLength(String iban) {
        return iban.length() == 28;
    }
}
