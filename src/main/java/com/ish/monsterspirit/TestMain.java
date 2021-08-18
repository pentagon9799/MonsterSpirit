/*
 * Hilmi Abdurrahman Fakhrudin - 1807422008
 */
package com.ish.monsterspirit;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * @author hilmi
 */
public class TestMain {

    public static void main(String[] args) {

        TestMain tm = new TestMain();
        tm.searchResource();

    }

    void searchResource() {
        ClassLoader cldr = this.getClass().getClassLoader();
        System.out.println("yooo");
//        System.out.println(cldr.getClass().getResource("").getPath());
        System.out.println(new File(".").getAbsolutePath());

        URL u = cldr.getResource("monsterIcon/img.png");

        if (u != null) {
            System.out.println("Resource found");
        } else {
            System.out.println("Resource not found");
        }
    }
}
