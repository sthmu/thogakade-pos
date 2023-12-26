package dto.tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
public class ItemTm extends RecursiveTreeObject<ItemTm> {
    private String code;
    private String desc;
    private double unitPrice;
    private int qty;
    private JFXButton btn;



}
