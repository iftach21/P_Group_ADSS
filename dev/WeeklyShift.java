import java.util.ArrayList;

public class WeeklyShift {
    private  int weekNUm;
    private int year;
    private int supernum;

    private Shift[] dayShift;
    private Shift[] nightShift;

    public int getWeekNUm() {
        return weekNUm;
    }

    public int getYear() {
        return year;
    }

    /**
     * Creating a weekly shift
     */
    public WeeklyShift(int weekNUm, int year,int supernum) {
        this.weekNUm = weekNUm;
        this.year = year;
        this.supernum = supernum;
        this.dayShift = new Shift[7];
        this.nightShift = new Shift[7];
        for(int i=0;i< 7;i++){
            dayShift[i] = new Shift(" "+i + " /" + weekNUm);
            nightShift[i] = new Shift(" "+i + " /" + weekNUm);
        }

    }
    public int getSupernum(){return this.supernum;}

    /**
     * Replacing an employee on shift with another employee
     */
    public  boolean changeWorker(Workers worker1,Workers worker2,int profindx, WindowType shiftTime){
        if(shiftTime.ordinal()<7){
            if(!this.dayShift[shiftTime.ordinal()].checkIfWorkerInShift(worker1.getId())){
                return false;}
            else {
                this.dayShift[shiftTime.ordinal()].removalWorker(worker1);
                this.dayShift[shiftTime.ordinal()].insertToShift(worker2,profindx);
            }
        }
        else {
            if(!this.nightShift[shiftTime.ordinal()-7].checkIfWorkerInShift(worker1.getId())){
                return false;}
            else {
                this.nightShift[shiftTime.ordinal()-7].removalWorker(worker1);
                this.nightShift[shiftTime.ordinal()-7].insertToShift(worker2,profindx);
            }
        }

        return true;
    }
    /**
     * Returns a string of all employees when employed
     */
    public  StringBuilder printSpesific(){
        StringBuilder pWeelShift= new StringBuilder();
        for (int i = 0; i < 7; i++){
            pWeelShift.append(" Day ").append(i+1).append(": ").append(dayShift[i].printShift()).append(",");
            pWeelShift.append(" Night ").append(i+1).append(": ").append(nightShift[i].printShift());
        }
        return pWeelShift;
    }

    /**
     * Returns the amount of shifts an employee has do
     */
    public int howMuchShiftWorkerDid(int id){
        //given id tells hm shift did the worker did
        int countShift=0;
        for(Shift dShift:this.dayShift){
            if(dShift.checkIfWorkerInShift(id)){
                countShift++;
            }
        }
        for(Shift nShift:this.nightShift) {
            if (nShift.checkIfWorkerInShift(id)) {
                countShift++;
            }
        }
        return countShift;

    }
    /**
     * Add an employee to a shift
     */
    public void addworkertoshift(Workers newWorker, WindowType shiftTime, int profindx) {
        if(shiftTime.ordinal()<7){
            this.dayShift[shiftTime.ordinal()].insertToShift(newWorker,profindx);
        }
        else {
            this.nightShift[shiftTime.ordinal()-7].insertToShift(newWorker,profindx);
        }
    }
    public void addreqtoshift(WindowType shiftTime, int profindx,int hm){
        if(shiftTime.ordinal()<7){
            this.dayShift[shiftTime.ordinal()].addreq(profindx,hm);
        }
        else {
            this.nightShift[shiftTime.ordinal()-7].addreq(profindx,hm);
        }
    }


    /**
     * Checks if a worker is on shift
     */
    public boolean checkifworkallready(int id, WindowType shiftTime){
        if(shiftTime.ordinal()<7){
           return this.dayShift[shiftTime.ordinal()].checkIfWorkerInShift(id);
        }
        else {
            return this.nightShift[shiftTime.ordinal()-7].checkIfWorkerInShift(id);
        }
    }
    public void setTimeForShift(String newtime, WindowType st){
        if(st.ordinal()<7){
             this.dayShift[st.ordinal()].setStartTime(newtime);
        }
        else {
             this.nightShift[st.ordinal()-7].setStartTime(newtime);
        }
    }


}//end class