package fooddelivery.domain;

import fooddelivery.StoreApplication;
import fooddelivery.domain.OrderRejected;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "FoodCooking_table")
@Data
//<<< DDD / Aggregate Root
public class FoodCooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;

    private String foodId;

    private String orderId;
    private String customerId;

    @ElementCollection
    private List<String> options;

    private String storeId;

    @PostPersist
    public void onPostPersist() {
        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();
    }

    public static FoodCookingRepository repository() {
        FoodCookingRepository foodCookingRepository = StoreApplication.applicationContext.getBean(
            FoodCookingRepository.class
        );
        return foodCookingRepository;
    }

    //<<< Clean Arch / Port Method
    public void accept(AcceptCommand acceptCommand) {
        //implement business logic here:

        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void start() {
        //implement business logic here:

        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void finish() {
        //implement business logic here:

        CookFinished cookFinished = new CookFinished(this);
        cookFinished.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void 주문정보복제(OrderPlaced orderPlaced) {
        //implement business logic here:

        /** Example 1:  new item */
        FoodCooking foodCooking = new FoodCooking();
        foodCooking.setCustomerId(orderPlaced.getCustomerId());
        foodCooking.setFoodId(String.valueOf(orderPlaced.getFoodId()));
        foodCooking.setOrderId(String.valueOf(orderPlaced.getId()));
        foodCooking.setStatus("미결제");
        repository().save(foodCooking);


        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(foodCooking->{
            
            foodCooking // do something
            repository().save(foodCooking);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateStatus(Paid paid) {
        //implement business logic here:

        /** Example 1:  new item 
        FoodCooking foodCooking = new FoodCooking();
        repository().save(foodCooking);

        */


        
        repository().findByOrderId(paid.getOrderId()).ifPresent(foodCooking->{
            
            foodCooking.setStatus("결제됨"); // do something
            repository().save(foodCooking);


         });


    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
