/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Service;

import com.ledinhnam.Entity.CustomersEntity;
import com.ledinhnam.Entity.TicketEntity;
import com.ledinhnam.bean.ZDate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */
public class Test {
 
    public static void main(String[] args) {
     
        Calendar c1 = Calendar.getInstance(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();      
        String dateTring =ZDate.dateString(date);
        c1.setTime(date);
        c1.roll(Calendar.DATE, 5);
         dateTring = sdf.format(c1.getTime());
         System.out.println(dateTring);
         Date dateApoit = ZDate.dateParse(dateTring);
         System.out.println("com.ledinhnam.Service.Test.main()" + dateApoit);
         
         
         
         
         long milisReturnDate  = dateApoit.getTime();
         long miliesAppointDate = date.getTime();
        
        
        int totalDateExpired =(int) ((milisReturnDate-miliesAppointDate)/(1000*60*60*24)); 
        System.out.println("-------------------------------------------------------");
         System.out.println( totalDateExpired*5000);
         System.out.println("cap nhat");
         updateCustome(1);
         System.out.println("==========================================");
       
    }
    
    public static void updateCustome(int id){
        CustomerService service = new CustomerService();
        CustomersEntity customer = new CustomersEntity();     
        service.updateCus(customer);
    }
    
   
}
