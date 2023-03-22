package org.TelegramBot.ScheduleBot.schedule.ScheduleGroupFBK;


import org.TelegramBot.ScheduleBot.schedule.Sender.MessageSender;
import org.TelegramBot.ScheduleBot.schedule.configBot.BotConfig;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class OutScheduleThisDayFBK {
    final BotConfig config;


    public OutScheduleThisDayFBK(BotConfig config) {
        this.config = config;
    }

    public void ScheduleFBKThisDay(long chatId,String group,XSSFWorkbook wb) {
        DateCheckAndCheckSaturday dateCheck = new DateCheckAndCheckSaturday();
        String date = dateCheck.DayCheck();
        int ThisDay = 0;
        int[] nada = new int[2];
        int m = 0;
        int k = wb.getNumberOfSheets();
        for (int l = 1; l < k; l++) {
            XSSFSheet sheet = wb.getSheetAt(l);
            for (int v = 3; v < 29; v = v + 5) {
                XSSFRow row = sheet.getRow(v);
                if (row != null == true) {
                    XSSFCell cell = row.getCell(1);
                    if (cell.getStringCellValue().equals(null) == false) {
                        if (date.equals(cell.getStringCellValue())) {
                            ThisDay = v;
                            XSSFSheet sheet1 = wb.getSheetAt(l);
                            int cols = sheet1.getRow(0).getLastCellNum();
                            for (int r = 0; r < 1; r++) {
                                XSSFRow row1 = sheet1.getRow(2);
                                for (int c = 0; c < cols; c++) {
                                    XSSFCell cell1 = row1.getCell(c);
                                    String str = cell1.getStringCellValue();
                                    int indexM = str.indexOf(group);
                                    if (indexM == -1 == false) {
                                        nada[0] = l;
                                        nada[1] = c;
                                        outThisDaySTR(wb,nada,ThisDay,chatId);
                                        return;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    public void outThisDaySTR(XSSFWorkbook wb,int[]nada,int ThisDay,long chatId){

        String[] TimeArray = new String[31];
        String[] SubjectArray = new String[31];
        String[] LectionPracticArray = new String[31];
        String[] ClassArray = new String[31];
        String[] DateArray = new String[31];
        String[] WeekDay = new String[31];
        int nadatyt=nada[1];
        int nadatyt1=nadatyt+1;
        int nadatyt2=nadatyt+2;
        int rowInt = ThisDay;
        int dataInt=0;
        XSSFSheet sheet = wb.getSheetAt(nada[0]);
        for(int s=0;s<5;s++){
            XSSFRow row = sheet.getRow(rowInt);
            rowInt+=1;
            for (int z=0; z<1; z++) {
                XSSFCell celltyt = row.getCell(nadatyt);
                XSSFCell celltyt1 = row.getCell(nadatyt1);
                XSSFCell celltyt2 = row.getCell(nadatyt2);
                XSSFCell celltyt3 = row.getCell(2);
                XSSFCell celltyt4 = row.getCell(0);
                XSSFCell celltyt5 = row.getCell(1);
                XSSFCell celltyt6 = row.getCell(0);
                if (celltyt3.getStringCellValue().equals(null)==false) {
                    //System.out.println(celltyt3.getStringCellValue()+celltyt3.getAddress());
                    TimeArray[s] = celltyt3.getStringCellValue();
                }
                if (celltyt.getStringCellValue().equals(null)==false) {
                    //System.out.println(celltyt.getStringCellValue());
                    SubjectArray[s] = celltyt.getStringCellValue();
                }
                if (celltyt1.getStringCellValue().equals(null)==false) {
                    //System.out.println(celltyt1.getStringCellValue());
                    LectionPracticArray[s] = celltyt1.getStringCellValue();
                }
                if (celltyt2.getStringCellValue().equals(null)==false) {
                    //System.out.println(celltyt2.getStringCellValue());
                    ClassArray[s] = celltyt2.getStringCellValue();
                }
                if (celltyt5.getStringCellValue().equals(null)==false) {
                    dataInt += 1;
                    if(dataInt==1){
                        DateArray[z] = celltyt5.getStringCellValue();
                        WeekDay[z] = celltyt6.getStringCellValue();
                        //System.out.println(celltyt5.getStringCellValue());
                    }
                }
            }
        }
        String ThisDaySTR;
        ThisDaySTR=(WeekDay[0]+" "+DateArray[0]+"\n"+
                TimeArray[0]+" "+SubjectArray[0]+" "+ LectionPracticArray[0]+" "+ClassArray[0]+"\n"+
                TimeArray[1]+" "+SubjectArray[1]+" "+ LectionPracticArray[1]+" "+ClassArray[1]+"\n"+
                TimeArray[2]+" "+SubjectArray[2]+" "+ LectionPracticArray[2]+" "+ClassArray[2]+"\n"+
                TimeArray[3]+" "+SubjectArray[3]+" "+ LectionPracticArray[3]+" "+ClassArray[3]+"\n"+
                TimeArray[4]+" "+SubjectArray[4]+" "+ LectionPracticArray[4]+" "+ClassArray[4]+"\n"+"\n");
        MessageSender messageSender = new MessageSender(config);
        messageSender.sendMessage(chatId,ThisDaySTR);
    }
}
