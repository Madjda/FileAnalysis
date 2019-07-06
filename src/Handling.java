import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.io.*;

public class Handling {

    Data data ;
// Constructor
    public Handling ( Data d)
    {
        this.data=d ;
    }

//Display Content

void displayVariables (  HashMap<String,String> h )
{
  Set set2 =h.entrySet();
  Iterator iterator2 = set2.iterator();
  while(iterator2.hasNext()) {
      Map.Entry mentry2 = (Map.Entry)iterator2.next();
      System.out.print("Variable is: "+mentry2.getKey() + " & its type is: ");
      System.out.println(mentry2.getValue());
   }
  }

   void displayActions (  HashMap<Integer,String> h )
{
  Set set3= h.entrySet();
       Iterator iterator3 = set3.iterator();
       while(iterator3.hasNext()) {
          Map.Entry mentry3 = (Map.Entry)iterator3.next();
          System.out.print("indice is: "+mentry3.getKey() + " & its action is: ");
          System.out.println(mentry3.getValue());
       }
}

void displayFileContent (  ArrayList<String> l)
{ int i ;
  for(i=0;i<l.size();i++)
  {
    System.out.print(l.get(i)+" ");
  }
  
}



// shift 
   void replaceBlock(ArrayList<String> vect,String pt,int db ,int fn ){
        int cpt=0;
        boolean yes = true ;
        vect.add(db,pt);
        while ( yes ) {
        vect.remove(db+1);
        cpt ++ ;
        if (cpt==fn-db+1) yes= false ;
        
    }
}

// Add to the hashMap the variable with its type
 void addVar ( String var , String typ )
{ 
     if (data.getListVar().get(var)== null ) data.getListVar().put(var, typ);
     else  System.out.println("Error ! This name of variable already exist");

}
//
void addAct ( int indice , String action )
{ 
     data.getListAct().put(indice, action);

}

int generatePartAutom(String block){
int i ;
     Automata temp1 = new Automata(0);
     Automata temp2 = new Automata(0);
      System.out.println(block);

     Operation op = new Operation();

     String toks[] = block.split("((?<=;)|(?=;)|(?=[+])|(?<=[+])|(?<=~)|(?=~)|(?<=[}])|(?=[}])|(?<=[{])|(?=[{]))") ;
     /** to get the first block **/
     if( Integer.parseInt(toks[1]) < 1000) 
             //// basic table --> we need to generate a new one
               temp1 = temp1.CreateBasicAutomata(Integer.parseInt(toks[1]));
         else 
             /// Get the Transation table from the hashmap
           temp1 = data.getPartaef().get(Integer.parseInt(toks[1]));
               
     /** the left side of block **/
     for( i=3 ;i< toks.length-1 ; i=i+2 ) 
     { 
       
       if( Integer.parseInt(toks[i]) < 1000)
               //// basic table --> we need to generate a new one
       { temp2 = temp2.CreateBasicAutomata(Integer.parseInt(toks[i]));
       
       }
       
           else 
               /// Get the Transation table from the hashmap
             
             temp2 = data.getPartaef().get(Integer.parseInt(toks[i]));
       
       
       // operations
       System.out.println("the operation "+toks[i-1]);
       
       
       switch(toks[i-1]) {
       case "+":  temp1 = op.alternative(temp1, temp2); break ;
       case "~": temp1 = op.choice(temp1, temp2); break ;
       case ";": temp1 = op.sequence(temp1, temp2); break ;
       }
       
     }
     //test 
     for (int l=0;l<temp1.getNbState();l++){

        for (int k=0;k<temp1.getNbState();k++){
          System.out.print("  "+temp1.transtTab [l][k]+"   ");
        }
        System.out.println();

      }
     /** add the partial transition table **/
       data.getPartaef().put(data.getBlockNum(), temp1);
       data.setBlockNum(data.getBlockNum()+1);;
      
    
    return data.getBlockNum()-1 ;
}
 int actionRestantees()
{
int n=0;
for(int jj=0;jj< data.getVectAction().size();jj++) 
  if ( !(data.getVectAction().get(jj).equals("{")) && !(data.getVectAction().get(jj).equals("}")) && !(data.getVectAction().get(jj).equals("+")) 
  && !(data.getVectAction().get(jj).equals(";")) && !(data.getVectAction().get(jj).equals("~")) ) 
    n++;
return n;
}



///Global


 void generateGlobalAutom()
{
  int nbAction=0;
  //////////////initilalisation
    data.getVectAction().add(0,"{");
    data.getVectAction().add(data.getVectAction().size(),"}");
    nbAction=actionRestantees();
  ///////////////////////////////////
  System.out.println("generateGlobalAutom");
boolean bool=false,continue2=true;
int taille=data.getVectAction().size(),i=0,cpt=0,indicedbt=0;
StringBuilder temp=new StringBuilder();
while (nbAction!=1)
{
  while (i<taille)
  {
    
    if(data.getVectAction().get(i).equals("{"))
    {
   
      if(! (data.getVectAction().get(i+1).equals("{") ) )
      {
        
        cpt=i+3;
        indicedbt=i;
        temp.append("{"+data.getVectAction().get(i+1));
       
        if( data.getVectAction().get(i+2).equals("+") || data.getVectAction().get(i+2).equals(";")  || data.getVectAction().get(i+2).equals("~") )
        {
          temp.append(data.getVectAction().get(i+2));
        }
        else if(data.getVectAction().get(i+2).equals("}"))
        {
          temp.append("}");
          continue2=false;System.out.println("te");
          replaceBlock(data.getVectAction(),Integer.toString(generatePartAutom(temp.toString())),indicedbt,i+2 );
          taille=data.getVectAction().size();
          bool=true;
        }
        while(continue2)
        {
          if(data.getVectAction().get(cpt).equals("{"))
          {
            continue2=false;
          }
          else
          {
            temp.append(data.getVectAction().get(cpt));cpt++;
            if(data.getVectAction().get(cpt-1).equals("}"))
            {
              replaceBlock(data.getVectAction(),Integer.toString(generatePartAutom(temp.toString())),indicedbt,cpt-1 );nbAction=actionRestantees();
              continue2=false;bool=true;
              taille=data.getVectAction().size();
            }
          }
        }
        continue2=true;
        temp.setLength(0); i=(bool)? i+1 : cpt -1; bool=false;
      }
      //
    } 
    i++;
  }
  i=0;
}


}



 }











