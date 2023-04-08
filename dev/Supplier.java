import java.util.Map;

public abstract class Supplier {
    private String name;
    private String business_id;
    private String Payment_method;
    private String Suplier_ID;
    private Contact_Person person;
    private Contract contract;
    private Map<Item,Pair<Integer,Float>>items;


    public Supplier(String name, String business_id, String payment_method, String suplier_ID, Contact_Person person, Contract contract, Map<Item, Pair<Integer,Float>> items) {
        this.name = name;
        this.business_id = business_id;
        Payment_method = payment_method;
        Suplier_ID = suplier_ID;
        this.person = person;
        this.contract = contract;
        this.items = items;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getPayment_method() {
        return Payment_method;
    }

    public void setPayment_method(String payment_method) {
        Payment_method = payment_method;
    }

    public String getSuplier_ID() {
        return Suplier_ID;
    }

    public void setSuplier_ID(String suplier_ID) {
        Suplier_ID = suplier_ID;
    }

    public Contact_Person getPerson() {
        return person;
    }

    public void setPerson(Contact_Person person) {
        this.person = person;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Map<Item,Pair<Integer,Float>> getItems() {
        return items;
    }

    public void setItems(Map<Item, Pair<Integer,Float>> items) {
        this.items = items;
    }
    public void add_Items(Item item,int num,float cost) {
        Pair<Integer,Float> pair=new Pair<Integer,Float>(num,cost);
        items.put(item,pair);
    }
    public  void add_item_to_contract(String name,int num,double discount){
        for(Item item : items.keySet()){
            if(item.getName().equals(name)){

            this.contract.add_to_contract(item,num,discount);}
    }}
    public void update_contact_person(String name ,String number){
        Contact_Person contact_person=new Contact_Person(name,number);
        this.person=contact_person;
    }
    public void add_total_discount(double discount){
        this.contract.total_discount=discount;
    }
    public  void remove_item(String item_to_remove){
        for(Item item :items.keySet()){
            if(item.getName().equals(item_to_remove)){
            this.contract.remove_by_item(item);
            this.items.remove(item);}
        }
    }


}