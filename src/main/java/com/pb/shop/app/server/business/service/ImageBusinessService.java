/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server.business.service;

import com.pb.shop.exception.GeneralException;
import com.pb.shop.model.UserGoodMessage;
import java.io.InputStream;

/**
 *
 * @author Дмитрий
 */
public interface ImageBusinessService {
    
    InputStream getImageStreamById(String id) throws GeneralException;
    UserGoodMessage saveImageById(String id, String imageDataString) throws GeneralException;
    
}
