package com.elvis.swingapp.librarysystem.utils;

import com.elvis.swingapp.librarysystem.DAO.BookCategoryDAO;
import com.elvis.swingapp.librarysystem.DAO.BookDAO;
import com.elvis.swingapp.librarysystem.model.Book;
import java.io.File;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLSXReader{
    
    private static BookCategoryDAO bookCategoryDAO = new BookCategoryDAO();
    private static BookDAO bookDAO = new BookDAO();
    
    public static Set<Book> ReadXLSX(File file){
        Set<Book> books = new HashSet<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = (Row) sheet.getRow(i);
                Book book = new Book();
                if(row.getCell(0) == null){book.setBookId(null);}
                else book.setBookId(row.getCell(0).getStringCellValue());
                
                if(row.getCell(1) == null){book.setTitle(null);}
                else book.setTitle(row.getCell(1).getStringCellValue());
                
                if(row.getCell(2) == null){book.setPublishingHouse(null);}
                else book.setPublishingHouse(row.getCell(2).getStringCellValue());
                
                if(row.getCell(3) == null){book.setDateofPublication(null);}
                else book.setDateofPublication(new Date(row.getCell(3).getDateCellValue().getTime()));
                
                if(row.getCell(4) == null){book.setAuthor(null);}
                else book.setAuthor(row.getCell(4).getStringCellValue());
                
                if(row.getCell(5) == null){book.setPages(0);}
                else book.setPages((int) row.getCell(5).getNumericCellValue());
                
                if(row.getCell(6) == null){book.setCategory(null);}
                else book.setCategory(bookCategoryDAO.findCategoryById(row.getCell(6).getStringCellValue()));
                               
                books.add(book);
            }
            bookDAO.saveBatch(books);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return books;
    }
}
