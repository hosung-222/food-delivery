package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderCancled extends AbstractEvent {

    private Long id;

    public OrderCancled(Order aggregate) {
        super(aggregate);
    }

    public OrderCancled() {
        super();
    }
}
//>>> DDD / Domain Event
