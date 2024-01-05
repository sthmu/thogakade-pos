package dao.custom;

import dao.CrudDao;
import entity.Customer;

public interface CustomerDao extends CrudDao<Customer> {
//    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
//    boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
//    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
//    List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException;
        public Customer getCustomerById(String CustomerId);
}
