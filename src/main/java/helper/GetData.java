package helper;

import entity.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class GetData {

    public List<String> listFilesForFolder(File pathToFolder) {
        List<String> listFileName = new ArrayList<>();

        for (File fileEntry : pathToFolder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                listFileName.add(fileEntry.getName());
            }
        }

        return listFileName;

    }

    public Set<User> parsExelData(String pathToExelFile) throws IOException {
        Set<User> list = new HashSet<>();

        // Read XSL file
        FileInputStream inputStream = new FileInputStream(new File(pathToExelFile));

        // Get the workbook instance for XLS file
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        // Get first sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // Get iterator to all cells of current row
            User user = new User(String.valueOf(row.getCell(0)), String.valueOf(row.getCell(1)), String.valueOf(row.getCell(2)),
                                 String.valueOf(row.getCell(3)));

            list.add(user);

        }

        User deleted = new User("ФИО", "Адрес", "Номер телефона", "З\\П");

        list.remove(deleted);
        return list;
    }


}
