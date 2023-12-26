package dao.custom;

import dao.CrudDao;
import dto.CustomerDto;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends CrudDao<Item> {
    boolean save(Item entity) throws SQLException, ClassNotFoundException;
    boolean update(Item entity) throws SQLException, ClassNotFoundException;
    boolean delete(String code) throws SQLException, ClassNotFoundException;
    List<ItemDto> allItems() throws SQLException, ClassNotFoundException;
    CustomerDto searchItem(String id);



}
