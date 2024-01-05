package dao.custom.impl;

import dao.custom.OrderDetailDao;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {

    @Override
    public boolean save(Object entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Object entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
