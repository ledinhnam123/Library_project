/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.bean;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Hp
 */
public class ZDate {

    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String toString(Date date) {
        return format.format(date);
    }

    public static String toString(long epocTime) {
        return format.format(new Date(epocTime));
    }

    public static String toString(Timestamp timestamp) {
        return toString(timestamp.getTime());
    }

    public static String dateString(Date date) {
        return dateFormat.format(date);
    }

    public static Date dateParse(String dateString) {

        if (dateString == null) {
            return null;
        }

        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return null;
    }

    public static Date parse(long time) {
        return new Date(time);
    }

    public static Date parse(String dateString) {
        if (dateString == null) {
            return null;
        }

        try {
            return format.parse(dateString);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return null;
    }

    public ZDate() {
    }

    public static long nowMilisecTime() {
        return System.currentTimeMillis();
    }

    public static String now() {
        Date now = new Date();
        return toString(now);
    }

    public static String nowDate() {
        Date now = new Date();
        return dateString(now);
    }

    public static String nowMiliSec() {
        return "" + new Date().getTime();
    }

    public static Date currentDate() {
        return new Date();
    }

    public static LocalDate currentLocalDate() {
        return LocalDate.now();
    }

    @SuppressWarnings("unused")
    public static String toStringDate(long time) {

        try {

            Date date = parse(time);
            SimpleDateFormat dateFromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFromat.format(date);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";
    }

    public static long convertDateToMiliSec(String time) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(time);
            return date.getTime();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return 0;
    }
}
