/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server.dao.impl;

import com.pb.shop.app.server.dao.service.MakerDaoService;
import com.pb.shop.model.Maker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Madness
 */
//@Service("maker")
public class MakerDaoServiceImpl extends JdbcDaoSupport implements MakerDaoService{

    
    private final String GET_ALL_MAKERS = "SELECT * FROM maker";
    private final String GET_MAKER_BY_ID = "SELECT * FROM maker WHERE makid = ?"; 
    private final String GET_MAKER_BY_NAME = "SELECT * FROM maker WHERE makname like ?";
    private final String ADD_MAKER = "INSERT INTO maker (makid, makname, makdescr) VALUES(?,?,?)";
    private final String UPDATE_MAKER = "UPDATE maker SET makname = ?, makdescr = ? WHERE makid = ?";
    private final String DELETE_MAKER = "DELETE FROM Maker WHERE MakID = ?";
    
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Maker> getAllMakers() {
        return getJdbcTemplate().query(GET_ALL_MAKERS, new MakerMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Maker getMakerById(String mkId) {
        List<Maker> list = getJdbcTemplate().query(
                GET_MAKER_BY_ID, 
                new Object[]{Integer.parseInt(mkId)}, 
                new MakerMapper());
        return list.get(0);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Maker> getMakersByName(String name) {
        List<Maker> list = getJdbcTemplate().query(
                GET_MAKER_BY_NAME, 
                new Object[]{"%" + name + "%"}, 
                new MakerMapper());
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addMaker(Maker maker) {
        getJdbcTemplate().update(
                ADD_MAKER, 
                new Object[]{
                    maker.getMakID(),
                    maker.getMakName(),
                    maker.getMakDescr()}
                );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateMaker(Maker maker) {
        getJdbcTemplate().update(
                UPDATE_MAKER, 
                new Object[]{
                    maker.getMakName(),
                    maker.getMakDescr(),
                    maker.getMakID()}
                );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deletMaker(String mkId) {
        getJdbcTemplate().update(DELETE_MAKER, 
                new Object[]{new Integer(mkId)}
        );
    }

private static final class MakerMapper implements RowMapper<Maker>{

    @Override
    public Maker mapRow(ResultSet rs, int rowNum) throws SQLException {
        Maker maker = new Maker();
        maker.setMakID(rs.getInt("MakID"));
        maker.setMakName(rs.getString("MakName"));
        maker.setMakDescr(rs.getString("MakDescr"));
        return maker;
    }

}
}
