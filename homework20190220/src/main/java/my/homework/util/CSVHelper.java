package my.homework.util;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class CSVHelper {
    public static <T> List<T> readFromCSV(Resource resource, Class<T> clazz) throws IOException {
        Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();
        ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
        ms.setType(clazz);
        CsvToBean cb = new CsvToBeanBuilder(reader)
                .withType(clazz)
                .withMappingStrategy(ms)
                .build();
        return cb.parse();
    }
}
