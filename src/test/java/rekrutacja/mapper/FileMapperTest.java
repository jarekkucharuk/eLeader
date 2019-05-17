package rekrutacja.mapper;

import org.junit.Assert;
import org.junit.Test;
import rekrutacja.domain.Account;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FileMapperTest {

    FileMapper fileMapper = new FileMapper();

    @Test
    public void mapToAccount() throws ParseException {

        List<String> lines = new ArrayList<>();
        lines.add("<?xml version = \"1.0\"?>");
        lines.add("<accounts>");
        lines.add("        <account iban=\"PL61109010140000071219812870\">");
        lines.add("              <name>pasikonik</name>");
        lines.add("              <currency>PLN</currency>");
        lines.add("              <balance>9876.54</balance>");
        lines.add("             <closingDate>2029-10-11</closingDate>");
        lines.add("           </account>");
        lines.add("        <account iban=\"FR6110901014\">");
        lines.add("              <name>maison</name>");
        lines.add("              <currency>EUR</currency>");
        lines.add("              <balance>666.99</balance>");
        lines.add("             <closingDate>2011-04-04</closingDate>");
        lines.add("           </account>");
        lines.add("</accounts>");

        List<Account> accounts = fileMapper.mapToAccount(lines);
        Assert.assertEquals(2, accounts.size());
        Assert.assertEquals("PL61109010140000071219812870", accounts.get(0).getIban());
        Assert.assertEquals("pasikonik", accounts.get(0).getName());
        Assert.assertEquals("PLN", accounts.get(0).getCurrency());
        Assert.assertEquals(BigDecimal.valueOf(9876.54), accounts.get(0).getBalance());
        Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2029-10-11"), accounts.get(0).getClosingDate());
        Assert.assertEquals("FR6110901014", accounts.get(1).getIban());
        Assert.assertEquals("maison", accounts.get(1).getName());
        Assert.assertEquals("EUR", accounts.get(1).getCurrency());
        Assert.assertEquals(BigDecimal.valueOf(666.99), accounts.get(1).getBalance());
        Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2011-04-04"), accounts.get(1).getClosingDate());
    }

    @Test
    public void mapFromAccount() throws ParseException {

        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account());
        accounts.get(0).setIban("PL61109010140000071219812870");
        accounts.get(0).setName("konto 1");
        accounts.get(0).setCurrency("PL");
        accounts.get(0).setBalance(BigDecimal.valueOf(12345.67));
        accounts.get(0).setClosingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2029-10-11"));
        accounts.add(new Account());
        accounts.get(1).setIban("DE6110901014000007");
        accounts.get(1).setName("Main count");
        accounts.get(1).setCurrency("EUR");
        accounts.get(1).setBalance(BigDecimal.valueOf(222.99));
        accounts.get(1).setClosingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2015-11-22"));

        List<String> lines = fileMapper.mapFromAccount(accounts);

        Assert.assertEquals(lines.size(), 15);
        Assert.assertEquals(lines.get(0).trim(), "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        Assert.assertEquals(lines.get(1).trim(), "<accounts>");
        Assert.assertEquals(lines.get(2).trim(), "<account iban=\"PL61109010140000071219812870\">");
        Assert.assertEquals(lines.get(3).trim(), "<name>konto 1</name>");
        Assert.assertEquals(lines.get(4).trim(), "<currency>PL</currency>");
        Assert.assertEquals(lines.get(5).trim(), "<balance>12345.67</balance>");
        Assert.assertEquals(lines.get(6).trim(), "<closingDate>2029-10-11</closingDate>");
        Assert.assertEquals(lines.get(7).trim(), "</account>");
        Assert.assertEquals(lines.get(8).trim(), "<account iban=\"DE6110901014000007\">");
        Assert.assertEquals(lines.get(9).trim(), "<name>Main count</name>");
        Assert.assertEquals(lines.get(10).trim(), "<currency>EUR</currency>");
        Assert.assertEquals(lines.get(11).trim(), "<balance>222.99</balance>");
        Assert.assertEquals(lines.get(12).trim(), "<closingDate>2015-11-22</closingDate>");
        Assert.assertEquals(lines.get(13).trim(), "</account>");
        Assert.assertEquals(lines.get(14).trim(), "</accounts>");
    }
}