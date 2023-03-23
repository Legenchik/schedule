package org.TelegramBot.ScheduleBot.schedule.TelegramBot;


import lombok.Getter;
import lombok.Setter;
import org.TelegramBot.ScheduleBot.schedule.DateBase.Group;
import org.TelegramBot.ScheduleBot.schedule.DateBase.GroupRepository;
import org.TelegramBot.ScheduleBot.schedule.DateBase.User;
import org.TelegramBot.ScheduleBot.schedule.DateBase.UserRepository;
import org.TelegramBot.ScheduleBot.schedule.DownloadSchedule.DownloadSchedulesFromUrl;
import org.TelegramBot.ScheduleBot.schedule.ScheduleGroupFBK.OutScheduleFBK;
import org.TelegramBot.ScheduleBot.schedule.ScheduleGroupFBK.OutScheduleThisDayFBK;
import org.TelegramBot.ScheduleBot.schedule.ScheduleGroupFBK.OutScheduleThisDayUrlFBK;
import org.TelegramBot.ScheduleBot.schedule.Sender.MessageSender;
import org.TelegramBot.ScheduleBot.schedule.UniversityFaculty.ChoseGroup;
import org.TelegramBot.ScheduleBot.schedule.UniversityFaculty.FBK.FBKChouseGroup;
import org.TelegramBot.ScheduleBot.schedule.UniversityFaculty.SortCallBackData;
import org.TelegramBot.ScheduleBot.schedule.configBot.BotConfig;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



@Setter
@Component
public class ScheduleBot extends TelegramLongPollingBot {



    public  XSSFWorkbook wbFBK =new XSSFWorkbook();

    public void setWbFBK(XSSFWorkbook wbFBK) {
        this.wbFBK = wbFBK;
    }

    public XSSFWorkbook getWbFBK() {
        return wbFBK;
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;

    final BotConfig config;

    public ScheduleBot(BotConfig config) {
        this.config = config;
    }

    @Override

    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        ChoseGroup choseGroup = new ChoseGroup(config);
        FBKChouseGroup fbkChouseGroup = new FBKChouseGroup(config);
        Message inconsole = update.getMessage();
        System.out.println(inconsole);
        //System.out.println(inconsole.getFrom().getId());
        DownloadSchedulesFromUrl downloadSchedulesFromUrl = new DownloadSchedulesFromUrl(config);
        if(update.hasMessage()&&update.getMessage().hasText()){
            Message message =update.getMessage();
            long chatId = message.getChatId();
            String messageText = message.getText();
            long IdAdministratora =941240371;
            switch(messageText){
                case"/start@proverma_my_first_bot","/start":
                    registerUser(message);
                    Head(message);
                    break;
                case"/updateschedule":
                    if(IdAdministratora==message.getFrom().getId()){
                        downloadSchedulesFromUrl.downloadSheet();
                        sendMessage(chatId,"Розклад завантажено");
                    }else{
                        sendMessage(chatId,"Ви не адміністратор");
                        sendMessage(chatId,message.getFrom().getId().toString());
                    }

                    break;
                case"Тиждень":
                    SearchGroupBD(chatId,"week");
                    break;
                case "День":
                    SearchGroupBD(chatId,"day");
                    break;
                case "День з посил.":
                    SearchGroupBD(chatId,"dayurl");
                    break;
                case "Вибрати/змінити групу":
                    choseGroup.DaytimeFormOrExtramural(chatId);
                    break;
                case "Отримання перед парой":
                    SendscheduleEverydayButton(chatId);
                    break;
                case "Моя група":
                    SearchGroupBD(chatId,"my group");;
                    break;
                case"/in":
                    choseGroup.DaytimeFormOrExtramural(chatId);
                    break;
                case"/testweek":
                    SearchGroupBD(chatId,"week");
                    break;
                case"/testday":
                    SearchGroupBD(chatId,"day");
                    break;
                case"/testdayurl":
                    SearchGroupBD(chatId,"dayurl");
                    break;
                case"/testhead":
                    Head(message);
                    break;
                default:
                sendMessage(chatId,"Невідома команда");
            }


        }else if(update.hasCallbackQuery()){
            String callbackDataSTR = update.getCallbackQuery().getData().toString();
            Message callbackData = update.getCallbackQuery().getMessage();
            System.out.println(callbackData);
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            System.out.println(chatId);
            switch(callbackDataSTR){
                case"YESA":
                    everyDaySend(chatId,"Дякую");
                    deleteMessage(chatId,messageId);
                    break;
                case"NOA":
                    CancelSendEveryDay(chatId,"Дякую");
                    deleteMessage(chatId,messageId);
                    break;
                case"DAYTIME":
                    choseGroup.Faculty(chatId,messageId);
                    break;
                case"FBK":
                    fbkChouseGroup.courseFBK(chatId,messageId);
                    break;
                case"FBK1":
                    fbkChouseGroup.groupCourseFBK1(chatId,messageId);
                    break;
                case"FBK2":
                    fbkChouseGroup.groupCourseFBK2(chatId,messageId);
                    break;
                case"FBK3":
                    fbkChouseGroup.groupCourseFBK3(chatId,messageId);
                    break;
                case"FBK4":
                    fbkChouseGroup.groupCourseFBK4(chatId,messageId);
                    break;
                default:
                    prepairGroup(callbackData,callbackDataSTR);
            }
        }
    }
    private void SearchGroupBD(long chatId,String forSend){
        var group = groupRepository.findAll();
        String faculty;
        String Sgroup;
        for(Group g:group){
            if(chatId==g.getChatId()){
                faculty=g.getFaculty();
                Sgroup=g.getNumberGroup();
                SendSchedule(chatId,faculty,Sgroup,forSend);
            }
        }
    }

