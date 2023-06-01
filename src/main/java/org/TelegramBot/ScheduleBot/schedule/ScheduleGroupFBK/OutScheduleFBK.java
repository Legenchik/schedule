package org.TelegramBot.ScheduleBot.schedule.ScheduleGroupFBK;

import org.TelegramBot.ScheduleBot.schedule.Sender.MessageSender;
import org.TelegramBot.ScheduleBot.schedule.configBot.BotConfig;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class OutScheduleFBK {
    final BotConfig config;


    public OutScheduleFBK(BotConfig config) {
        this.config = config;
    }

    public  void ScheduleFBK(long chatId,String group,XSSFWorkbook wb) {
        SearchGroupInSheetsFBK search = new SearchGroupInSheetsFBK();
        String Group=" ";
        String Week=" ";


        String[] TimeArray = new String[70];
        String[] SubjectArray = new String[70];
        String[] LectionPracticArray = new String[70];
        String[] ClassArray = new String[70];
        String[] DateArray = new String[70];
        String[] DayName=new String[70];

        int[] SheetAndRow = search.SheetIndexForGroup(wb,group);
        int nada = SheetAndRow[1];
        int nada1 = nada+1;
        int nada2 = nada+2;

        boolean CheckForSaturday=false;//TODO доробить або удалить

        int VariableArray = -1;
        XSSFSheet sheet = wb.getSheetAt(SheetAndRow[0]);
        int rows1 = sheet.getLastRowNum();
        int data=0;
        for (int r=2; r<=rows1;r++) {
            XSSFRow row = sheet.getRow(r);
            VariableArray++;
            for (int c=0; c<1; c++) {
                XSSFCell cell = row.getCell(nada);
                XSSFCell cell1 = row.getCell(nada1);
                XSSFCell cell2 = row.getCell(nada2);
                XSSFCell cell3 = row.getCell(2);
                XSSFCell cell4 = row.getCell(0);
                XSSFCell cell5 = row.getCell(1);

                if (cell3.getStringCellValue().equals(null)==false) {
                    TimeArray[VariableArray] = cell3.getStringCellValue();
                }
                if (cell.getStringCellValue().equals(null)==false) {
                    SubjectArray[VariableArray] = cell.getStringCellValue();
                }
                if (cell1.getStringCellValue().equals(null)==false) {
                    LectionPracticArray[VariableArray] = cell1.getStringCellValue();
                }
                if (cell2.getStringCellValue().equals(null)==false) {
                    ClassArray[VariableArray] = cell2.getStringCellValue();
                }
                if (cell4.getStringCellValue().equals(null)==false) {
                    DayName[VariableArray]=cell4.getStringCellValue();
                    if (cell4.getStringCellValue().equals("Субота")==true) {//TODO доробить або удалить
                        CheckForSaturday = true;
                    }
                }
                if (cell5.getStringCellValue().equals(null)==false) {
                    data += 1;
                    if((data==2)|(data==12)|(data==22)|(data==32)|(data==42)|(data==52)){
                        DateArray[VariableArray] = cell5.getStringCellValue();
                    }
                }
            }
        }

        Group=(SubjectArray[0]+"\n"+"\n");
        Week=Week+Group;
        int n=1;
        for (int i =1; i<=50;i=i+10){
            Week=(Week+"\n"+DayName[i]+" "+DateArray[i]);
            for(int b=0;b<=5;){
               Week=(Week+"\n"+TimeArray[n]+" "+SubjectArray[n]+" "+ LectionPracticArray[n]+" "+ClassArray[n]);
               n++;
               b++;
            }
            n=n+4;
        }
        MessageSender messageSender = new MessageSender(config);
        messageSender.sendMessage(chatId,Week);
    }
}
