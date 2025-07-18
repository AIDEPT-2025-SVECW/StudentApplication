package com.bvraju.aidept.StudentApplication.repository.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.bvraju.aidept.StudentApplication.model.Student;
import com.bvraju.aidept.StudentApplication.repository.IStudentRepository;

import jakarta.annotation.PostConstruct;

//commented to remove the bean registration and loading activities
@Repository
public class StudentExcelRepositoryImpl implements IStudentRepository {

    @Value("${students.excel.file.location}")
    private String filePath;

    @Value("${students.excel.sheet.name}")
    private String sheetName;

    @Value("${college.name}")
    private String collegeName;

    @Value("${student.dept}")
    private String dept;

    private Map<String, Student> studentDetails;

    @PostConstruct
    private void init() {
        try {
            List<Student> students = readStudentsFromExcel();
            studentDetails = students.stream()
                    .collect(Collectors.toMap(
                            Student::getRegId, // Key mapper: get registration ID
                            student -> student // Value mapper: the student object itself
                    ));
        } catch (Exception ex) {
            System.out.println("Unable to load students from excel repository");
        }
    }

    @Override
    public List<Student> findAll() {

        List<Student> students = studentDetails.values()
                .stream()
                .collect(Collectors.toList());
        return students;
    }

    @Override
    public Student findByRegId(String regId) {
        Student requiredStudent = studentDetails.get(regId);
        return requiredStudent;
    }

    /**
     * Read student details from Excel file
     * 
     * @param filePath  Path to the Excel file
     * @param sheetName Name of the sheet to read from (optional, uses first sheet
     *                  if null)
     * @return List of students
     * @throws IOException If file cannot be read
     */
    public List<Student> readStudentsFromExcel() throws IOException {
        List<Student> students = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = (sheetName != null) ? workbook.getSheet(sheetName) : workbook.getSheetAt(0);

            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            System.out.println("Reading students from sheet: " + sheet.getSheetName());

            // Skip header row (assuming first row contains headers)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)
                    continue;

                Cell regIdCell = row.getCell(1);
                Cell nameCell = row.getCell(2);
                Cell sectionCell = row.getCell(4);
                String section = "N/A";
                if (sectionCell != null) {
                    section = getCellValueAsString(sectionCell);
                }

                if (regIdCell != null && nameCell != null) {
                    String id = getCellValueAsString(regIdCell);
                    String name = getCellValueAsString(nameCell);

                    if (!id.trim().isEmpty() && !name.trim().isEmpty()) {
                        Student currStudent = new Student(name.trim(), id.trim(), dept);
                        currStudent.setCollege(collegeName);
                        currStudent.setSec(section);
                        students.add(currStudent);
                    }
                }
            }
        }

        System.out.println("Read {} students from Excel file" + students.size());
        return students;
    }

    /**
     * Convert cell value to string
     */
    private String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Handle numeric values (convert to string without decimal if it's a whole
                    // number)
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == Math.floor(numericValue)) {
                        return String.valueOf((long) numericValue);
                    } else {
                        return String.valueOf(numericValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    @Override
    public int deleteByRegId(String regId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public int saveOrUpdate(Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