    public void SendSchedule(long chatId,String faculty, String group,String forSend) {
        OutScheduleFBK outScheduleFBK = new OutScheduleFBK(config);
        OutScheduleThisDayFBK outScheduleThisDayFBK = new OutScheduleThisDayFBK(config);
        OutScheduleThisDayUrlFBK outScheduleThisDayUrlFBK = new OutScheduleThisDayUrlFBK(config);
        switch(forSend){
            case"week":
                switch (faculty) {
                    case "FBK":
                        outScheduleFBK.ScheduleFBK(chatId, group, wbFBK);
                        break;
                }
                break;
            case"day":
                switch (faculty) {
                    case "FBK":
                        outScheduleThisDayFBK.ScheduleFBKThisDay(chatId, group, wbFBK);
                        break;
                }
                break;
            case"dayurl":
                switch (faculty) {
                    case "FBK":
                        System.out.println(wbFBK.getNumberOfSheets());
                       outScheduleThisDayUrlFBK.outScheduleThisDayUrl(chatId, group, wbFBK);
                        break;
                }
                break;
            case"my group":
                sendMessage(chatId,group);
                break;
        }


    }

    private void everyDaySend(long chatId, String text) {
        var group =groupRepository.findAll();
        for(Group g:group){
            if(chatId==g.getChatId()){
                g.setScheduleSend(true);
                groupRepository.save(g);
            }
        }
        sendMessage(chatId,text);
    }

    private void CancelSendEveryDay(long chatId, String text) {
        var group =groupRepository.findAll();
        for(Group g:group){
            if(chatId==g.getChatId()){
                g.setScheduleSend(false);
                groupRepository.save(g);
            }
        }
        sendMessage(chatId,text);
    }



    public void SendscheduleEveryday(long chatId, long messageId) {
        String sendtext = "Бажаєте отримувати розклад перед початком першої пари";
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(chatId);
        editMessage.setText(sendtext);
        editMessage.setMessageId((int) messageId);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var yes_button = new InlineKeyboardButton();
        var no_button = new InlineKeyboardButton();
        yes_button.setText("Так");
        yes_button.setCallbackData("YESA");
        no_button.setText("Ні");
        no_button.setCallbackData("NOA");
        rowInLine.add(yes_button);
        rowInLine.add(no_button);
        rowsInLine.add(rowInLine);
        markupInLine.setKeyboard(rowsInLine);
        editMessage.setReplyMarkup(markupInLine);
        ExucuteEditMessage(editMessage);
    }

