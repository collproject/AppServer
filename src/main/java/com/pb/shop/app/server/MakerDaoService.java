/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server;

import com.pb.shop.model.Maker;
import java.util.List;
/**
 *
 * @author Madness
 */
public interface MakerDaoService {
    List<Maker> getAllMakers();
    Maker getMakerById(String mkId);
    List<Maker> getMakersByName(String name);
    void addMaker(Maker maker);
    void updateMaker(Maker maker);
}
