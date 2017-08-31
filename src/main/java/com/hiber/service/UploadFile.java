/*
 * 
 */
package com.hiber.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceException;
/**
 *
 * @author SHWETA
 */

@WebService(serviceName = "UploadFile")
public class UploadFile {

//    @WebMethod(operationName = "hello")
//    public String hello(@WebParam(name = "name") String txt) {
//        return "Hello " + txt + " !";
//    }
    
    @WebMethod(operationName = "upload")
    public void upload(@WebParam(name = "fileName")String fileName, @WebParam(name = "imageBytes") byte[] imageBytes) {
         
        String filePath = "C:/Users/SHWETA/Documents/" + fileName;         
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            BufferedOutputStream outputStream = new BufferedOutputStream(fos);
            outputStream.write(imageBytes);
            outputStream.close();             
            //System.out.println("Received file: " + filePath);
             
        } catch (IOException ex) {
            System.err.println(ex);
            throw new WebServiceException(ex);
        }
    }
    
    @WebMethod
    public byte[] download(@WebParam(name = "fileName") String fileName) {
        
        String filePath = "C:/Users/SHWETA/Documents/" + fileName;
        //System.out.println("Sending file: " + filePath);
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(fis);
            byte[] fileBytes = new byte[(int) file.length()];
            inputStream.read(fileBytes);
            inputStream.close();             
            return fileBytes;
        } catch (IOException ex) {
            return null;
        }      
    }
}
