import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setup() {
    	LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
    	restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
    	Calendar calnder = Calendar.getInstance();
    	LocalTime openTime = LocalTime.of(calnder.get(Calendar.HOUR_OF_DAY) - 1 , 0);
        LocalTime closeTime = LocalTime.of(calnder.get(Calendar.HOUR_OF_DAY) + 1, 0);
        
        Restaurant rstrnt =new Restaurant("Ghanshyam's cafe","Chennai",openTime,closeTime);
        assertEquals(true,rstrnt.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
    	//Restaurant restaurant1 = Mockito.spy(restaurant);
    	Calendar calnder = Calendar.getInstance();
    	LocalTime openTime = LocalTime.of(calnder.get(Calendar.HOUR_OF_DAY) - 2 , 0);
        LocalTime closeTime = LocalTime.of(calnder.get(Calendar.HOUR_OF_DAY) - 1, 0);
        
        Restaurant rstrnt = new Restaurant("Ghanshyam's cafe","Chennai",openTime,closeTime);
        assertEquals(false,rstrnt.isRestaurantOpen());
    	//LocalTime stubTime = LocalTime.parse("11:00:00");
    	//Mockito.when(restaurant1.getCurrentTime()).thenReturn(stubTime);
    	//assertEquals(false,restaurant1.isRestaurantOpen());

    }
    
    @Test
    public void total_order_items_price_value_should_return() {
    	Integer totalPrice = 300;
    	restaurant.addToMenu("roti",100);
    	restaurant.addToMenu("Dal",200);
    	String itemList[]= {"roti", "Dal"};
    	assertEquals(totalPrice,restaurant.getTotalPrice(itemList));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        // LocalTime openingTime = LocalTime.parse("10:30:00");
        // LocalTime closingTime = LocalTime.parse("22:00:00");
        // restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        // LocalTime openingTime = LocalTime.parse("10:30:00");
        // LocalTime closingTime = LocalTime.parse("22:00:00");
        // restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        // LocalTime openingTime = LocalTime.parse("10:30:00");
        // LocalTime closingTime = LocalTime.parse("22:00:00");
        // restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}