package com.akz.ky.utils;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author lzx
 * @version 1.0
 * @date 2020.4.2 20:36
 * @Description 用于判断某个端口是否启动(如redis服务器6379)
 */
public class PortUtil {
    public static boolean testPort(int port) {
        try {
            ServerSocket ss = new ServerSocket(port);
            ss.close();
            return false;
        } catch (java.net.BindException e) {
            return true;
        } catch (IOException e) {
            return true;
        }
    }

    public static void checkPort(int port, String server, boolean shutdown) {
        if(!testPort(port)) {
            if(shutdown) {
                String message =String.format("在端口 %d 未检查得到 %s 启动%n",port,server);
                JOptionPane.showMessageDialog(null, message);
                System.exit(1);
            }
            else {
                String message = String.format("在端口 %d 未检查得到 %s 启动%n,是否继续?",port,server);
                if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(null, message))
                    System.exit(1);

            }
        }
    }
}
