package main.dao;

import java.io.*;

public class AdminDAO {
    public AdminDAO(){

    }
    public String login(String input_id) {

        String user_id = "", user_pw = "";

        try {
            File adminInfo = new File("/Users/limjeonghyun/Desktop/ManageProgram/info_files/adminInfo");
            if(!adminInfo.exists()) return user_pw;

            FileReader fileReader = new FileReader(adminInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                user_id = line.split("/")[0];
                if (user_id.equals(input_id)) {
                    user_pw = line.split("/")[1];
                    break;
                }
            }
            bufReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user_pw;
    }
}
