package dao.custom.impl;

import dao.custom.OrderDao;
import dao.util.HibernateUtil;
import db.DBConnection;
import dto.OrderDetailDto;
import dto.OrderDto;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderDto dto) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        OrderEntity orderEntity = new OrderEntity(
                dto.getOrderId(),
                dto.getDate()
        );
        orderEntity.setCustomer(session.find(Customer.class,dto.getCustId()));
        session.save(orderEntity);

        List<OrderDetailDto> list = dto.getList(); //dto type

        for (OrderDetailDto detailDto:list) {
            OrderDetail orderDetail=new OrderDetail(
                    new OrderDetailsKey(detailDto.getItemCode(),detailDto.getOrderId()),
                    session.find(Item.class,detailDto.getItemCode()),
                    orderEntity,
                    detailDto.getQty(),
                    detailDto.getUnitPrice()
            );
        }
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(OrderDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        Session session=HibernateUtil.getSession();
        Query query=session.createQuery("FROM OrderEntity",OrderEntity.class);
        List<OrderEntity> orderEntityList = query.getResultList();




        List<OrderDto> dtoList= new ArrayList<>();
        for(OrderEntity orderEntity : orderEntityList){
             List<OrderDetailDto> detailsDtoList=new ArrayList<>();
            for(OrderDetail orderDetail: orderEntity.getOrderDetails()){
                detailsDtoList.add(new OrderDetailDto(orderDetail.getOrderEntity().getOrderId(),orderDetail.getItem().getCode(),orderDetail.getQty(),orderDetail.getUnitPrice()));

            }
            dtoList.add(new OrderDto(orderEntity.getOrderId(), orderEntity.getDate(), orderEntity.getCustomer().getId(),detailsDtoList));
        }
        session.close();
        return dtoList;
    }

    @Override
    public OrderDto getLastOrder() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    null
            );
        }
        return null;
    }
}
