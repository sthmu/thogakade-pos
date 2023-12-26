package bo.custom.impl;


import bo.custom.ItemBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.custom.ItemDao;
import dao.util.DaoType;
import dto.CustomerDto;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;

public class ItemBoimpl implements ItemBo {
private ItemDao itemDao= DaoFactory.getInstance().getDao(DaoType.ITEM);

    @Override
    public boolean updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return itemDao.update(
            new Item(itemDto.getCode(),itemDto.getDesc(),itemDto.getUnitPrice(),itemDto.getQty())
        );
    }

    @Override
    public boolean saveItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return itemDao.save(
                new Item(itemDto.getCode(),itemDto.getDesc(), itemDto.getUnitPrice(), itemDto.getQty())
        );
    }
}
