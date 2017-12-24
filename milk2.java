
/*
ID: angusjy1
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;

class milk2 {
	public static ArrayList<Integer> blockStarts = new ArrayList<Integer>();
	public static ArrayList<Integer> blockEnds = new ArrayList<Integer>();
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());
						  // Get line, break into tokens
    int numOfCows = Integer.parseInt(st.nextToken());    // first integer
    ArrayList<Integer> startTimes = new ArrayList();
    ArrayList<Integer> endTimes = new ArrayList();
    while(true){
    	try{
    		st = new StringTokenizer(f.readLine());
    	}catch(NullPointerException np){
    		break;
    	}
    	startTimes.add(Integer.parseInt(st.nextToken()));
    	endTimes.add(Integer.parseInt(st.nextToken()));
    }
    ArrayList<Integer> blockStarts = new ArrayList();
    ArrayList<Integer> blockEnds = new ArrayList();
    blockStarts.add(startTimes.get(0));
    blockEnds.add(endTimes.get(0));
    int originalSize = blockStarts.size();
    for(int ind = 1; ind < startTimes.size(); ind++){
    	originalSize = blockStarts.size();
    	boolean merged = false;
    	for(int ind2 = 0; ind2 < originalSize; ind2++){
    		if(startTimes.get(ind)==blockStarts.get(ind2)&&endTimes.get(ind)==blockEnds.get(ind2)){
    			merged=true;
    		}else if(startTimes.get(ind)<=blockStarts.get(ind2)&&endTimes.get(ind)>=blockEnds.get(ind2)){
    			blockStarts.set(ind2, startTimes.get(ind));
    			blockEnds.set(ind2, endTimes.get(ind));
    			merged = true;
    		}else if(startTimes.get(ind)<=blockStarts.get(ind2)&&endTimes.get(ind)>=blockStarts.get(ind2)){
    			blockStarts.set(ind2, startTimes.get(ind));
    			merged = true;
    		}else if(startTimes.get(ind)<=blockEnds.get(ind2)&&endTimes.get(ind)>=blockEnds.get(ind2)){
    			blockEnds.set(ind2, endTimes.get(ind));
    			merged = true;
    		}
    	}
    	if(merged==false){
    		blockStarts.add(startTimes.get(ind));
			blockEnds.add(endTimes.get(ind));
    	}
    }
    milk2.recursiveSort(blockStarts, 0, blockStarts.size()-1);
    milk2.recursiveSort(blockEnds, 0, blockEnds.size()-1);
    for(int ind = 0; ind < blockStarts.size(); ind++){
    	originalSize = blockStarts.size();
    	for(int ind2 = ind+1; ind2 < blockStarts.size(); ind2++){
    		if(blockStarts.get(ind)==blockStarts.get(ind2)&&blockEnds.get(ind)==blockEnds.get(ind2)){
    			blockStarts.remove(ind2);
    			blockEnds.remove(ind2);
    			ind2--;
    		}else if(blockStarts.get(ind)<=blockStarts.get(ind2)&&blockEnds.get(ind)>=blockEnds.get(ind2)){
    			blockStarts.remove(ind2);
    			blockEnds.remove(ind2);
    			ind2--;
    		}else if(blockStarts.get(ind)>=blockStarts.get(ind2)&&blockEnds.get(ind)<=blockEnds.get(ind2)){
    			blockStarts.set(ind, blockStarts.get(ind2));
    			blockEnds.set(ind, blockEnds.get(ind2));
    			blockStarts.remove(ind2);
    			blockEnds.remove(ind2);
    			ind2--;
    		}else if(blockStarts.get(ind)<=blockStarts.get(ind2)&&blockEnds.get(ind)>=blockStarts.get(ind2)&&blockEnds.get(ind)<=blockEnds.get(ind2)){
    			blockEnds.set(ind, blockEnds.get(ind2));
    			blockStarts.remove(ind2);
    			blockEnds.remove(ind2);
    			ind2--;
    		}else if(blockStarts.get(ind)>=blockEnds.get(ind2)&&blockStarts.get(ind)<=blockEnds.get(ind2)&&blockEnds.get(ind)>=blockEnds.get(ind2)){
    			blockStarts.set(ind, blockStarts.get(ind2));
    			blockStarts.remove(ind2);
    			blockEnds.remove(ind2);
    			ind2--;
    		}
    	}
    	/**for(int indZ = 0; indZ < blockStarts.size(); indZ++){
    	out.println(blockStarts.get(indZ)+"|"+blockEnds.get(indZ));
    }
	out.println("-----");*/
    	//System.out.println(merged);
    }


    /**for(int ind = 0; ind < blockStarts.size(); ind++){
    	out.println(blockStarts.get(ind)+"|"+blockEnds.get(ind));
    }*/
    
    int smallestNum = Integer.MAX_VALUE;
    int largestNum = Integer.MIN_VALUE;
    boolean decrement = false;
    
    int greatestBetween = 0;
    int greatestDifference = 0;
    
    /**for(int ind = 0; ind < blockStarts.size(); ind++){
    	out.println(blockStarts.get(ind)+"|"+blockEnds.get(ind));
    }*/
    
    for(int ind = 0; ind < blockStarts.size(); ind++){
    	if(blockEnds.get(ind)-blockStarts.get(ind)>=greatestDifference){
    		greatestDifference=blockEnds.get(ind)-blockStarts.get(ind);
    		System.out.println(blockStarts.get(ind)+" ZZ "+blockEnds.get(ind));
    	}
    }
    
    
    System.out.println("Here");
    for(int ind = 0; ind < blockStarts.size()-1; ind++){
    		if(blockEnds.get(ind)<blockStarts.get(ind+1)){
    			if(blockStarts.get(ind+1)-blockEnds.get(ind)>greatestBetween){
    				greatestBetween=blockStarts.get(ind+1)-blockEnds.get(ind);
    			}
    		}
    }
    System.out.println("Here");
    out.print(greatestDifference+" "+greatestBetween+"\n");
    out.close();                                  // close the output file
  }
  
  /**
   * Sorts the elements by symbol (alphabetically) using merge / recursive sorting.
   * @param elements   the ArrayList with the IndexEntry's
   * @param from       the index the user wants recursiveSort to start "from"
   * @param to         the index the user wants recursiveSort to start "to"
   * */
  public static void recursiveSort(ArrayList<Integer> elements, int from, int to){
      if( to-from < 2){
          if( to > from && elements.get(to) < elements.get(from)){
        	  Integer arrTemp = elements.get(to);
              elements.set(to,elements.get(from));
              elements.set(from, arrTemp);
          }
      }else{
          int middle = (from + to)/2;
          recursiveSort(elements, from, middle);
          recursiveSort(elements, middle+1, to);
          merge(elements, from, middle, to);
      }
  }
  public static ArrayList<Integer> tempForMerge = new ArrayList<Integer>();
  /**
   * Merges the array components back together for merge sorting; combines separated
   * elements back together
   * */
  public static void merge(ArrayList<Integer> elements, int from, int middle, int to){
      Integer i = new Integer(from);
      Integer j = new Integer(middle+1);
      Integer k = new Integer(from);
      
      while(i<= middle && j <= to){
          if(elements.get(i) < elements.get(j)){
              tempForMerge.add(k, elements.get(i));
              i++;
          }else{
              tempForMerge.add(k, elements.get(j));
              j++;
          }
          k++;
      }
      
      while(i <= middle){
          tempForMerge.add(k, elements.get(i));
          i++;
          k++;
      }
      
      while(j <= to){
          tempForMerge.add(k, elements.get(j));
          j++;
          k++;
      }
      
      for(k = from; k <= to; k++){
          elements.set(k, tempForMerge.get(k));
      }
  }
}