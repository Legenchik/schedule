package org.TelegramBot.ScheduleBot.schedule.ScheduleGroupFBK;

import org.TelegramBot.ScheduleBot.schedule.Sender.MessageSender;
import org.TelegramBot.ScheduleBot.schedule.configBot.BotConfig;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OutScheduleThisDayUrlFBK {
    private static String[] Teacher= {"Абрамян","Амбер","Антонович","Артемов","Бабінчук","Базилюк","Безсмертна","Берназюк","Блінов"
            ,"Бліновська","Бортун","Бреус","Букатов","Варенко","Василевич","Вечеря",
            "Виклюк","Волкова","Вольвач М","Вольвач Я","Гаврилець","Гараган","Гіясов","Гнатюк","Горбенко","Гресик","Гуленко","Гункевич","Гурський",
            "Гущак","Данильчук","Дворецький","Девтеров","Дзюба","Діденко","Дмитренко","Довголуцький","Доценко","Другова","Єлеонська","Жовновач","Зайцев","Засадна",
            "Засанський","Захарченко","Іванюк","Ієрусалимов","Катаран","Квас","Клепар","Климчук","Козинець","Колодінська","Концеба","Корнієвський",
            "Корчак","Космідайло","Котвицька","Кохан",
            "Коцун","Кравчук","Крамченко","Кулинич","Кучина","Левченко","Лемеха","Лисенко","Майор","Майстренко",
            "Максимов","Мельник","Мельничук","Милашенко","Мировський","Невзоров","Ніколаєвський","Обідченко","Обозна","Овсієнко","Огнев'юк",
            "Олейнічук","Омельчук","Опольський","Остапчук","Павлік","Палюх","Панасюк","Панченко","Патлаха",
            "Пашорін","Петлін","Петровська",
            "Пилипяк","Поліщук","Польова А","Польова Н","Пономаренко","Пристанська","Процюк","Пустова","Пушенко",
            "Рассомахіна","Рибак","Романенкова","Русінова","Рябокінь","Садула","Свелеба","Святобог",
            "Семенюк","Скляренко","Снігір","Сушинський","Терещук","Тимошенко","Торба","Улічев","Федина","Фімяр","Форостюк","Ханін","Циганов С","Циганова Н","Цімоха",
            "Чен","Чепурний","Шапран","Шинкар","Шиян","Школенко","Шкраб","Шпильова","Шпильовий","Штурма",
            "Шульга","Щербатих","Щур","Ягодзінський","Яковуник","Яровий","Яроміч","Старовойтов"};
    final BotConfig config;
    public OutScheduleThisDayUrlFBK(BotConfig config) {
        this.config = config;
    }

    private  void ScheduleFBKThisDay(long chatId, String group,XSSFWorkbook wb) {
        DateCheckAndCheckSaturday dateCheck = new DateCheckAndCheckSaturday();
        String date = dateCheck.DayCheck();
        int ThisDay = 0;
        int[] nada = new int[2];
        int m = 0;
        int k = wb.getNumberOfSheets();
        System.out.println(k);
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

    private  void outThisDaySTR(XSSFWorkbook wb,int[]nada,int ThisDay,long chatId){

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
        ScheduleFBKThisDayUrl(TimeArray,SubjectArray,LectionPracticArray,ClassArray,DateArray,WeekDay,chatId);
    }

    private  void ScheduleFBKThisDayUrl(String[]TimeArray, String[]SubjectArray, String[]LectionPracticArray,
                                      String[]ClassArray,String[]DateArray,String[]WeekDay,long chatId){

        String[] URL= new String [31];
        String[] Teacher2 = new String[31];
        for(int i =0; i<= 30;i++) {
            String str= SubjectArray[i];
            for(int k=0; k<Teacher.length;k++) {
                if(str!=(null)) {
                    if(str.contains(Teacher[k])== true) {
                        Teacher2[i]=Teacher[k];
                        break;
                    }
                }
            }

        }
        int ko =0;


        String Exelfile = ".\\datafiles\\ScheduleUrl.xlsx";
        FileInputStream intExel = null;
        try {
            intExel = new FileInputStream(Exelfile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(intExel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        XSSFSheet sheet=wb.getSheetAt(0);

        for (int l=0; l<=30;l++) {
            for (int r=0; r<Teacher.length;r++) {
                XSSFRow row = sheet.getRow(r);

                for (int c=0; c<1; c++) {

                    XSSFCell cell = row.getCell(c);
                    String str = cell.getStringCellValue();
                    String sub= "google meet";
                    if(Teacher2[l]!=(" ")) {
                        if (Teacher2[l]!=(null)) {
                            if(str.contains(Teacher2[l])== true) {
                                XSSFRow row3 = sheet.getRow(ko);
                                XSSFCell cell3 = row3.getCell(4);
                                if(sub.contains(ClassArray[l])==false) {
                                    ko = cell.getRowIndex();
                                    XSSFRow row2 = sheet.getRow(ko);
                                    XSSFCell cell2 = row2.getCell(1);
                                    URL[l]=cell2.getStringCellValue();
                                    break;
                                }else {

                                    ko = cell.getRowIndex();
                                    XSSFRow row2 = sheet.getRow(ko);
                                    XSSFCell cell2 = row2.getCell(4);
                                    URL[l]=cell2.getStringCellValue();
                                    break;
                                }

                            }
                            break;



                        }
                    }
                }
            }
        }
        for(int l=0; l<=30;l++) {
            if (URL[l]!=(null)==false) {
                URL[l]="";
            }

        }

        String ThisDayUrl = (WeekDay[0]+" "+DateArray[0]+ "\n" +
                TimeArray[0] + " " + SubjectArray[0] + " " + LectionPracticArray[0] + " " + ClassArray[0] + "\n" +
                URL[0] + "\n" +
                TimeArray[1] + " " + SubjectArray[1] + " " + LectionPracticArray[1] + " " + ClassArray[1] + "\n" +
                URL[1] + "\n" +
                TimeArray[2] + " " + SubjectArray[2] + " " + LectionPracticArray[2] + " " + ClassArray[2] + "\n" +
                URL[2] + "\n" +
                TimeArray[3] + " " + SubjectArray[3] + " " + LectionPracticArray[3] + " " + ClassArray[3] + "\n" +
                URL[3] + "\n" +
                TimeArray[4] + " " + SubjectArray[4] + " " + LectionPracticArray[4] + " " + ClassArray[4] + "\n" +
                URL[4] + "\n" +
                "\n");
        MessageSender messageSender = new MessageSender(config);
        messageSender.sendMessage(chatId,ThisDayUrl);
    }

    public void outScheduleThisDayUrl(long chatId, String group , XSSFWorkbook wb){
        ScheduleFBKThisDay(chatId,group,wb);
    }
}
