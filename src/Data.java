import java.util.ArrayList;
import java.util.HashMap;
public class Data {

     HashMap<String, String>  listVar ;
     HashMap<Integer, String>  listAct;
     int cpt;
     int blockNum;
     HashMap <Integer,Automata> Partaef ;
     ArrayList<String> vectAction;


//Constructor 
public Data() {

    this.listVar = new HashMap<String, String>();
    this.listAct= new HashMap<Integer, String>();
    this.Partaef = new HashMap <Integer,Automata>();
    this.vectAction=new ArrayList<String>();
    this.cpt=1;
    this.blockNum=1000 ;
            
        }

//Getters & Setters 

public HashMap<String, String> getListVar() {
    return listVar;
}
public void setListVar(HashMap<String, String> listVar) {
    this.listVar = listVar;
}
public HashMap<Integer, String> getListAct() {
    return listAct;
}
public void setListAct(HashMap<Integer, String> listAct) {
    this.listAct = listAct;
}
public int getCpt() {
    return cpt;
}
public void setCpt(int cpt) {
    this.cpt = cpt;
}
public int getBlockNum() {
    return blockNum;
}
public void setBlockNum(int blockNum) {
    this.blockNum = blockNum;
}
public HashMap<Integer, Automata> getPartaef() {
    return Partaef;
}
public void setPartaef(HashMap<Integer, Automata> partaef) {
    Partaef = partaef;
}
public ArrayList<String> getVectAction() {
    return vectAction;
}
public void setVectAction(ArrayList<String> vectAction) {
    this.vectAction = vectAction;
}



}