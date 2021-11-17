import DBConnection.PropertyInf;
import entity.User;
import entity.СhoiceProperties;
import helper.GetData;
import myThread.ReadExelConcurrency;
import serviceJDBC.ServiceUser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private static GetData getFiles = new GetData();
    private static PropertyInf propertyInf = new PropertyInf();
    private static String path = propertyInf.getProperties(СhoiceProperties.PATH).getProperty("PATH_TO_FOLDER");


    public static void main(String[] args) throws IOException {

        File folder = new File(path);
        List<String> listFile = getFiles.listFilesForFolder(folder);

        //-------------------------------------------------------------//

        List<ReadExelConcurrency> tasks = new ArrayList<>();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < listFile.size(); i++) {
            ReadExelConcurrency task = new ReadExelConcurrency(path + "\\" + listFile.get(i));

            Thread t = new Thread(task);
            t.start();
            tasks.add(task);
            threads.add(t);
        }

        try {
            for (int i = 0; i < listFile.size(); i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Set<User>> userList = new ArrayList<>();
        for (int i = 0; i < listFile.size(); i++) {
            userList.add(tasks.get(i).getUsers());
        }

        //------------------------------------------------//

        Set<User> users = new HashSet<>();//without duplicate

        for (Set<User> q : userList) {
            for (User qq : q){
                users.add(qq);
            }
        }

        ServiceUser serviceUser = new ServiceUser();

        for (User q : users) {
            serviceUser.addUserInDB(q);
        }



    }
}


