package rekrutacja;

import rekrutacja.domain.Account;
import rekrutacja.mapper.FileMapper;
import rekrutacja.service.Filter;
import rekrutacja.service.Read;
import rekrutacja.service.Sort;
import rekrutacja.service.Write;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException {

        Read read = new Read();
        List<String> lines = read.readFile();

        FileMapper fileMapper = new FileMapper();
        List<Account> accountsMapped = fileMapper.mapToAccount(lines);

        Filter filter = new Filter();
        List<Account> accountsFiltered = filter.filterData(accountsMapped);

        Sort sort = new Sort();
        List<Account> accountsToSave = sort.sortByNameAsc(accountsFiltered);

        List<String> linesToSave = fileMapper.mapFromAccount(accountsToSave);

        Write write = new Write();
        write.writeFile(linesToSave);
    }
}
