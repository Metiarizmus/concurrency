package myThread;

import entity.User;
import helper.GetData;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Set;

public class ReadExelConcurrency extends Thread {

    @Getter private String fileName;
    @Getter private Set<User> users;

    public ReadExelConcurrency(String fileName) {
        this.fileName = fileName;
        System.out.println("Create Task to get content from " + fileName);
    }


    @SneakyThrows
    @Override
    public void run() {
        GetData getData = new GetData();
        System.out.printf("%s started... \n", Thread.currentThread().getName());

        if (!isInterrupted()){
            users = getData.parsExelData(fileName);
        }
    }
}
