//-----------------------------------------------------------------------------
// Simulation.java
// shjohn@unix.ucsc.edu
// Shridhik John
// 8 - 9 - 17
// A program that prints out a report and trace file that shows how much time a job requires
// to be processed by n number of processers
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Simulation{

//-----------------------------------------------------------------------------
//
// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
//
//-----------------------------------------------------------------------------

   public static Job getJob(Scanner in) { // sets up each job with starting time, duration
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }
   public static void printstat(PrintWriter TRC, int time, Queue [] s, int a){ //Prints Trace given the filename, time, and Queque, and number of jobs
   TRC.println("time="+time);
   for(int j=0;j<a+1;j++){
     TRC.println(j+": "+s[j]);
   }
   TRC.println();
  }

//-----------------------------------------------------------------------------
//
// The following stub for function main contains a possible algorithm for this
// project.  Follow it if you like.  Note that there are no instructions below
// which mention writing to either of the output files.  You must intersperse
// those commands as necessary.
//
//-----------------------------------------------------------------------------

   public static void main(String[] args) throws IOException{

      Scanner in = null; // reads input
      PrintWriter rpt = null; //prints out into .rpt
      PrintWriter trc = null; //prints out into .trc file
      Job J = null; // initialing a variable for job placeholder
      Job K = null; // ^
      Job L = null; // ^^
      Queue StorageQueue = new Queue(); // array of initial jobs
      int m = 0; // total number of jobs

//    1.  check command line arguments

      // check number of command line arguments is at least 3
         if(args.length != 1){
         System.out.println("Usage: Simulation <input file>");
         System.exit(1);
      }

//    2.  open files for reading and writing

      // open files
      in = new Scanner(new File(args[0]));
      rpt = new PrintWriter(new FileWriter(args[0]+".rpt")); //filewriter opens a new file for report with input file name.rpt //Prints formated obj to text output stream
      trc = new PrintWriter(new FileWriter(args[0]+".trc")); //filewriter opens a new file for report with input file name.rpt //Prints formated obj to text output stream

//    3.  read in m jobs from input file
      // read lines from in, extract and print tokens from each line
      m = Integer.parseInt(in.nextLine()); // reads first like for number of jobs
      
      while(in.hasNext()){                 // reads the rest of the lines and makes each pair of numbers a job through get job and inserts them into a Storage queue 
         StorageQueue.enqueue((Job)getJob(in));
      }

//    Prints the initial information of the trace file like filename/no of jobs/initial queue
      trc.println("Trace file: " + args[0] +".trc"); // first output onto trace file
      trc.println(m + " Jobs:"); // second output onto trace file
      trc.println(StorageQueue+"\n"); //prints a list of all jobs in Storage Queue in third line

//    Prints the initial report file information of the report file with filename/no of jobs/initial queue value
      rpt.println("Report file: "+ args[0] + ".rpt");
      rpt.println( m + " Jobs:");
      rpt.println(StorageQueue +"\n");
      rpt.println("***********************************************************");

// loop that prints out the amount of processors in the trace file
      for(int n=1; n<=m-1; n++){

      trc.println("*****************************");
           if (n==1) trc.println(n +" processor:"); //if there is only one processor
           else trc.println(n +" processors:");     //if there are multiple processsors 
      trc.println("*****************************");


      Queue[] ArrivalQueue = new Queue[n+1]; // declaring an array Queue called Arrival Queue
      for(int i=0; i<=n;i++){ // loop that initializes each index of the Arrival Queue with a new Queue
        ArrivalQueue[i]= new Queue();
      }    

        for(int i = 0; i<m; i++){
            J = (Job)StorageQueue.dequeue(); //remove the front job
            J.resetFinishTime(); //reset the finish time
            ArrivalQueue[0].enqueue(J);//the front job is added into the Arrival Queue
            StorageQueue.enqueue(J); // The front job is also added to Storage Queue
        }
      int time=0;
        // While the first Operator is empty or the job has not been finished or the the length of the Operator is not equal to the number of jobs
        while(ArrivalQueue[0].isEmpty() ||  ((Job)ArrivalQueue[0].peek()).getFinish() == -1  || ArrivalQueue[0].length()!=m){
          
        if(time == 0){ 
              printstat(trc, time, ArrivalQueue, n); //prints trace information to trc file
        }

		  int[] gettime= new int[ArrivalQueue.length]; //creating an array called getime that is the size of arrival queue length
		  if(time==0 && !ArrivalQueue[0].isEmpty()){ // if the time is 0, and the Arrival Queue's first index is not empty
			 time = ((Job)ArrivalQueue[0].peek()).getArrival(); // the time is equal to th first arrival time
		  }
		  else if(!ArrivalQueue[0].isEmpty()){ // if tbere is something in Arrival Queue, but not necessarily at time 0, 
			 J = ((Job)ArrivalQueue[0].peek()); // If the finish time is -1, then gettime[0] is equal to J's arrival time
			 if(J.getFinish()==-1){
				gettime[0] = J.getArrival();
			 }
		  }
		  for(int i = 1; i<ArrivalQueue.length; i++){ // if i is less than the arrival Queue'a length, i will be incremented
			 if(!ArrivalQueue[i].isEmpty()){ // as i is incremented, if the ArivalQueue is not empty at the given index
				Job P = ((Job)ArrivalQueue[i].peek()); // P is equal to the job's index
				gettime[i] = P.getFinish(); 
			 }
		  }
// Sorts Jobs with shortest time at the beginning and going in increasing chronological order
		   //sorts the time in order 
		   for(int numjob = gettime.length-1; numjob>0;numjob--){ //Sort loop based on length of the Queue
				 for(int job = 1; job<=numjob; job++){
					if(gettime[job]<gettime[job-1]){// Swaps the smallest time to the front
					   int temp = gettime[job]; //swaping gettime[i] with gettime[i-j]
					   gettime[job] = gettime[job-1];
					   gettime[job-1] = temp;
					}
				 }
			}
		   
		  //Stores gettime for each element if it is not 0 into variable time for printout in the trace 
		  for(int i = 0; i<gettime.length; i++){
			 if(gettime[i]==0) continue;
			 if(gettime[i]!=0){
				time = gettime[i];
				break;
			 }
		  }


        for(int j = 1; j<=n; j++){
            if(!ArrivalQueue[j].isEmpty() && ((Job)ArrivalQueue[j].peek()).getFinish()==time){ //if the arrival Queue of index j is not empty and the finishing time of the job is equal to time
                ArrivalQueue[0].enqueue(ArrivalQueue[j].dequeue());  // enqueue the dequeue of Arrival Queue at index j
                if(ArrivalQueue[j].length()>0){ //if arrival Queue at index j's length is greater than 0
                    if(((Job)ArrivalQueue[j].peek()).getFinish()==-1){ // and if the job at index j's finishing time is equal to -1
                        Job front = ((Job)ArrivalQueue[j].peek());  // the front job's is equal to the job of the given index
                            front.computeFinishTime(time); // used a given function to compute finish time of the front job
                    }
                }
            }
        }

	  if(ArrivalQueue.length-1==1){ // if arrival Queue's length minus 1 is equal to one 
         if(!ArrivalQueue[0].isEmpty()){ // and if the arrivalQueue is not empty
            J = ((Job)ArrivalQueue[0].peek()); // J is qual to the first job
            if(J.getArrival()==time){ // if the first job's arrival time is equal to the time
               ArrivalQueue[1].enqueue(ArrivalQueue[0].dequeue()); // we will dequeue the first job and enqueue it to the first index
            }
         }
      }
	  else{
         if(!ArrivalQueue[0].isEmpty()){ // if there are no jobs
            J = ((Job)ArrivalQueue[0].peek()); // the first job in Arrival Queue is equal to J
            if(J.getArrival()==time){ // if J's arrival time is equal to time
               int[] lengths = new int[ArrivalQueue.length-1]; // created a new array called lengths with the size of the Arrival Queue's array minus one
               for(int i = 0; i<lengths.length;i++){ // as long as i is less than lengths.length, increment i
                  lengths[i] = ArrivalQueue[i+1].length(); // lengths index i is equal to ArrivalQueue's length and index i + 1
               }
               int index = 0; 
               int min = lengths[0]; 
               for(int i = 1; i<lengths.length; i++){ 
                  if(lengths[i]<min){ // a loop that finds the minimum value
                     min = lengths[i]; // the min is equivalent to the length at index i 
                     index = i; 
                  } 
               } 
               ArrivalQueue[index+1].enqueue(ArrivalQueue[0].dequeue()); // the first job will be dequeued and then added to the next Queue
            }
         }
      }

           
        for(int j = 1; j<ArrivalQueue.length; j++){ // increment j as long as it is less than the length of the arrival Queue
               if(!ArrivalQueue[j].isEmpty()){ // if the arrival Queue at index j is not empty, the front job will be the one taken from Arrival Queue
                  Job front = ((Job)ArrivalQueue[j].peek());
                  if(front.getFinish()==-1) front.computeFinishTime(time); // in the case that finishing time of the front returns negative 1, calculate the finishing time 
               }
            }
            printstat(trc, time, ArrivalQueue, n);// prints the trace information to trace file
        }


         int[] gettime = new int[ArrivalQueue[0].length()]; // create int array gettime with the size of the length of the original amount of jobs  
         int totalWait = 0; 
         for(int j = 0; j<gettime.length; j++){ // calculating total wait time with a loop incrementing j as long as it is less than the length of the queue
            Job temp1 = ((Job)ArrivalQueue[0].peek()); 
            gettime[j] = temp1.getWaitTime(); 
            totalWait = totalWait + gettime[j]; // total wait is calculated
            ArrivalQueue[0].enqueue(ArrivalQueue[0].dequeue()); // Deques the first element in Arrival Queue and enqueus
         }
         
	// Sorts Jobs with shortest time at the beginning and going in increasing chronological order
		 for(int numjob = gettime.length-1; numjob>0;numjob--){	//Sort loop based on length of the Queue
				 for(int job = 1; job<=numjob; job++){           // Sorts using swap looping for each job
					if(gettime[job]<gettime[job-1]){  // Swaps the smallest time to the front
					   int temp = gettime[job];
					   gettime[job] = gettime[job-1];
					   gettime[job-1] = temp;
					}
				 }
		  
		 }
		 
         int maxWait= gettime[gettime.length-1]; // gets the maximum wait time from the last element of the sorted times above
         double averageWait= (double)totalWait/(double)gettime.length; // calculages the average wait time by dividing the total wait time by the no of elements
         
        String AW = String.format("%.2f", averageWait); // formatting averageWait to print 2 decimal places
	// prints the toatl wait time, max wait time, and average wait time in the report file as below
        if ( n ==1 )
      		rpt.println( n + " processor: totalWait="+ totalWait+ ", maxWait="+maxWait+", averageWait="+AW);
        else
      		rpt.println( n + " processors: totalWait="+ totalWait+ ", maxWait="+maxWait+", averageWait="+AW);
        } 
    trc.close();  // closes trace file 
    rpt.close();  // closes report file
    in.close();   // closes input file  
    
    }   
   
}

