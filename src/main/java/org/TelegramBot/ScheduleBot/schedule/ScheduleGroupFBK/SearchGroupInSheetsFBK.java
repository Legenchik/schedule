package org.TelegramBot.ScheduleBot.schedule.ScheduleGroupFBK;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class SearchGroupInSheetsFBK {
    public static int[] SheetIndexForGroup(XSSFWorkbook wb, String group){
        DateCheckAndCheckSaturday dateCheckAndCheckSaturday = new DateCheckAndCheckSaturday();
        String data = dateCheckAndCheckSaturday.DayCheck();
        int[] nada = new int[2];
        int m=0;
        int k = wb.getNumberOfSheets();
        for(int l=1;l<k;l++){
            XSSFSheet sheet = wb.getSheetAt(l);
            for(int v=3;v<63;v=v+10){
                XSSFRow row = sheet.getRow(v);
                if(row!=null==true){
                    XSSFCell cell = row.getCell(1);
                    if(cell.getStringCellValue().equals(null)==false){
                        if(data.equals(cell.getStringCellValue())){
                            XSSFSheet sheet1 = wb.getSheetAt(l);
                            int cols = sheet1.getRow(0).getLastCellNum();
                            for (int r=0; r<1;r++) {
                                XSSFRow row1 = sheet1.getRow(2);
                                for (int c = 0; c < cols; c++) {
                                    XSSFCell cell1 = row1.getCell(c);
                                    String str = cell1.getStringCellValue();
                                    int indexM = str.indexOf(group);
                                    if (indexM == -1 == false) {
                                        nada[0]=l;
                                        nada[1]=c;
                                        System.out.println(m);
                                        return nada;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        return nada;
    }

}
