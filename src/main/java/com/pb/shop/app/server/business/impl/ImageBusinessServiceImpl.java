/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server.business.impl;

import com.pb.shop.app.server.business.service.ImageBusinessService;
import com.pb.shop.app.server.dao.service.ImageService;
import com.pb.shop.exception.GeneralException;
import com.pb.shop.model.UserGoodMessage;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Дмитрий
 */
@Service
public class ImageBusinessServiceImpl implements ImageBusinessService{

    @Autowired
    @Qualifier("imgImpl")
    ImageService imageService;
    
    @Override
    public InputStream getImageStreamById(String id) throws GeneralException{
        try{
            Integer.parseInt(id);
            InputStream in = imageService.getImageStreamById(id);
            return in;
        } catch(Exception e){
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public UserGoodMessage saveImageById(String id, String imageDataString) throws GeneralException{
        try{
            Integer.parseInt(id);
            imageService.saveImageById(id, imageDataString);
            return new UserGoodMessage("Изображение успешно сохранено.");
        } catch(Exception e){
            throw new GeneralException(e.getMessage());
        }
    }
    
}
