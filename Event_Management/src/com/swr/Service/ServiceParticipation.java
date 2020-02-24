/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.swr.Entite.Participation;
import com.swr.IService.IServiceParticipation;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.Date;
import com.swr.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.swr.Entite.Event;
import com.swr.Service.ServiceEvent;
import com.swr.Entite.User;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.collections.ObservableList;
import static javax.management.Query.times;
/**
 *
 * @author Eya
 */
public class ServiceParticipation implements IServiceParticipation<Participation> {
 private Connection con;
    private Statement ste;

    public ServiceParticipation() {
        con = DataBase.getInstance().getConnection();

    }
    @Override
    public boolean addParticipation(Participation t) throws SQLException {
        
        if(this.checkParticipation1(t.getU().getIdu(),t.getE().getId_evt())==false)
        {
          PreparedStatement pre=con.prepareStatement("INSERT INTO `swr`.`participation` ( `id_user`, `id_evt`, `date_participation`) VALUES ( ?, ?, CURDATE());");
          pre.setInt(1, t.getU().getIdu());
          pre.setInt(2, t.getE().getId_evt());
          if(pre.executeUpdate()!=0) {
         Event e= new Event(t.getE().getId_evt());
         ServiceEvent ser = new ServiceEvent();
         ser.updateSNbparticipantEvent(e);
         
     }
          return true;
      }
      return false; // else System.out.println("you've already paticipated");
    }

    @Override
    public boolean deleteParticipation(Participation t) throws SQLException {
         PreparedStatement pre=con.prepareStatement("DELETE FROM `swr`.`participation`  WHERE id_evt =? AND id_user=?");
         pre.setInt(1,t.getE().getId_evt());
         pre.setInt(2, t.getU().getIdu());
        if(pre.executeUpdate()!=0) {
            Event e = new Event(t.getE().getId_evt());
            ServiceEvent ser = new ServiceEvent();
            ser.decrementNbparticipantEvent(e);
            return true;
        }
        
        
        return false;
    }

    @Override
    public List<Participation> readAllParticipation() throws SQLException {
     List<Participation> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from participation");
     while (rs.next()) {                
               int idU=rs.getInt(1);
               User u = new User();
               u.setId(idU);
               Event e = new Event();
               
               int idE=rs.getInt(2);
               e.setId_evt(idE);
            Date date=rs.getDate(3);
              
               
                
               Participation p=new Participation(u,e,date);
     arr.add(p);
     }
    return arr;
    }

    public boolean checkParticipation1(int idu , int ide) throws SQLException {
       
         PreparedStatement pre=con.prepareStatement("select * from participation where id_evt=? AND id_user=?");
           pre.setInt(1, ide);
           pre.setInt(2, idu);
           
           ResultSet rs=pre.executeQuery();
           return rs.next();
       
    
    }

    @Override
    public boolean checkParticipation(Participation t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    public static void ToPDF(String name,String date,String time,String blasa,String Location,String QR,int nbS,ObservableList<String> data) throws DocumentException, FileNotFoundException, BadElementException, IOException{
                        String s1="we are happy to yo";  
                        int j=83;
                    Document doc = new Document(PageSize.A4.rotate(), 10f, 10f, 10f, 0f);
                   PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(Location));
                    
                    doc.open();
                    String foobar = "Foobar Film Festival";
                    Font f3 = new Font(Font.FontFamily.TIMES_ROMAN, 32.0f, Font.BOLD, new BaseColor(60, 105, 147));
                    Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f, Font.BOLD, new BaseColor(123, 123, 123));
                    Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14.0f, Font.BOLD, new BaseColor(123, 123, 123));
                    PdfContentByte canvas = writer.getDirectContent();
 
                    Phrase phrase = new Phrase(name,f3);
                      Phrase phrase1 = new Phrase(date,f2);
                      Phrase phrase2 = new Phrase(time,f2);
                      Phrase phrase3 = new Phrase(blasa,f2);
                       Phrase phrase4 = new Phrase("Please make sure to bring your ticket with you the day of the event",f1);
                    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase, 84, 428, 0);
                    ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase1, 200, 365, 0);
                    ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase2, 350, 365, 0);
                    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase3, 135, 296, 0);
                      ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase4, 442, 20, 0);
                     com.itextpdf.text.Image image = Image.getInstance("C:\\wamp64\\www\\pidev_swr\\sprint java\\Event Management\\Event_Management\\src\\com\\swr\\GUI\\Front\\images\\pdfSwr-01.png");
                    //image.setAbsolutePosition(0, 0);
                     doc.add(image);
                                                                        
                  BarcodeQRCode barcodeQRCode = new BarcodeQRCode(QR, 1000, 1000, null);
                 com.itextpdf.text.Image codeQrImage = barcodeQRCode.getImage();
                 codeQrImage.scaleAbsolute(100, 100);  
                 codeQrImage.setAbsolutePosition(725, 475);
                     doc.add(codeQrImage);
                     
                     for(int i=0 ; i <nbS;i++)
                     {
                         String s="C:\\wamp64\\www\\swr\\web\\uploads\\"+data.get(i).toString();
                         com.itextpdf.text.Image imageS = Image.getInstance(s);
                         imageS.scaleToFit(50, 50);
                         imageS.setAbsolutePosition(j, 112);
                         j+=84;
                     doc.add(imageS);
                     }
                     
                doc.close();
                                }
}
