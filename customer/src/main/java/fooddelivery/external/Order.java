package fooddelivery.external;

import java.util.Date;
import lombok.Data;

@Data
public class Order {

    private Long id;
    private Long foodId;
    private Object options;
    private String address;
    private String customerId;
    private String storeId;
}
