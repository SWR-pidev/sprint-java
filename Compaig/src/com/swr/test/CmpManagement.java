/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.test;
import com.swr.Utils.DataBase;
import com.swr.Entite.Compaign;
import com.swr.Service.ServiceCompaign;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.swr.Service.ServiceProposition;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
/**
 *
 * @author Monta
 */

public class CmpManagement {
    
    
        public static void ToPDF(String Details,String Location,String QR) throws DocumentException, FileNotFoundException{
                              
                    Document doc = new Document();
                    PdfWriter.getInstance(doc, new FileOutputStream(Location));
                    doc.open();
                   Paragraph p1=new Paragraph(Details);
                   doc.add(p1);
                   BarcodeQRCode barcodeQRCode = new BarcodeQRCode(QR, 1000, 1000, null);
                 Image codeQrImage = barcodeQRCode.getImage();
                 codeQrImage.scaleAbsolute(100, 100);  
                     doc.add(codeQrImage);
                doc.close();
                                } 
 
     
     
    public static void main(String[] args) throws IOException, SQLException, DocumentException {
    
  //  ServiceCompaign Ser =new ServiceCompaign ();
    
    Compaign C1=new Compaign ("New Comapign",8000,3750,"Cmp_Logo.png",300,440,"this is Amazing");
   
    //ToPDF("this is what we do ","C:/Users/Monta/Desktop/testPd.pdf","171JMT2201");
    
    }
}