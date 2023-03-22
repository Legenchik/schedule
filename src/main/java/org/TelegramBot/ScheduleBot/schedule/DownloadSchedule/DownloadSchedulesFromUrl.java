package org.TelegramBot.ScheduleBot.schedule.DownloadSchedule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.TelegramBot.ScheduleBot.schedule.TelegramBot.ScheduleBot;
import org.TelegramBot.ScheduleBot.schedule.configBot.BotConfig;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;






public class DownloadSchedulesFromUrl {
	final BotConfig config;

	public DownloadSchedulesFromUrl(BotConfig config) {
		this.config = config;
	}

	public  void  downloadSheet() {
		URL website = null;
		try {
			website = new URL("https://docs.google.com/spreadsheets/d/1MW7fdukf2_-NSiqke9EdNHU-CwCouvz0fK_jaNtac_I/export?format=xlsx");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		ReadableByteChannel rbc = null;
		try {
			rbc = Channels.newChannel(website.openStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(".\\datafiles\\scheduleFBK.xlsx");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		try {
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String Exelfile = ".\\datafiles\\ScheduleFBK.xlsx";
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(Exelfile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(fs);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		ScheduleBot scheduleBot = new ScheduleBot(config);
		scheduleBot.setWbFBK(wb);
		//log.debug("Download is ok");

	}
}


