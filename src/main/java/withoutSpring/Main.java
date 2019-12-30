package withoutSpring;

import java.io.FileInputStream;
import java.io.*;
import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@SpringBootApplication
//@PropertySource("classpath:application.properties")
public class Main /*extends TimerTask */ {
//    public static final String PATH_TO_PROPERTIES = "/home/olga/IdeaProjects/SchedulerWithSpring/src/main/resources/application.properties";
    private static final String FIlENAME = "/home/olga/Рабочий стол/file1.txt";
//    Properties prop = new Properties();

/*    @Override
    public void run() {
        System.out.println("Началась запись в файл");
        completeTask();
        System.out.println("Закончилась запись в файл");
    }

 */
    @Scheduled(cron = "${instructionSchedularTime}")
    private void completeTask() {
        Date currentdate = new Date();
        try {
            writer(FIlENAME, currentdate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writer(String fileName, Date currentDate) throws IOException {
        File file = new File(fileName);
        String dateAsString = currentDate.toString();
        if (!file.exists()){
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bwriter = new BufferedWriter(fileWriter);
        System.out.println(currentDate);
        bwriter.write(dateAsString +"\n"); // TODO: 22.12.2019 системный перенос на новую строку
        bwriter.close();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);

/*        Date sheduleDate = new Date();
        FileInputStream fileInputStream;
        Properties prop = new Properties();

        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);

        int h = Integer.parseInt(prop.getProperty("hours"));
        int m = Integer.parseInt(prop.getProperty("minutes"));
        int s = Integer.parseInt(prop.getProperty("seconds"));
        int d = Integer.parseInt(prop.getProperty("day"));
        int mon = Integer.parseInt(prop.getProperty("month"));

        sheduleDate.setHours(h);
        sheduleDate.setMinutes(m);
        sheduleDate.setSeconds(s);
        sheduleDate.setDate(d);
        sheduleDate.setMonth(mon);

        TimerTask timerTask = new Main();
        // стартуем TimerTask в виде демона
        Timer timer = new Timer(true);

        timer.schedule(timerTask, sheduleDate);
        System.out.println("TimerTask начал выполнение");
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask прекращена");

 */
    }
}