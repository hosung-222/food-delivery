package fooddelivery.domain;

import fooddelivery.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private Long foodId;
    private List<String> options;
    private String address;
    private String customerId;
}
