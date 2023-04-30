
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

class OrderMangerTest {
    @Test
    void test_add_item_list_one_supplier(){ //1
        var ordermanger=new OrderManger();
        Map<Item,Integer> maplist =new HashMap<Item,Integer>();

        Item item1 = new Item("Apple", "1001", "12/10/1992", 0.5, "Fruits", 10.0);
        Item item2 = new Item("Milk", "2002", "12/10/1992", 1.0, "Dairy", 4.0);
        Item item3 = new Item("Bread", "3003", "12/10/1992", 0.8, "Bakery", 25.0);
        Item item4 = new Item("Salmon", "4004", "12/10/1992", 0.3, "Seafood", -2.0);

        maplist.put(item1,100);
        maplist.put(item2,100);
        maplist.put(item3,100);
        maplist.put(item4,100);

        Supplier_Manger masupplier=new Supplier_Manger();
        ContactPerson contactPerson = new ContactPerson("John Smith", "555-1234");
        NonDeliveringSupplier supplier = new NonDeliveringSupplier("Supplier Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier.add_Items(item1,100,100);
        supplier.add_Items(item2,100,100);
        supplier.add_Items(item3,100,100);
        supplier.add_Items(item4,100,100);

        masupplier.getSuppliers().add(supplier);
        ordermanger.assing_Orders_to_Suppliers(maplist,masupplier,20);
        supplier.print_items();
        ordermanger.getPending_for_apporval().get(0).print_items();



    }


    //test 2
    @Test
    void test_add_item_list_two_supplier(){
        var ordermanger=new OrderManger();
        Map<Item,Integer> maplist =new HashMap<Item,Integer>();

        Item item1 = new Item("Apple", "1001", "12/10/1992", 0.5, "Fruits", 10.0);
        Item item2 = new Item("Milk", "2002", "12/10/1992", 1.0, "Dairy", 4.0);
        Item item3 = new Item("Bread", "3003", "12/10/1992", 0.8, "Bakery", 25.0);
        Item item4 = new Item("Salmon", "4004", "12/10/1992", 0.3, "Seafood", -2.0);

        maplist.put(item1,100);
        maplist.put(item2,100);
        maplist.put(item3,100);
        maplist.put(item4,100);

        Supplier_Manger masupplier=new Supplier_Manger();
        ContactPerson contactPerson = new ContactPerson("John Smith", "555-1234");
        NonDeliveringSupplier supplier_1 = new NonDeliveringSupplier("Supplier1 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_1.add_Items(item1,100,100);
        supplier_1.add_Items(item2,100,100);
        supplier_1.add_Items(item3,100,100);
        supplier_1.add_Items(item4,100,100);
        NonDeliveringSupplier supplier_2 = new NonDeliveringSupplier("Supplier2 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_2.add_Items(item1,100,100);
        supplier_2.add_Items(item2,100,100);
        supplier_2.add_Items(item3,100,100);
        masupplier.getSuppliers().add(supplier_1);
        masupplier.getSuppliers().add(supplier_2);
        ordermanger.assing_Orders_to_Suppliers(maplist,masupplier,20);
        assertEquals(ordermanger.getPending_for_apporval().get(0).getSupplier().getName(),"Supplier1 Inc.");


    }

    //test 3



    @Test
    void test_add_item_list_two_supplier_one_is_cheaper(){
        var ordermanger=new OrderManger();
        Map<Item,Integer> maplist =new HashMap<Item,Integer>();

        Item item1 = new Item("Apple", "1001", "12/10/1992", 0.5, "Fruits", 10.0);
        Item item2 = new Item("Milk", "2002", "12/10/1992", 1.0, "Dairy", 4.0);
        Item item3 = new Item("Bread", "3003", "12/10/1992", 0.8, "Bakery", 25.0);
        Item item4 = new Item("Salmon", "4004", "12/10/1992", 0.3, "Seafood", -2.0);

        maplist.put(item1,100);
        maplist.put(item2,100);
        maplist.put(item3,100);
        maplist.put(item4,100);

        Supplier_Manger masupplier=new Supplier_Manger();
        ContactPerson contactPerson = new ContactPerson("John Smith", "555-1234");
        NonDeliveringSupplier supplier_1 = new NonDeliveringSupplier("Supplier1 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_1.add_Items(item1,100,100);
        supplier_1.add_Items(item2,100,100);
        supplier_1.add_Items(item3,100,100);
        supplier_1.add_Items(item4,100,100);
        NonDeliveringSupplier supplier_2 = new NonDeliveringSupplier("Supplier2 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_2.add_Items(item1,100,100);
        supplier_2.add_Items(item2,100,100);
        supplier_2.add_Items(item3,100,100);
        supplier_2.add_Items(item4,100,5);
        masupplier.getSuppliers().add(supplier_1);
        masupplier.getSuppliers().add(supplier_2);
        ordermanger.assing_Orders_to_Suppliers(maplist,masupplier,20);
        assertEquals("Supplier2 Inc.",ordermanger.getPending_for_apporval().get(0).getSupplier().getName());


    }
    //test 4

    @Test
    void test_add_item_list_two_supplier_split_orders(){
        var ordermanger=new OrderManger();
        Map<Item,Integer> maplist =new HashMap<Item,Integer>();

        Item item1 = new Item("Apple", "1001", "12/10/1992", 0.5, "Fruits", 10.0);
        Item item2 = new Item("Milk", "2002", "12/10/1992", 1.0, "Dairy", 4.0);
        Item item3 = new Item("Bread", "3003", "12/10/1992", 0.8, "Bakery", 25.0);
        Item item4 = new Item("Salmon", "4004", "12/10/1992", 0.3, "Seafood", -2.0);

        maplist.put(item1,100);
        maplist.put(item2,100);
        maplist.put(item3,100);
        maplist.put(item4,100);

        Supplier_Manger masupplier=new Supplier_Manger();
        ContactPerson contactPerson = new ContactPerson("John Smith", "555-1234");
        NonDeliveringSupplier supplier_1 = new NonDeliveringSupplier("Supplier1 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_1.add_Items(item1,100,100);
        supplier_1.add_Items(item2,100,100);

        NonDeliveringSupplier supplier_2 = new NonDeliveringSupplier("Supplier2 Inc.", "123456789", 1, "S001", contactPerson, null, null);

        supplier_2.add_Items(item3,100,100);
        supplier_2.add_Items(item4,100,5);
        masupplier.getSuppliers().add(supplier_1);
        masupplier.getSuppliers().add(supplier_2);
        ordermanger.assing_Orders_to_Suppliers(maplist,masupplier,20);
        assertEquals("Supplier2 Inc.",ordermanger.getPending_for_apporval().get(0).getSupplier().getName());
        assertEquals("Supplier1 Inc.",ordermanger.getPending_for_apporval().get(1).getSupplier().getName());


    }
    //test 5

    @Test
    void test_add_item_list_two_supplier_split_orders_with_deals(){

        var ordermanger=new OrderManger();
        Map<Item,Integer> maplist =new HashMap<Item,Integer>();

        Item item1 = new Item("Apple", "1001", "12/10/1992", 0.5, "Fruits", 10.0);
        Item item2 = new Item("Milk", "2002", "12/10/1992", 1.0, "Dairy", 4.0);
        Item item3 = new Item("Bread", "3003", "12/10/1992", 0.8, "Bakery", 25.0);
        Item item4 = new Item("Salmon", "4004", "12/10/1992", 0.3, "Seafood", -2.0);

        maplist.put(item1,100);
        maplist.put(item2,100);
        maplist.put(item3,100);
        maplist.put(item4,100);

        Supplier_Manger masupplier=new Supplier_Manger();
        ContactPerson contactPerson = new ContactPerson("John Smith", "555-1234");
        NonDeliveringSupplier supplier_1 = new NonDeliveringSupplier("Supplier1 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_1.add_Items(item1,100,100);
        supplier_1.add_Items(item2,100,100);
        supplier_1.add_Items(item3,100,100);
        supplier_1.add_Items(item4,100,100);
        supplier_1.add_total_discount(0.9);
        NonDeliveringSupplier supplier_2 = new NonDeliveringSupplier("Supplier2 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_2.add_Items(item1,100,100);
        supplier_2.add_Items(item2,100,100);
        supplier_2.add_Items(item3,100,100);
        supplier_2.add_Items(item4,100,100);
        supplier_2.add_total_discount(0.5);
        masupplier.getSuppliers().add(supplier_1);
        masupplier.getSuppliers().add(supplier_2);
        ordermanger.assing_Orders_to_Suppliers(maplist,masupplier,20);
        assertEquals("Supplier2 Inc.",ordermanger.getPending_for_apporval().get(0).getSupplier().getName());


    }

    @Test
    void test_add_item_list_two_supplier_split_orders_with_deals_on_amount(){

        var ordermanger=new OrderManger();
        Map<Item,Integer> maplist =new HashMap<Item,Integer>();

        Item item1 = new Item("Apple", "1001", "12/10/1992", 0.5, "Fruits", 10.0);
        Item item2 = new Item("Milk", "2002", "12/10/1992", 1.0, "Dairy", 4.0);
        Item item3 = new Item("Bread", "3003", "12/10/1992", 0.8, "Bakery", 25.0);
        Item item4 = new Item("Salmon", "4004", "12/10/1992", 0.3, "Seafood", -2.0);

        maplist.put(item1,100);
        maplist.put(item2,100);
        maplist.put(item3,100);
        maplist.put(item4,100);

        Supplier_Manger masupplier=new Supplier_Manger();
        ContactPerson contactPerson = new ContactPerson("John Smith", "555-1234");
        NonDeliveringSupplier supplier_1 = new NonDeliveringSupplier("Supplier1 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_1.add_Items(item1,100,100);
        supplier_1.add_item_to_contract(item1.getName(),100,0.1);
        supplier_1.add_Items(item2,100,100);
        supplier_1.add_Items(item3,100,100);
        supplier_1.add_Items(item4,100,100);
        supplier_1.add_total_discount(0.5);
        supplier_1.add_item_to_contract(item1.getName(),20,0.1);;
        NonDeliveringSupplier supplier_2 = new NonDeliveringSupplier("Supplier2 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_2.add_Items(item1,100,100);
        supplier_2.add_Items(item2,100,100);
        supplier_2.add_Items(item3,100,100);
        supplier_2.add_Items(item4,100,100);
        supplier_2.add_total_discount(0.5);
        masupplier.getSuppliers().add(supplier_1);
        masupplier.getSuppliers().add(supplier_2);
        ordermanger.assing_Orders_to_Suppliers(maplist,masupplier,20);
        assertEquals("Supplier1 Inc.",ordermanger.getPending_for_apporval().get(0).getSupplier().getName());

}
    //tetst 6
    @Test
    void move_order_to_approvel(){
        var ordermanger=new OrderManger();
        Map<Item,Integer> maplist =new HashMap<Item,Integer>();

        Item item1 = new Item("Apple", "1001", "12/10/1992", 0.5, "Fruits", 10.0);
        Item item2 = new Item("Milk", "2002", "12/10/1992", 1.0, "Dairy", 4.0);
        Item item3 = new Item("Bread", "3003", "12/10/1992", 0.8, "Bakery", 25.0);
        Item item4 = new Item("Salmon", "4004","12/10/1992", 0.3, "Seafood", -2.0);

        maplist.put(item1,100);
        maplist.put(item2,100);
        maplist.put(item3,100);
        maplist.put(item4,100);

        Supplier_Manger masupplier=new Supplier_Manger();
        ContactPerson contactPerson = new ContactPerson("John Smith", "555-1234");
        NonDeliveringSupplier supplier_1 = new NonDeliveringSupplier("Supplier1 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_1.add_Items(item1,100,100);
        supplier_1.add_item_to_contract(item1.getName(),100,0.1);
        supplier_1.add_Items(item2,100,100);
        supplier_1.add_Items(item3,100,100);
        supplier_1.add_Items(item4,100,100);
        supplier_1.add_total_discount(0.5);
        supplier_1.add_item_to_contract(item1.getName(),20,0.1);;
        NonDeliveringSupplier supplier_2 = new NonDeliveringSupplier("Supplier2 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_2.add_Items(item1,10,90);
        supplier_2.add_Items(item2,100,100);
        supplier_2.add_Items(item3,100,100);
        supplier_2.add_Items(item4,100,100);
        supplier_2.add_total_discount(0.5);
        masupplier.getSuppliers().add(supplier_1);
        masupplier.getSuppliers().add(supplier_2);
        ordermanger.assing_Orders_to_Suppliers(maplist,masupplier,20);
        int num =ordermanger.getPending_for_apporval().get(0).getOrderNum();
        ordermanger.move_from_pending_to_approvel(num);
        assertEquals(1, ordermanger.getApproval().size());



    }
    // test 7
    @Test
    void three_supllier_split(){
        var ordermanger=new OrderManger();
        Map<Item,Integer> maplist =new HashMap<Item,Integer>();

        Item item1 = new Item("Apple", "1001", "12/10/1992", 0.5, "Fruits", 10.0);
        Item item2 = new Item("Milk", "2002", "12/10/1992", 1.0, "Dairy", 4.0);
        Item item3 = new Item("Bread", "3003", "12/10/1992", 0.8, "Bakery", 25.0);
        Item item4 = new Item("Salmon", "4004", "12/10/1992", 0.3, "Seafood", -2.0);

        maplist.put(item1,100);
        maplist.put(item2,100);
        maplist.put(item3,100);
        maplist.put(item4,100);

        Supplier_Manger masupplier=new Supplier_Manger();
        ContactPerson contactPerson = new ContactPerson("John Smith", "555-1234");
        NonDeliveringSupplier supplier_1 = new NonDeliveringSupplier("Supplier1 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_1.add_Items(item1,100,100);
        supplier_1.add_item_to_contract(item1.getName(),100,0.1);
        supplier_1.add_Items(item2,100,100);
        supplier_1.add_Items(item3,100,100);
        supplier_1.add_Items(item4,100,100);
        supplier_1.add_total_discount(0.5);
        supplier_1.add_item_to_contract(item1.getName(),20,0.1);;
        NonDeliveringSupplier supplier_2 = new NonDeliveringSupplier("Supplier2 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_2.add_Items(item1,10,90);
        supplier_2.add_Items(item2,100,100);
        supplier_2.add_Items(item3,100,100);
        supplier_2.add_Items(item4,100,100);
        supplier_2.add_total_discount(0.5);

        NonDeliveringSupplier supplier_3 = new NonDeliveringSupplier("Supplier2 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_3.add_Items(item2,100,100);
        supplier_3.add_Items(item3,100,100);
        supplier_3.add_Items(item4,100,100);
        supplier_3.add_total_discount(0.5);


        masupplier.getSuppliers().add(supplier_1);
        masupplier.getSuppliers().add(supplier_2);
        ordermanger.assing_Orders_to_Suppliers(maplist,masupplier,20);
        int num =ordermanger.getPending_for_apporval().get(0).getOrderNum();
        assertEquals("Supplier1 Inc.",ordermanger.getPending_for_apporval().get(0).getSupplier().getName());


    }
 //test 8
    @Test
    void order_move_to_history(){
        var ordermanger=new OrderManger();
        Map<Item,Integer> maplist =new HashMap<Item,Integer>();

        Item item1 = new Item("Apple", "1001", "12/10/1992", 0.5, "Fruits", 10.0);
        Item item2 = new Item("Milk", "2002", "12/10/1992", 1.0, "Dairy", 4.0);
        Item item3 = new Item("Bread", "3003", "12/10/1992", 0.8, "Bakery", 25.0);
        Item item4 = new Item("Salmon", "4004", "12/10/1992", 0.3, "Seafood", -2.0);

        maplist.put(item1,100);
        maplist.put(item2,100);
        maplist.put(item3,100);
        maplist.put(item4,100);

        Supplier_Manger masupplier=new Supplier_Manger();
        ContactPerson contactPerson = new ContactPerson("John Smith", "555-1234");
        NonDeliveringSupplier supplier_1 = new NonDeliveringSupplier("Supplier1 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_1.add_Items(item1,100,100);
        supplier_1.add_item_to_contract(item1.getName(),100,0.1);
        supplier_1.add_Items(item2,100,100);
        supplier_1.add_Items(item3,100,100);
        supplier_1.add_Items(item4,100,100);
        supplier_1.add_total_discount(0.5);
        supplier_1.add_item_to_contract(item1.getName(),20,0.1);;
        NonDeliveringSupplier supplier_2 = new NonDeliveringSupplier("Supplier2 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_2.add_Items(item1,10,90);
        supplier_2.add_Items(item2,100,100);
        supplier_2.add_Items(item3,100,100);
        supplier_2.add_Items(item4,100,100);
        supplier_2.add_total_discount(0.5);

        NonDeliveringSupplier supplier_3 = new NonDeliveringSupplier("Supplier2 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_3.add_Items(item2,100,100);
        supplier_3.add_Items(item3,100,100);
        supplier_3.add_Items(item4,100,100);
        supplier_3.add_total_discount(0.5);


        masupplier.getSuppliers().add(supplier_1);
        masupplier.getSuppliers().add(supplier_2);
        ordermanger.assing_Orders_to_Suppliers(maplist,masupplier,20);
        int num =ordermanger.getPending_for_apporval().get(0).getOrderNum();
        ordermanger.move_from_pending_to_approvel(num);
        ordermanger.moveFromApprovalToHistory(ordermanger.getApproval().get(0));
        assertEquals(1,ordermanger.getOrders_history().size());
    }
    @Test
    //test 9
    void manipule_supplier(){
        var ordermanger=new OrderManger();
        Map<Item,Integer> maplist =new HashMap<Item,Integer>();

        Item item1 = new Item("Apple", "1001", "12/10/1992", 0.5, "Fruits", 10.0);
        Item item2 = new Item("Milk", "2002", "12/10/1992", 1.0, "Dairy", 4.0);
        Item item3 = new Item("Bread", "3003", "12/10/1992", 0.8, "Bakery", 25.0);
        Item item4 = new Item("Salmon", "4004", "12/10/1992", 0.3, "Seafood", -2.0);

        maplist.put(item1,100);
        maplist.put(item2,100);
        maplist.put(item3,100);
        maplist.put(item4,100);

        Supplier_Manger masupplier=new Supplier_Manger();
        ContactPerson contactPerson = new ContactPerson("John Smith", "555-1234");
        NonDeliveringSupplier supplier_1 = new NonDeliveringSupplier("Supplier1 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        supplier_1.add_Items(item1,100,100);
        supplier_1.add_item_to_contract(item1.getName(),100,0.1);
        supplier_1.add_Items(item2,100,100);
        supplier_1.add_Items(item3,100,100);
        supplier_1.add_Items(item4,100,100);
        supplier_1.add_total_discount(0.5);
        supplier_1.add_item_to_contract(item1.getName(),20,0.1);;
        supplier_1.update_contact_person("omer" ,"020323");
        assertEquals("omer",supplier_1.getPerson().getName());
        supplier_1.remove_item(item1.getName());
        assertFalse(supplier_1.getItems().containsKey(item1));

}
//test 10
    @Test
    void test_only_supplier_exist(){
        Supplier_Manger masupplier=new Supplier_Manger();
        ContactPerson contactPerson = new ContactPerson("John Smith", "555-1234");
        NonFixedDaySupplier supplier_1 = new NonFixedDaySupplier(1,"Supplier1 Inc.", "123456789", 1, "S001", contactPerson, null, null);
        masupplier.getSuppliers().add(supplier_1);
        assertEquals(1,masupplier.getSuppliers().size());


    }
}