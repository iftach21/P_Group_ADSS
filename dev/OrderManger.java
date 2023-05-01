import java.util.*;

public class OrderManger {

    private List<Order> pending_for_apporval;
    private List<Order> approval;
    private List<Order> orders_history;

    public List<Order>Period_orders;






    public List<Order> getApproval() {
        return approval;
    }

    public void setApproval(List<Order> approval) {
        this.approval = approval;
    }

    public List<Order> getPending_for_apporval() {
        return pending_for_apporval;
    }

    public void setPending_for_apporval(List<Order> pending_for_apporval) {
        this.pending_for_apporval = pending_for_apporval;
    }


    public List<Order> getOrders_history() {
        return orders_history;
    }

    public void setOrders_history(List<Order> orders_history) {
        this.orders_history = orders_history;
    }

    public OrderManger() {
        this.orders_history=new ArrayList<Order>();
        this.approval=new ArrayList<Order>();
        this.pending_for_apporval=new ArrayList<Order>();
        this.Period_orders=new ArrayList<Order>();

    }
    public void move_from_pending_to_approvel(int order_num) {
        Iterator<Order> iter = pending_for_apporval.iterator();
        while (iter.hasNext()) {
            Order order = iter.next();
            if (order.getOrderNum().equals(order_num)) {
                iter.remove();
                approval.add(order);
            }
        }
    }

    public void moveFromApprovalToHistory(Order order) {
        if (order != null && approval.contains(order)) {
            approval.remove(order);
            orders_history.add(order);
        }
    }


    public Boolean assing_Orders_to_Suppliers(Map<Item,Integer> itemlist, Supplier_Manger manger,int number_store){
        //if there is no supplier do nothing
        if(manger == null){
            return false;
        }
        manger.sort_supplier_by_deliver_days();
        //to find the min supplier(in case of one supplier have it all)
        Supplier min_sup=null;
        Order min_order = null;
        float min_cost = 0;
        //to cheak who have the max items in each call to the function
        int number_of_item=0;
        int trigger=0;
        int no_match=0;
        int min_number_days;


        for(Supplier supplier: manger.getSuppliers()){

            Set<Item> commonElements = new HashSet<>(supplier.getItems().keySet());
            commonElements.retainAll(itemlist.keySet());

            int numOfCommonElements = commonElements.size();

            if(numOfCommonElements>0 && (number_of_item<=numOfCommonElements)){

                for(Item item:itemlist.keySet()){
                    if(supplier.getItems().containsKey(item)){

                    if(itemlist.get(item)>supplier.getItems().get(item).getFirst()){
                        trigger=1;
                    }

                }}

                //if the supplier dont have the right amount;
                if(trigger==1){
                    continue;
                }
                if(min_sup == null){
                    no_match=1;
                    min_sup=supplier;}

                //make a new order to be cheak if there is better
                Order order = new Order(null,supplier,0,number_store);
                for(Item item :itemlist.keySet()){
                    if(supplier.getItems().containsKey(item)){
                    double discount = 1;

                    int amount=itemlist.get(item);
                    float base_price = supplier.getItems().get(item).getSecond();
                    int new_amount=0;
                    float cost = 0;
                    //if the supplier give a discount on the item serch for the biggest amount
                    if(supplier.getContract().itemsMapDiscount.containsKey(item)){
                        //cheak how much max amount have a discount from the curr amount
                        for(int i = 0; i < amount ;i++){
                            if(supplier.getContract().itemsMapDiscount.get(item).containsKey(amount-i)){
                                discount = supplier.getContract().itemsMapDiscount.get(item).get(amount-i);
                                new_amount = amount-i;


                                break;
                            }


                        }
                    }
                    //add the item to the total price o
                    cost = (float) (new_amount*base_price*discount + ((amount-new_amount)*base_price));
                    order.add_item(item,amount,cost);


                }




            }
               if(number_of_item < numOfCommonElements){
                   number_of_item = numOfCommonElements;
                   min_sup = supplier;
                   min_cost = order.getCost();
                   min_order = order;
               }
               else {
                   if(min_cost>order.getCost()){
                       min_sup=supplier;
                       min_cost=order.getCost();
                       min_order=order;

                   }
               }





            }}
        if(no_match==0){
            return false;
        }
        //add it to the order's
        //add it to the order's
        pending_for_apporval.add(min_order);
        Iterator<Item> iter = itemlist.keySet().iterator();
        while (iter.hasNext()) {
            Item item = iter.next();
            if (min_sup.getItems().containsKey(item)) {
                iter.remove();
            }
        }
        //reucsive call to cheak to supplie the other items
        if(itemlist.keySet().size()>0){
            assing_Orders_to_Suppliers(itemlist,manger,number_store);
        }
        else{
            return true;
        }
        return true;




    }



    public void print_all_orders_num(){
        System.out.println("pending:");
        for(Order order: pending_for_apporval){
            System.out.println(order.getOrderNum());
        }
        System.out.println("approved:");
        for(Order order: approval){
            System.out.println(order.getOrderNum());
        }
        System.out.println("history:");
        for(Order order: orders_history){
            System.out.println(order.getOrderNum());
        }


    }
    public void print_all_orders_details(){
        System.out.println("pending:");
        for(Order order: pending_for_apporval){
            order.print_order_detail();
        }
        System.out.println("approved:");
        for(Order order: approval){
            order.print_order_detail();
        }
        System.out.println("history:");
        for(Order order: orders_history){
            order.print_order_detail();
        }
    }
    public boolean period_order(Supplier supplier,Map<Item,Integer> list_items, int store_num) {
        Map<Item, Pair<Integer, Float>> maplist;
        Order order = new Order(null, supplier, 0, store_num);
        for (Item item : list_items.keySet()) {
            if (supplier.getItems().containsKey(item)) {
                double discount = 1;

                int amount = list_items.get(item);
                float base_price = supplier.getItems().get(item).getSecond();
                int new_amount = 0;
                float cost = 0;
                //if the supplier give a discount on the item serch for the biggest amount
                if (supplier.getContract().itemsMapDiscount.containsKey(item)) {
                    //cheak how much max amount have a discount from the curr amount
                    for (int i = 0; i < amount; i++) {
                        if (supplier.getContract().itemsMapDiscount.get(item).containsKey(amount - i)) {
                            discount = supplier.getContract().itemsMapDiscount.get(item).get(amount - i);
                            new_amount = amount - i;


                            break;
                        }


                    }
                }
                //add the item to the total price o
                cost = (float) (new_amount * base_price * discount + ((amount - new_amount) * base_price));
                order.add_item(item, amount, cost);


            }
        }
        this.Period_orders.add(order);
        return true;
    }




}





