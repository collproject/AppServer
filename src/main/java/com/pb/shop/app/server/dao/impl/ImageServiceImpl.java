/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server.dao.impl;

import com.pb.shop.app.server.dao.service.ImageService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Madness
 */
public class ImageServiceImpl implements ImageService {
    
    private String imgDir;

    public String getImgDir() {
        return imgDir;
    }

    public void setImgDir(String imgDir) {
        this.imgDir = imgDir;
    }
    
            
    @Override
    public InputStream getImageStreamById(String id) {

        File f = new File(imgDir + "/" + id + ".jpg");
        
        if(!f.isFile()){
            f = new File(imgDir + "/no_image.jpg");
        }
        
        InputStream in = null;
        try {
            in = new FileInputStream(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return in;
    }

    @Override
    public void saveImageById(String id, String imageDataString) {

        byte[] imageByteArray = Base64.decodeBase64(imageDataString);
        try {
            FileOutputStream imageOutFile = new FileOutputStream(
                    imgDir + "/" + id + ".jpg");

            imageOutFile.write(imageByteArray);
            imageOutFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ImageServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean delImageById(String id) {
        File f = new File(imgDir + "/" + id + ".jpg");
        
        if(f.isFile()){
            return f.delete();
        }
        
        return false;
    }
}