    public void SendscheduleEverydayButton(long chatId) {
        String sendtext = "Бажаєте отримувати розклад перед початком першої пари";
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sendtext);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var yes_button = new InlineKeyboardButton();
        var no_button = new InlineKeyboardButton();
        yes_button.setText("Так");
        yes_button.setCallbackData("YESA");
        no_button.setText("Ні");
        no_button.setCallbackData("NOA");
        rowInLine.add(yes_button);
        rowInLine.add(no_button);
        rowsInLine.add(rowInLine);
        markupInLine.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(markupInLine);
        ExucuteMessage(sendMessage);
    }

    private void sendoutGroup(Message msg) {
        var group = groupRepository.findAll();
        for(Group ad: group) {
            sendMessageGroup(ad.getChatId(), ad.getNumberGroup());
        }
    }
    private void sendMessageGroup(long msg, String text)  {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(String.valueOf(msg));
        try {
            execute (sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    private void sendMessage(long msg,String text)  {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(msg);
        try {
            execute (sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepairGroup(Message msg,String callbackDataSTR){
        SortCallBackData sortCallBackData = new SortCallBackData();
        String[] groupAndFaculty=sortCallBackData.SortCallBackQuery(callbackDataSTR);
        registerGroup(msg,groupAndFaculty[0],groupAndFaculty[1]);
        SendscheduleEveryday(msg.getChatId(),msg.getMessageId());
    }
    private void registerUser(Message msg){
        if (userRepository.findById(msg.getChatId()).isEmpty()) {
            var chatID = msg.getChatId();
            var chat = msg.getChat();
            User user = new User();
            user.setChatId(msg.getChatId());
            user.setGrouptype(chat.getType());
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            System.out.println(chat);
        }
    }

    private void registerGroup(Message msg,String text,String faculty){

        var chatID = msg.getChatId();
        var chat = msg.getChat();
        Group group = new Group();
        group.setChatId(chatID);
        group.setGrouptype(chat.getType());
        group.setNumberGroup(text);
        group.setFaculty(faculty);
        group.setRegisteredAt(new Timestamp(System.currentTimeMillis()));
        groupRepository.save(group);
        sendMessage(chatID,"Запам'ятав");
    }

    public void ExucuteMessage(SendMessage msg){
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public void ExucuteEditMessage(EditMessageText msg){
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void Head(Message message) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup reply = new ReplyKeyboardMarkup();
        reply.setResizeKeyboard(true);
        List<KeyboardRow> keyRowList = new ArrayList<>();
        KeyboardRow keyRow1 = new KeyboardRow();
        keyRow1.add("Тиждень");
        keyRow1.add("День");
        keyRow1.add("День з посил.");
        keyRowList.add(keyRow1);

        KeyboardRow keyRow2 = new KeyboardRow();
        keyRow2.add("Вибрати/змінити групу");
        keyRow2.add("Отримання перед парой");
        keyRowList.add(keyRow2);

        KeyboardRow keyRow3 = new KeyboardRow();
        keyRow3.add("Моя група");
        keyRowList.add(keyRow3);

        reply.setKeyboard(keyRowList);
        sendMessage.setReplyMarkup(reply);
        sendMessage.setText("Виберіть");
        sendMessage.setChatId(message.getChatId());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e1) {
            e1.printStackTrace();
        }
    }

    private void deleteMessage(long chatId, long msgId){
        DeleteMessage deleteMessage = new DeleteMessage(String.valueOf(chatId),(int)msgId);
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron = "1 55 8 * * *")
    private void send(){
        long chatId = config.getOwnerId();
        MessageSender sendM = new MessageSender(config);
        System.out.println(wbFBK.getNumberOfSheets());
        var group = groupRepository.findAll();
        for(Group g:group){
            if(g.isScheduleSend()==true){
                SendSchedule(g.getChatId(),g.getFaculty(),g.getNumberGroup(),"dayurl");
            }
        }

    }
}
