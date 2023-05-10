package Domain.Employee;

import Data.WeeklyShiftDAO;
import Data.WorkersDAO;
import Domain.Enums.TempLevel;
import Domain.Enums.WindowType;
import Domain.Enums.WindowTypeCreater;
import Domain.Enums.weightType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeeklyShiftAndWorkersManager {
    private static WeeklyShiftAndWorkersManager Instance = null;
    private final WorkersDAO workersDAO;
    private final WeeklyShiftDAO weeklyShiftDAO;



    private WeeklyShiftAndWorkersManager() throws SQLException {
        workersDAO = WorkersDAO.getInstance();
        weeklyShiftDAO = WeeklyShiftDAO.getInstance();
    }
    public static WeeklyShiftAndWorkersManager getInstance() throws SQLException {
        if(Instance==null){Instance = new WeeklyShiftAndWorkersManager();}
        return Instance;
    }
    //function for the "1" option in menu
    public void createnewweeklyshift(int weeknum,int year,int supernum) throws SQLException {
        weeklyShiftDAO.add(new WeeklyShift(weeknum,year,supernum));
    }

    //function for the "2"
    public void addtoexistingweeklyshift(int weeknum, int year, int supernum, WindowType wt, int id , int profindx) throws SQLException {
        WeeklyShift weeklyShift = this.getweeklyshift(weeknum,year,supernum);
        weeklyShift.addworkertoshift(this.getworkerbyid(id),wt,profindx);
        this.weeklyShiftDAO.update(weeklyShift);

    }

    //function for the "3"
    public void switchemployeeinashift(int weeknum,int yearnum,int supernum, WindowType wt,int prof, int id1, int id2) throws SQLException {
        WeeklyShift weeklyShift = this.getweeklyshift(weeknum,yearnum,supernum);
        weeklyShift.changeWorker(this.getworkerbyid(id1),this.getworkerbyid(id2),prof,wt);
        this.weeklyShiftDAO.update(weeklyShift);
    }
    //function for the "4"
    public void fireemployee(int id){
        this.workersDAO.delete(id);
    }

    //function for the "5"
    public void addemployee(int id,String name,String contract,String start_date,int wage,int phoneNUM, String personalinfo,int bankNum){
        Workers w = new Workers(id,name,contract,start_date,wage,phoneNUM,personalinfo,bankNum);
        workersDAO.add(w);
    }
    //function for the "6"
    public void addwagetoemployee(int id,int addedwage){
        Workers worker = this.getworkerbyid(id);
        worker.setWage(addedwage + this.getworkerbyid(id).getWage());
        this.workersDAO.update(worker);
    }
    //function for the "7"
    public int getwageforemployee(int id, int week,int year){
        Workers worker = this.getworkerbyid(id);
        int sumToReturn = 0;
        List <WeeklyShift> weeklyShiftList = this.weeklyShiftDAO.getWeeklyShiftList(week,year);
        for(int i=0; i<weeklyShiftList.size();i++) {
            if(weeklyShiftList.get(i).getWeekNUm()==week && weeklyShiftList.get(i).getYear()==year){
                int hm =  weeklyShiftList.get(i).howMuchShiftWorkerDid(id);
                sumToReturn+= hm * worker.getWage();
                }
            }
        return sumToReturn;
        }

    //function for the "8"
    public void changeemployeecontract(int id,String contract){
        Workers worker = this.getworkerbyid(id);
        worker.setContract(contract);
        this.workersDAO.update(worker);
    }
    //function for the "9"
    public void updateemployeesbank(int id,int newbanknum) {
        Workers worker = this.getworkerbyid(id);
        worker.setBankNum(newbanknum);
        this.workersDAO.update(worker);
    }

    //function for the "10"
    public void addavilableforemployee(int id, WindowType wt){
        Workers worker = this.getworkerbyid(id);
        worker.addwindow(wt);
        this.workersDAO.update(worker);
    }

    //function for the "11"
    public void addnewproforemployee(int id, int indx) {
        Workers worker = this.getworkerbyid(id);
        if(worker.amIDriver()){return;}

        //0=manager
        //1=cashier
        //2=stoke
        //3=security
        //4=cleaning
        //5=shelfstoking
        //6= generalworker

        worker.addprof(indx);
        this.workersDAO.update(worker);
    }
    //function for the "12"
    public void removeprofforemployee(int id,int pros){
        Workers worker = this.getworkerbyid(id);
        if(worker.amIDriver()){return;}
        worker.removePro(pros);
        this.workersDAO.update(worker);
    }

    //function for the "13"
    public void removeavalbleforemployee(int id,WindowType wt){
        Workers worker = this.getworkerbyid(id);
        worker.removewindow(wt);
        this.workersDAO.update(worker);
    }
    //for function "14"
    public void printweeklyshift(int weeknum,int year,int supernum) throws SQLException {
        System.out.println(this.getweeklyshift(weeknum,year,supernum).printSpesific());
    }
    public Workers getworkerbyid(int id) {
            return this.workersDAO.get(id);
    }
    public WeeklyShift getweeklyshift(int week,int year,int supernum) throws SQLException {
        return this.weeklyShiftDAO.get(week,year,supernum);
    }
    public void printall(){
        List<Workers> list = this.workersDAO.getAllworkerslist();
        for(int i=0;i<list.size();i++){
            list.get(i).print();
        }
    }

    public void printAllWorkersWhoCanWork(int prof,int daynum, String don){
        WindowTypeCreater wc = new WindowTypeCreater();
        List <Workers> allworkerslist = this.workersDAO.getAllworkerslist();
        for(int i=0;i<allworkerslist.size();i++){
            if(allworkerslist.get(i).caniworkatprofindx(prof) && allworkerslist.get(i).canIworkat(wc.getwidowtype(daynum,don))){
                allworkerslist.get(i).print();
            }
        }
    }

    public void addreqtoweeklyshift(int weeknum, int year,int supernum, int daynum, String don,int prof,int hm) throws SQLException {
        WindowTypeCreater wc = new WindowTypeCreater();
        WeeklyShift weeklyShift = this.getweeklyshift(weeknum,year,supernum);
        weeklyShift.addreqtoshift(wc.getwidowtype(daynum,don),prof,hm);
        this.weeklyShiftDAO.update(weeklyShift);
    }

    public void settimeforweeklyshift(int weeknum, int year,int supernum, int daynum,String don, String startime) throws SQLException {
        WindowTypeCreater wc = new WindowTypeCreater();
        WeeklyShift weeklyShift = this.getweeklyshift(weeknum,year,supernum);
        weeklyShift.setTimeForShift(startime,wc.getwidowtype(daynum,don));
        this.weeklyShiftDAO.update(weeklyShift);
    }
    public void printweeklyreq(int weeknum, int year,int supernum) throws SQLException {
        getweeklyshift(weeknum,year,supernum).printweeklyreq();

    }

    //while giving the right info giving back if there is a stock viable.
    public List<WindowType> doIHaveStokeForTheShipment(int weeknum, int year,int supernum,WindowType wt) throws SQLException {
        List<WindowType> ans = new ArrayList<>();
        WeeklyShift weeklyShift = this.weeklyShiftDAO.get(weeknum,year,supernum);
        for(WindowType windowType: WindowTypeCreater.getAllWindowTypes()) {
            if (weeklyShift.doIhaveAStoke(windowType)) {
                ans.add(windowType);
            }
        }

        return ans;
    }

    public List<Driver> giveMeViableDrivers(int weekNum, int yearNum, WindowType wt) throws SQLException {
        return this.weeklyShiftDAO.get(weekNum,yearNum,0).giveAllDrivers(wt);
    }

    //adding a new driver:
    public void addNewDriver(int id, String name, String contract, String start_date, int wage, int phoneNUM, String personalinfo, int bankNum, TempLevel tl, weightType wt){
        Driver new_w = new Driver(id, name, contract, start_date, wage, phoneNUM, personalinfo, bankNum, tl, wt);
        this.workersDAO.add(new_w);
    }

    //setting the personal info
    public void setPersonalinfo(int id,String setPersonalinfo ){
        Workers workers = getworkerbyid(id);
        workers.setPersonalinfo(setPersonalinfo);
        this.workersDAO.update(workers);
    }





}

