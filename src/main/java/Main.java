import DBConnection.PropertyInf;
import entity.User;
import entity.СhoiceProperties;
import helper.GetData;
import myThread.ReadExelConcurrency;
import serviceJDBC.ServiceUser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static final GetData getFiles = new GetData();
    private static final PropertyInf propertyInf = new PropertyInf();
    private static final String path = propertyInf.getProperties(СhoiceProperties.PATH).getProperty("PATH_TO_FOLDER");


    public static void main(String[] args) {

        File folder = new File(path);
        List<String> listFile = getFiles.listFilesForFolder(folder);

        //-------------------------------------------------------------//

        List<ReadExelConcurrency> tasks = new ArrayList<>();

        List<Thread> threads = new ArrayList<>();

        for (String s : listFile) {
            ReadExelConcurrency task = new ReadExelConcurrency(path + "\\" + s);

            Thread t = new Thread(task);
            t.start();
            tasks.add(task);
            threads.add(t);

        }


        for (int i = 0; i < listFile.size(); i++) {
            try {
                if (!threads.get(i).isInterrupted()) {
                    threads.get(i).join();
                }else {
                    System.out.println(threads.get(i).getName() + " has been interrupted");
                }
            } catch (InterruptedException w) {
                System.out.println(w);
            }
        }

        List<Set<User>> userList = new ArrayList<>();
        for (int i = 0; i < listFile.size(); i++) {
            userList.add(tasks.get(i).getUsers());
        }

        //------------------------------------------------//

        Set<User> users = new HashSet<>();//without duplicate

        for (Set<User> q : userList) {
            for (User qq : q) {
                users.add(qq);
            }
        }

        ServiceUser serviceUser = new ServiceUser();

        for (User q : users) {
            serviceUser.addUserInDB(q);
        }


    }
}


