
public class Operation {
	
		protected Automata choice(Automata a ,Automata b){

	    int i,j;
	    
	    //final state of automata1 (fin1)  it'll be the final state of the new automata
	     
	    Automata temp;
		temp = new Automata(a.getNbState() + b.getNbState() -1);
	     //the new initial state 
	  
	         for(j=0;j<a.getNbState();j++)    // new initial state S0  linked with S0 of 1st automata
	         {
	             temp.transtTab[0][j]=a.transtTab[0][j];  
	         }
	         for(j=a.getNbState();j<a.getNbState()+b.getNbState()-1;j++)  // same initial state S0  linked with S0 of 2nd automata
	         {
	             temp.transtTab[0][j]=b.transtTab[0][j-a.getNbState()+1];
	             
	         }
	         
	         
	         //The rest - final state         
	         
	         for(i=1;i<a.getNbState()+b.getNbState()-1;i++)
	         {
	             for(j=0;j<a.getNbState()+b.getNbState()-1;j++)
	             {
	                 if (i<a.getNbState())   //1st automata
	                 {    
	                                 
	                     if (j<a.getNbState())  temp.transtTab[i][j]=a.transtTab[i][j];   
	                     
	                     else  temp.transtTab[i][j]=0;                           
	                     
	                 }
	                 else // 2nd automata 
	                 {
	                         if  (j<a.getNbState()) temp.transtTab[i][j]=0;
	                         
	                         else { 
	                             temp.transtTab[i][j]=b.transtTab[i-(a.getNbState()-1)][j-(a.getNbState()-1)];
	                             }
	                 }    
	                 
	             }
	         }
	         
	         
	     //The final state :
	         
	         for(i=0;i<a.getNbState() + b.getNbState() -1;i++)
	         {
	             for(j=0;j<a.getNbState() + b.getNbState() -1;j++)
	             {
	                 if ( (j==b.getFinalState()+(a.getNbState()-1)) && (temp.transtTab[i][j]!=0) ) 
	                 {
	                    temp.transtTab[i][a.getFinalState()]=temp.transtTab[i][j]; 
	                    temp.transtTab[i][j]=0 ;

	                 }
	                 
	                if (j > b.getFinalState()+(a.getNbState()-1)) temp.transtTab[i][j-1]=temp.transtTab[i][j];
	                if (i > b.getFinalState()+(a.getNbState()-1))  temp.transtTab[i-1][j]=temp.transtTab[i][j] ;
	             }
	         }
			temp.finalState = a.getFinalState();
	        temp.nbState--;
			return temp;    
	         
	        
	    
	}
	
	
		protected Automata sequence(Automata a ,Automata b){

        int i,j;
        Automata temp;
		temp = new Automata(a.getNbState() + b.getNbState());
        
        /*fin1 represente l'etat final du premier automate en general 
        c'est le dernier etat */ 
                
        for(i=0;i<temp.getNbState()-1;i++){
            for(j=0;j<temp.getNbState()-1;j++){
                if (i<a.getNbState()) {
                    if (j<a.getNbState()) temp.transtTab[i][j]=a.transtTab[i][j];
                    else {if (i==a.getFinalState())  temp.transtTab[a.getFinalState()][j]=b.transtTab[0][j-a.getNbState()+1];   
                          else temp.transtTab[i][j]=0;}
                          
                    
                }else {
                    if (j==a.getFinalState()) 
                    	temp.transtTab[i][a.getFinalState()]=b.transtTab[i-a.getNbState()+1][0]; 
                    else {if  (j<a.getNbState()-1) temp.transtTab[i][j]=0;
                          else temp.transtTab[i][j]=b.transtTab[i-a.getNbState()+1][j-a.getNbState()+1];}
                }    
                
            }
        }
        temp.nbState --;
        temp.setFinalState (temp.getNbState()-1);
        return temp;
        
    }    
    

	
	 protected Automata alternative(Automata a ,Automata b){
         Automata temp;
		 temp = new Automata(a.getNbState() + b.getNbState());
		 
		 /** first block **/
		 for(int i=0;i< a.getNbState();i++){
			 for(int j=0;j< a.getNbState();j++){

			 temp.transtTab[i][j] = a.transtTab[i][j];
			 
			 }
		 }
		 
		 /** to link the new intial state with the b bloc **/
		 for (int i=1;i<b.getNbState();i++){
		 temp.transtTab[0][a.getNbState()+i-1]=b.transtTab[0][i];
		 b.transtTab[0][i]=0;
		 }
		 /** second block **/
		 for(int i=1;i< b.getNbState();i++){
			 for(int j=1;j< b.getNbState();j++){


			 temp.transtTab[a.getNbState()+i-1][a.getNbState()+j-1] = b.transtTab[i][j];
			 }
		 }
		 
		 
		 /** les états finaux **/
		 
			 temp.transtTab[a.getFinalState()][temp.getNbState()-1]=-1;
			 temp.transtTab[b.getFinalState()+a.getNbState()-1][temp.getNbState()-1]=-1;
     		 temp.setFinalState(temp.getNbState()-1);
		 
		 return temp;
	 }
	 
	
	
	
}
