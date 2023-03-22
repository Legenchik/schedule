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
        String Group=" ";
        String Monday=" ";
        String Tuesday=" ";
        String Wednesday=" ";
        String Thursday=" ";
        String Friday=" ";
        String Saturday=" ";
        String Week=" ";

        String[] TimeArray = new String[31];
        String[] SubjectArray = new String[31];
        String[] LectionPracticArray = new String[31];
        String[] ClassArray = new String[31];
        String[] DateArray = new String[31];

        int[] SheetAndRow = SearchGroupInSheetsFBK.SheetIndexForGroup(wb,group);
        int nada = SheetAndRow[1];
        int nada1 = nada+1;
        int nada2 = nada+2;
        boolean CheckForSaturday=false;

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
                    if (cell4.getStringCellValue().equals("Субота")==true) {
                        CheckForSaturday = true;
                    }
                }
                if (cell5.getStringCellValue().equals(null)==false) {
                    data += 1;
                    if((data==2)|(data==7)|(data==12)|(data==17)|(data==22)|(data==27)){
                        DateArray[VariableArray] = cell5.getStringCellValue();
                    }
                }
            }
        }
        Group=(SubjectArray[0]+"\n"+"\n");
        Monday=("Понеділок"+" "+DateArray[1]+"\n"+
                TimeArray[1]+" "+SubjectArray[1]+" "+ LectionPracticArray[1]+" "+ClassArray[1]+"\n"+
                TimeArray[2]+" "+SubjectArray[2]+" "+ LectionPracticArray[2]+" "+ClassArray[2]+"\n"+
                TimeArray[3]+" "+SubjectArray[3]+" "+ LectionPracticArray[3]+" "+ClassArray[3]+"\n"+
                TimeArray[4]+" "+SubjectArray[4]+" "+ LectionPracticArray[4]+" "+ClassArray[4]+"\n"+
                TimeArray[5]+" "+SubjectArray[5]+" "+ LectionPracticArray[5]+" "+ClassArray[5]+"\n"+"\n");
        Tuesday=("Вівторок"+" "+DateArray[6]+"\n"+
                TimeArray[1]+" "+SubjectArray[6]+" "+ LectionPracticArray[6]+" "+ClassArray[6]+"\n"+
                TimeArray[2]+" "+SubjectArray[7]+" "+ LectionPracticArray[7]+" "+ClassArray[7]+"\n"+
                TimeArray[3]+" "+SubjectArray[8]+" "+ LectionPracticArray[8]+" "+ClassArray[8]+"\n"+
                TimeArray[4]+" "+SubjectArray[9]+" "+ LectionPracticArray[9]+" "+ClassArray[9]+"\n"+
                TimeArray[5]+" "+SubjectArray[10]+" "+ LectionPracticArray[10]+" "+ClassArray[10]+"\n"+"\n");
        Wednesday=("Середа"+" "+DateArray[11]+"\n"+
                TimeArray[1]+" "+SubjectArray[11]+" "+ LectionPracticArray[11]+" "+ClassArray[11]+"\n"+
                TimeArray[2]+" "+SubjectArray[12]+" "+ LectionPracticArray[12]+" "+ClassArray[12]+"\n"+
                TimeArray[3]+" "+SubjectArray[13]+" "+ LectionPracticArray[13]+" "+ClassArray[13]+"\n"+
                TimeArray[4]+" "+SubjectArray[14]+" "+ LectionPracticArray[14]+" "+ClassArray[14]+"\n"+
                TimeArray[5]+" "+SubjectArray[15]+" "+ LectionPracticArray[15]+" "+ClassArray[15]+"\n"+"\n");
        Thursday=("Четверг"+" "+DateArray[16]+"\n"+
                TimeArray[1]+" "+SubjectArray[16]+" "+ LectionPracticArray[16]+" "+ClassArray[16]+"\n"+
                TimeArray[2]+" "+SubjectArray[17]+" "+ LectionPracticArray[17]+" "+ClassArray[17]+"\n"+
                TimeArray[3]+" "+SubjectArray[18]+" "+ LectionPracticArray[18]+" "+ClassArray[18]+"\n"+
                TimeArray[4]+" "+SubjectArray[19]+" "+ LectionPracticArray[19]+" "+ClassArray[19]+"\n"+
                TimeArray[5]+" "+SubjectArray[20]+" "+ LectionPracticArray[20]+" "+ClassArray[20]+"\n"+"\n");
        Friday=("П'ятниця"+" "+DateArray[21]+"\n"+
                TimeArray[1]+" "+SubjectArray[21]+" "+ LectionPracticArray[21]+" "+ClassArray[21]+"\n"+
                TimeArray[2]+" "+SubjectArray[22]+" "+ LectionPracticArray[22]+" "+ClassArray[22]+"\n"+
                TimeArray[3]+" "+SubjectArray[23]+" "+ LectionPracticArray[23]+" "+ClassArray[23]+"\n"+
                TimeArray[4]+" "+SubjectArray[24]+" "+ LectionPracticArray[24]+" "+ClassArray[24]+"\n"+
                TimeArray[5]+" "+SubjectArray[25]+" "+ LectionPracticArray[25]+" "+ClassArray[25]+"\n"+"\n");
        if(CheckForSaturday==true) {
            Saturday=("Субота"+" "+DateArray[26]+"\n"+
                    TimeArray[1]+" "+SubjectArray[26]+" "+ LectionPracticArray[26]+" "+ClassArray[26]+"\n"+
                    TimeArray[2]+" "+SubjectArray[27]+" "+ LectionPracticArray[27]+" "+ClassArray[27]+"\n"+
                    TimeArray[3]+" "+SubjectArray[28]+" "+ LectionPracticArray[28]+" "+ClassArray[28]+"\n"+
                    TimeArray[4]+" "+SubjectArray[29]+" "+ LectionPracticArray[29]+" "+ClassArray[29]+"\n"+
                    TimeArray[5]+" "+SubjectArray[30]+" "+ LectionPracticArray[30]+" "+ClassArray[30]+"\n"+"\n");

            Week=(Group+" "+Monday+" "+Tuesday+" "+Wednesday+" "+Thursday+" "+Friday+" "+Saturday);
        }else {
            Week=(Group+" "+Monday+" "+Tuesday+" "+Wednesday+" "+Thursday+" "+Friday);
        }
        MessageSender messageSender = new MessageSender(config);
        messageSender.sendMessage(chatId,Week);

    }
}
