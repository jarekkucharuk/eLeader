package rekrutacja.mapper;

import rekrutacja.domain.Account;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileMapper {

    public List<Account> mapToAccount(List<String> lines) {

        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account());
        int i = 0;
        for (String s : lines) {
            if (s.contains("iban")) {
                accounts.get(i).setIban(getIbanFromLine(s));
            }
            if (s.contains("name")) {
                accounts.get(i).setName(getStringData(s));
            }
            if (s.contains("currency")) {
                accounts.get(i).setCurrency(getStringData(s));
            }
            if (s.contains("balance")) {
                accounts.get(i).setBalance(getBallanceFromLine(s));
            }
            if (s.contains("closingDate")) {
                try {
                    accounts.get(i).setClosingDate(getDateFromLine(s));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (s.contains("</account>")) {
                accounts.add(new Account());
                i++;
            }
        }
        accounts.remove(accounts.size() - 1);

        return accounts;
    }

    public List<String> mapFromAccount(List<Account> accounts) {

        List<String> lines = new ArrayList<>();
        lines.add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        lines.add("<accounts>");

        accounts.stream()
                .forEach(a -> {
                    lines.add(getIbanLine(a));
                    lines.add(getNameLine(a));
                    lines.add(getCurrencyLine(a));
                    lines.add(getBalanceLine(a));
                    lines.add(getClosingDateLine(a));
                    lines.add("        </account>");
                });

        lines.add("</accounts>");
        return lines;
    }

    private String formateDateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    private String getStringData(String s) {
        return s.substring(startIndex(s), endIndex(s));
    }

    private String getIbanFromLine(String s) {
        return s.substring(startIbanIndex(s), endIbanIndex(s));
    }

    private BigDecimal getBallanceFromLine(String s) {
        return new BigDecimal(getStringData(s));
    }

    private Date getDateFromLine(String s) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(getStringData(s));
    }

    private String getIbanLine(Account a) {
        return "        <account iban=\"" + a.getIban() + "\">";
    }

    private String getNameLine(Account a) {
        return "                <name>" + a.getName() + "</name>";
    }

    private String getCurrencyLine(Account a) {
        return "                <currency>" + a.getCurrency() + "</currency>";
    }

    private String getBalanceLine(Account a) {
        return "                <balance>" + a.getBalance() + "</balance>";
    }

    private String getClosingDateLine(Account a) {
        return "                <closingDate>" + formateDateToString(a.getClosingDate()) + "</closingDate>";
    }

    private int startIndex(String line) {
        return line.indexOf(">") + 1;
    }

    private int endIndex(String line) {
        return line.lastIndexOf("<");
    }

    private int startIbanIndex(String line) {
        return line.indexOf("\"") + 1;
    }

    private int endIbanIndex(String line) {
        return line.lastIndexOf("\"");
    }

}
