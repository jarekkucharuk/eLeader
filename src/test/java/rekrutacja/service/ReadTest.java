package rekrutacja.service;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ReadTest {

    @Test
    public void readFile() {

        Read read = new Read();
        List<String> lines = read.readFile();

        Assert.assertEquals(51, lines.size());
        Assert.assertEquals("<?xml version = \"1.0\"?>", lines.get(0).trim());
        Assert.assertEquals("", lines.get(43));
        Assert.assertEquals("<account iban=\"PL61109010140000071219812874\">", lines.get(37).trim());
        Assert.assertEquals("</accounts>", lines.get(50).trim());
    }
}