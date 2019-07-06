public class Automata {

		int finalState ;
		int [][] transtTab ;
		int nbState;
		
		Automata(int length){
			this.nbState = length;
			this.transtTab = new int [length][length];
		}

        public Automata CreateBasicAutomata(int action){
        	Automata basicAutomat = new Automata(2);
        	basicAutomat.transtTab[0][1]= action;
        	basicAutomat.finalState=1;
        	return basicAutomat;
		}
		/// Getters & Setters ///
		
		public int getFinalState() {
			return finalState;
		}

		public void setFinalState(int finalState) {
			this.finalState = finalState;
		}

		public int[][] getTranstTab() {
			return transtTab;
		}

		public void setTranstTab(int[][] transtTab) {
			this.transtTab = transtTab;
		}

		public int getNbState() {
			return nbState;
		}

		public void setNbState(int nbState) {
			this.nbState = nbState;
		}
	

}
