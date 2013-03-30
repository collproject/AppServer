/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server;

import com.pb.shop.model.Maker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 *
 * @author Madness
 */
//@Service("maker")
public class MakerDaoServiceImpl extends JdbcDaoSupport implements MakerDaoService{

    @Override
    public List<Maker> getAllMakers() {
        String query = "SELECT * FROM maker";
        return getJdbcTemplate().query(query, new MakerMapper());
    }

    @Override
    public Maker getMakerById(String mkId) {
        String query = "SELECT * FROM maker WHERE makid = ?";               
        List<Maker> list = getJdbcTemplate().query(
                query, 
                new Object[]{Integer.parseInt(mkId)}, 
                new MakerMapper());
        return list.get(0);
    }

    @Override
    public List<Maker> getMakersByName(String name) {
        String query = "SELECT * FROM maker WHERE makname like ?";               
        List<Maker> list = getJdbcTemplate().query(
                query, 
                new Object[]{"%" + name + "%"}, 
                new MakerMapper());
        return list;
    }

    @Override
    public void addMaker(Maker maker) {
        String query = "INSERT INTO maker (makid, makname, makdescr) VALUES(?,?,?)";               
        getJdbcTemplate().update(
                query, 
                new Object[]{
                    maker.getMakID(),
                    maker.getMakName(),
                    maker.getMakDescr()}
                );
    }

    @Override
    public void updateMaker(Maker maker) {
        String query = "UPDATE maker SET makname = ?, makdescr = ? WHERE makid = ?";               
        getJdbcTemplate().update(
                query, 
                new Object[]{
                    maker.getMakName(),
                    maker.getMakDescr(),
                    maker.getMakID()}
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
