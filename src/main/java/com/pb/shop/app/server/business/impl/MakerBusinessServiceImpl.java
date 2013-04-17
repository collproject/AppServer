/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server.business.impl;

import com.pb.shop.app.server.business.service.MakerBusinessService;
import com.pb.shop.app.server.dao.service.MakerDaoService;
import com.pb.shop.exception.GeneralException;
import com.pb.shop.model.Maker;
import com.pb.shop.model.UserGoodMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Дмитрий
 */
@Service
public class MakerBusinessServiceImpl implements MakerBusinessService {

    @Autowired
    @Qualifier("makerImpl")
    MakerDaoService makerDaoService;

    @Override
    public List<Maker> getAllMakers() throws GeneralException {
        try {
            List<Maker> list = makerDaoService.getAllMakers();
            if (list.isEmpty()) {
                throw new GeneralException("Нет ни одного производителя.");
            }
            return list;
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public Maker getMakerById(String mkId) throws GeneralException {
        try {
            Integer.parseInt(mkId);
            Maker maker = makerDaoService.getMakerById(mkId);
            if(maker == null){
                throw new GeneralException("Производитель с таким ИН отсутствует.");
            }
            return maker;
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public List<Maker> getMakersByName(String name) throws GeneralException {
        try {
            List<Maker> list = makerDaoService.getMakersByName(name);
            if (list.isEmpty()) {
                throw new GeneralException("Нет ни одного производителя с таким именем.");
            }
            return list;
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public UserGoodMessage addMaker(Maker maker) throws GeneralException {
        try {
            makerDaoService.addMaker(maker);
            return new UserGoodMessage("Производитель успешно добавлен");
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public UserGoodMessage updateMaker(Maker maker) throws GeneralException {
        try {
            makerDaoService.updateMaker(maker);
            return new UserGoodMessage("Производитель успешно обновлен");
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public UserGoodMessage deletMaker(String mkId) throws GeneralException {
        try {
            Integer.parseInt(mkId);
            makerDaoService.deletMaker(mkId);
            return new UserGoodMessage("Производитель успешно удален");
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }
}
