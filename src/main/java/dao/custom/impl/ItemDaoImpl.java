package dao.custom.impl;

import dao.util.HibernateUtil;
import db.DBConnection;
import dto.CustomerDto;
import dto.ItemDto;
import dao.custom.ItemDao;
import entity.Customer;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {


    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Item item=session.find(Item.class,entity.getCode());
        item.setDescription(entity.getDescription());
        item.setUnitPrice(entity.getUnitPrice());
        item.setQtyOnHand(entity.getQtyOnHand());
        session.save(item);
        transaction.commit();
       session.close();
       return true;
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        Session session=HibernateUtil.getSession();
        Transaction transaction= session.beginTransaction();
        session.delete(session.find(Item.class,code));
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }



    @Override
    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        List<ItemDto> list = new ArrayList<>();
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM item");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list.add(new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            ));
        }
        return list;
    }

    @Override
    public CustomerDto searchItem(String id) {
        return null;
    }
}
