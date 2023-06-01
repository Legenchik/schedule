package org.TelegramBot.ScheduleBot.schedule.UniversityFaculty.FBK;

import org.TelegramBot.ScheduleBot.schedule.Sender.EditMessageSender;
import org.TelegramBot.ScheduleBot.schedule.configBot.BotConfig;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class FBKChouseGroup {
    final BotConfig config;
    public FBKChouseGroup(BotConfig config) {
        this.config = config;
        editMessageSender = new EditMessageSender(this.config);
    }
    EditMessageSender editMessageSender;
    public void courseFBK(long chatId, long messageId) {
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(chatId);
        editMessage.setText("Виберіть курс");
        editMessage.setMessageId((int) messageId);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var fbk1 = new InlineKeyboardButton();
        var fbk2 = new InlineKeyboardButton();

        fbk1.setText("1 курс");
        fbk1.setCallbackData("FBK1");

        fbk2.setText("2 курс");
        fbk2.setCallbackData("FBK2");

        rowInLine.add(fbk1);
        rowInLine.add(fbk2);

        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        var fbk3 = new InlineKeyboardButton();
        var fbk4 = new InlineKeyboardButton();

        fbk3.setText("3 курс");
        fbk3.setCallbackData("FBK3");

        fbk4.setText("4 курс");
        fbk4.setCallbackData("FBK4");

        rowInLine2.add(fbk3);
        rowInLine2.add(fbk4);

        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine2);

        markupInLine.setKeyboard(rowsInLine);
        editMessage.setReplyMarkup(markupInLine);
        editMessageSender.editMessageSender(editMessage);
    }
    public void groupCourseFBK1(long chatId, long messageId) {
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(chatId);
        editMessage.setText("Виберіть групу");
        editMessage.setMessageId((int) messageId);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var fbk011 = new InlineKeyboardButton();
        var fbk012 = new InlineKeyboardButton();
        var fbk013 = new InlineKeyboardButton();

        fbk011.setText("011");
        fbk011.setCallbackData("FBK011");
        fbk012.setText("012");
        fbk012.setCallbackData("FBK012");
        fbk013.setText("013");
        fbk013.setCallbackData("FBK013");

        rowInLine.add(fbk011);
        rowInLine.add(fbk012);
        rowInLine.add(fbk013);

        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        var fbk014 = new InlineKeyboardButton();
        var fbk015 = new InlineKeyboardButton();

        fbk014.setText("014");
        fbk014.setCallbackData("FBK014");
        fbk015.setText("015");
        fbk015.setCallbackData("FBK015");

        rowInLine2.add(fbk014);
        rowInLine2.add(fbk015);

        List<InlineKeyboardButton> rowInLine3 = new ArrayList<>();
        var back = new InlineKeyboardButton();

        back.setText("Назад");
        back.setCallbackData("FBK");

        rowInLine3.add(back);

        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine2);
        rowsInLine.add(rowInLine3);

        markupInLine.setKeyboard(rowsInLine);
        editMessage.setReplyMarkup(markupInLine);
        editMessageSender.editMessageSender(editMessage);
    }
    public void groupCourseFBK2(long chatId, long messageId) {
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(chatId);
        editMessage.setText("Виберіть групу");
        editMessage.setMessageId((int) messageId);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var fbk021 = new InlineKeyboardButton();
        var fbk022 = new InlineKeyboardButton();
        var fbk023 = new InlineKeyboardButton();

        fbk021.setText("021");
        fbk021.setCallbackData("FBK021");
        fbk022.setText("022");
        fbk022.setCallbackData("FBK022");
        fbk023.setText("023");
        fbk023.setCallbackData("FBK023");

        rowInLine.add(fbk021);
        rowInLine.add(fbk022);
        rowInLine.add(fbk023);

        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        var fbk024_1 = new InlineKeyboardButton();
        var fbk024_2 = new InlineKeyboardButton();
        var fbk024_3 = new InlineKeyboardButton();

        fbk024_1.setText("024/1");
        fbk024_1.setCallbackData("FBK024_1");
        fbk024_2.setText("024/2");
        fbk024_2.setCallbackData("FBK024_2");
        fbk024_3.setText("024/3");
        fbk024_3.setCallbackData("FBK024_3");

        rowInLine2.add(fbk024_1);
        rowInLine2.add(fbk024_2);
        rowInLine2.add(fbk024_3);

        List<InlineKeyboardButton> rowInLine3 = new ArrayList<>();
        var fbk026 = new InlineKeyboardButton();
        var fbk027 = new InlineKeyboardButton();
        var fbk028 = new InlineKeyboardButton();

        fbk026.setText("026");
        fbk026.setCallbackData("FBK026");
        fbk027.setText("027");
        fbk027.setCallbackData("FBK027");
        fbk028.setText("028");
        fbk028.setCallbackData("FBK028");

        rowInLine3.add(fbk026);
        rowInLine3.add(fbk027);
        rowInLine3.add(fbk028);

        List<InlineKeyboardButton> rowInLine4 = new ArrayList<>();
        var fbk029 = new InlineKeyboardButton();

        fbk029.setText("029");
        fbk029.setCallbackData("FBK029");

        rowInLine4.add(fbk029);

        List<InlineKeyboardButton> rowInLine5 = new ArrayList<>();
        var back = new InlineKeyboardButton();

        back.setText("Назад");
        back.setCallbackData("FBK");

        rowInLine5.add(back);

        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine2);
        rowsInLine.add(rowInLine3);
        rowsInLine.add(rowInLine4);
        rowsInLine.add(rowInLine5);

        markupInLine.setKeyboard(rowsInLine);
        editMessage.setReplyMarkup(markupInLine);
        editMessageSender.editMessageSender(editMessage);
    }

    public void groupCourseFBK3(long chatId, long messageId) {
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(chatId);
        editMessage.setText("Виберіть групу");
        editMessage.setMessageId((int) messageId);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var fbk032 = new InlineKeyboardButton();
        var fbk034_1 = new InlineKeyboardButton();
        var fbk034_2 = new InlineKeyboardButton();

        fbk032.setText("032");
        fbk032.setCallbackData("FBK032");
        fbk034_1.setText("034/1");
        fbk034_1.setCallbackData("FBK034_1");
        fbk034_2.setText("034/2");
        fbk034_2.setCallbackData("FBK034_2");

        rowInLine.add(fbk032);
        rowInLine.add(fbk034_1);
        rowInLine.add(fbk034_2);

        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        var fbk038 = new InlineKeyboardButton();
        var fbk039 = new InlineKeyboardButton();

        fbk038.setText("038");
        fbk038.setCallbackData("FBK038");
        fbk039.setText("039");
        fbk039.setCallbackData("FBK039");

        rowInLine2.add(fbk038);
        rowInLine2.add(fbk039);

        List<InlineKeyboardButton> rowInLine3 = new ArrayList<>();
        var back = new InlineKeyboardButton();

        back.setText("Назад");
        back.setCallbackData("FBK");

        rowInLine3.add(back);

        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine2);
        rowsInLine.add(rowInLine3);

        markupInLine.setKeyboard(rowsInLine);
        editMessage.setReplyMarkup(markupInLine);
        editMessageSender.editMessageSender(editMessage);
    }

    public void groupCourseFBK4(long chatId, long messageId) {
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(chatId);
        editMessage.setText("Виберіть групу");
        editMessage.setMessageId((int) messageId);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var fbk044 = new InlineKeyboardButton();
        var fbk048 = new InlineKeyboardButton();
        var fbk049 = new InlineKeyboardButton();

        fbk044.setText("044");
        fbk044.setCallbackData("FBK044");
        fbk048.setText("048");
        fbk048.setCallbackData("FBK048");
        fbk049.setText("049");
        fbk049.setCallbackData("FBK049");


        rowInLine.add(fbk044);
        rowInLine.add(fbk048);
        rowInLine.add(fbk049);

        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        var back = new InlineKeyboardButton();

        back.setText("Назад");
        back.setCallbackData("FBK");

        rowInLine2.add(back);

        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine2);

        markupInLine.setKeyboard(rowsInLine);
        editMessage.setReplyMarkup(markupInLine);
        editMessageSender.editMessageSender(editMessage);
    }
}
