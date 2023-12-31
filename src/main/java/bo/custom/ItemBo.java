package bo.custom;

import bo.SuperBo;
import dto.ItemDto;

import java.sql.SQLException;

public interface ItemBo extends SuperBo {

    public boolean saveItem(ItemDto itemDto) throws SQLException, ClassNotFoundException;
    public boolean updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException;

    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
}
