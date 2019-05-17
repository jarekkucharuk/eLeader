package rekrutacja.service;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class WriteTest {

    @Test
    public void writeFile() throws IOException {

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

        Write writeMock = mock(Write.class);
        writeMock.writeFile(lines);

        verify(writeMock, Mockito.times(1)).writeFile(lines);
        verify(writeMock).writeFile(lines);
    }
}