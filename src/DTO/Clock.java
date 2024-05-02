/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author DucThanh
 */
public class Clock extends Thread{

    JLabel labelTime;
    public Clock(JLabel labelTime) {
        this.labelTime = labelTime;
    }
    
    public void realTime()
    {
        SimpleDateFormat realTime = new SimpleDateFormat("H:mm:ss");
        while (true)
        {
            Date date = new Date();
            labelTime.setText(realTime.format(date));
            try {
                 Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}
