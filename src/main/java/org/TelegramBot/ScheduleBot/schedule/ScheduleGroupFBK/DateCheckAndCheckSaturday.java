package org.TelegramBot.ScheduleBot.schedule.ScheduleGroupFBK;

import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCheckAndCheckSaturday {
    public String DayCheck(){
        Calendar instance = Calendar.getInstance();
        Date date = instance.getTime();
        String patterns = "EEEE";
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        String data = new SimpleDateFormat(patterns).format(date);
        String dataTime = dateFormat.format(date);
        int dataTimeint = Integer.parseInt(dataTime);
        switch(data){
            case"понеділок", "вівторок", "середа", "четвер":
                if(dataTimeint>=15){
                    instance.setTime(date);
                    instance.add(Calendar.DAY_OF_MONTH, 1);
                }
                break;
            case"пʼятниця":
                if(dataTimeint>=15){
                    instance.setTime(date);
                    instance.add(Calendar.DAY_OF_MONTH, 3);
                }
                break;
            case"субота":
                instance.setTime(date);
                instance.add(Calendar.DAY_OF_MONTH, 2);
                break;
            case"неділя":
                instance.setTime(date);
                instance.add(Calendar.DAY_OF_MONTH, 1);
                break;
            default:

        }
        Date newDate = instance.getTime();
        String newpatterns = "dd MMMMM yyyy";
        String newData = new SimpleDateFormat(newpatterns).format(newDate)+" р.";
        return newData;
    }

   /* @SneakyThrows
    public static boolean checkSartuday(){
        boolean check=false;
        String group="024/3";
        String Exelfile = ".\\datafiles\\Schedule.xlsx";
        FileInputStream fs = new FileInputStream(Exelfile);
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        XSSFSheet sheet = wb.getSheetAt(1);
        XSSFRow row = sheet.getRow(28);
        XSSFCell cell = row.getCell(0);
        if(cell.getStringCellValue().equals("Субота")){
            check=true;
        }

        return check;
    }НЕ ВИКОРИСТОВУЮ*///TODO або видалити або доробити
}
