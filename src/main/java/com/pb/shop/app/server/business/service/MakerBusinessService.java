/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server.business.service;

import com.pb.shop.exception.GeneralException;
import com.pb.shop.model.Maker;
import com.pb.shop.model.UserGoodMessage;
import java.util.List;

/**
 *
 * @author Дмитрий
 */
public interface MakerBusinessService {
    
    List<Maker> getAllMakers() throws GeneralException;
    Maker getMakerById(String mkId) throws GeneralException;
    List<Maker> getMakersByName(String name) throws GeneralException;
    UserGoodMessage addMaker(Maker maker) throws GeneralException;
    UserGoodMessage updateMaker(Maker maker) throws GeneralException;
    UserGoodMessage deletMaker(String mkId) throws GeneralException;
    
}
