package controller;

import bo.BoFactory;
import bo.custom.OrderBo;
import dao.util.BoType;
import dto.OrderDetailDto;
import dto.OrderDto;
import dto.tm.OrderInfoTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {

    public TableView<OrderInfoTm> tblOrderList;
    public TableColumn ColOrderId;
    public TableColumn ColCustomerId;
    public TableColumn ColAmount;
    public BorderPane pane;
    public TableColumn ColDate;

    private OrderBo orderBo= BoFactory.getInstance().getBo(BoType.ORDER);

    private List<OrderDto> listOfOrders;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        ColCustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        ColAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("Date"));


        try {
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTable() throws SQLException, ClassNotFoundException {
        ObservableList<OrderInfoTm> TmOrderList= FXCollections.observableArrayList();
        listOfOrders = orderBo.getAllOrders();

        for(OrderDto orderDto: listOfOrders){

            double amount=0;

            for(OrderDetailDto detailDto:orderDto.getList()){
                amount+= detailDto.getQty()*detailDto.getUnitPrice();
            }
            TmOrderList.add(new OrderInfoTm(orderDto.getOrderId(),orderDto.getCustId(),orderDto.getDate(),amount));
        }
        tblOrderList.setItems(TmOrderList);

    }

    public void pendingOrdersdBtnOnAction(ActionEvent actionEvent) {
    }

    public void completedOrdersBtnOnAction(ActionEvent actionEvent) {
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
