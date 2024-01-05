package dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class OrderInfoTm {
    private String OrderId;
    private String CustomerId;
    private String Date;
    private double Amount;

}
