package edu.csumb.pdahl.project2;
/**
 * Title: UserLog.java
 * Abstract: File to actual log the users.
 * Name: Pernille Dahl
 * Date: 2018-Dec-16
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UserLog {

    private UUID airlineId;
    String userName;
    String password;
    Date logDate;

    SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy @ HH:mm:ss");


    public UserLog(){
        logDate = new Date();
        airlineId = UUID.randomUUID();
    }

    public UserLog(UUID airlineId){
        this.airlineId = airlineId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public SimpleDateFormat getSf() {
        return sf;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("-=-=-=-=-=-=-=\n");
        sb.append(userName).append(":").append(password).append("\n");
        sb.append(sf.format(logDate));

        return sb.toString();
    }
}
