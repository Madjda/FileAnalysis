import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class SaveAef {
	
	 FileWriter out ;
	 BufferedWriter buf ;	
	
	 void saveAutomata ( Automata aef ) //  aef is the global automata   /* referenced by the unique elmnt in vetor after global void */ 
	 {
		 int i ,j ;
		 int [] [] m= aef.transtTab ;
         try {
        	 out = new FileWriter(FileUploaderController.dirPath+"\\automata.aef");
        	
             buf = new BufferedWriter(this.out);
				 buf.write( "The Global autamata transition table :") ;
				 buf.newLine();
				 
				 buf.write(" // both line & comlumn represent the states and the cells the actions") ;
				 buf.newLine() ;
			/******************************/

			 	 for(i=0;i<m.length ;i++)
	              {
						if (i< 10) buf.write("S"+i+" |") ;
						else buf.write("S"+i+"|") ;
				         for(j=0;j<m.length;j++)
				         {   
							 
				        	 buf.write(Integer.toString(m[i][j])+" ") ;
				    		 
				         }
	                     buf.newLine() ;
	              } 
			
		      } catch (IOException e) {
		
		     	e.printStackTrace();
		      }
         finally {

 			try {

 				if (this.buf!= null)
 					this.buf.close();

 				if (this.out != null)
 					this.out.close();

 			} catch (IOException ex) {

 				ex.printStackTrace();

 			}

		
			
	
		
		 
	 }
	 }
	 }

