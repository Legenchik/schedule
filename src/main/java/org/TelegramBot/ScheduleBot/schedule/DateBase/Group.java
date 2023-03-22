package org.TelegramBot.ScheduleBot.schedule.DateBase;











import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@Entity(name = "NumberGroup")
public class Group {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatId;

    private String Grouptype;

    private String NumberGroup;

    private boolean ScheduleSend;
    private String Faculty;

    private Timestamp registeredAt;



    @Override
    public String toString() {
        return "User{" +
                "chatId=" + chatId +
                ", group type='" + Grouptype + '\'' +
                ", number group='" + NumberGroup + '\'' +
                ", ScheduleSend ='" + ScheduleSend + '\'' +
                ", Faculty ='" + Faculty + '\'' +
                ", registeredAt=" + registeredAt +
                '}';
    }
}
