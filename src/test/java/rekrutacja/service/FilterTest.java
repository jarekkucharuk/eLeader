package rekrutacja.service;

import org.junit.Assert;
import org.junit.Test;
import rekrutacja.domain.Account;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FilterTest {

    @Test
    public void filterData() throws ParseException {

        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account());
        accounts.get(0).setIban("PL61109010140000071219812875");
        accounts.get(0).setName("konto 1");
        accounts.get(0).setCurrency("PLN");
        accounts.get(0).setBalance(BigDecimal.valueOf(12345.67));
        accounts.get(0).setClosingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2029-10-11"));
        accounts.add(new Account());
        accounts.get(1).setIban("DE61109010140000074566565463");
        accounts.get(1).setName("Main count");
        accounts.get(1).setCurrency("PLN");
        accounts.get(1).setBalance(BigDecimal.valueOf(222.99));
        accounts.get(1).setClosingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-22"));
        accounts.add(new Account());
        accounts.get(2).setIban("PL611090101400071219812870");
        accounts.get(2).setName("konto 1");
        accounts.get(2).setCurrency("PLN");
        accounts.get(2).setBalance(BigDecimal.valueOf(12345.67));
        accounts.get(2).setClosingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2029-10-11"));
        accounts.add(new Account());
        accounts.get(3).setIban("PL61109010140000071219812870");
        accounts.get(3).setName("konto 1");
        accounts.get(3).setCurrency("EUR");
        accounts.get(3).setBalance(BigDecimal.valueOf(12345.67));
        accounts.get(3).setClosingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2029-10-11"));
        accounts.add(new Account());
        accounts.get(4).setIban("PL61109010140000071219812870");
        accounts.get(4).setName("konto 1");
        accounts.get(4).setCurrency("PLN");
        accounts.get(4).setBalance(BigDecimal.valueOf(-12345.67));
        accounts.get(4).setClosingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2029-10-11"));
        accounts.add(new Account());
        accounts.get(5).setIban("PL61109010140000071219812870");
        accounts.get(5).setName("konto 1");
        accounts.get(5).setCurrency("PLN");
        accounts.get(5).setBalance(BigDecimal.valueOf(12345.67));
        accounts.get(5).setClosingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2012-10-11"));

        Filter filter = new Filter();
        List<Account> accountsResult = filter.filterData(accounts);
        Assert.assertEquals(1, accountsResult.size());
        Assert.assertEquals("PL61109010140000071219812875", accountsResult.get(0).getIban());
    }
}