/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server;

import java.io.InputStream;

/**
 *
 * @author Madness
 */
public interface ImageService {

    InputStream getImageStreamById(String id);
    void saveImageById(String id, String imageDataString);
    
}
