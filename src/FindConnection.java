
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class FindConnection {
     Map cityMap;
    
    
    
    public FindConnection(String  fileName) throws InvalidMapException, IOException {
      this.cityMap=new Map(fileName);
    }
    public static void main(String [] args) throws InvalidMapException, FileNotFoundException,IOException, EmptyStackException{
      /* if(args.length < 1) {
		System.out.println("You must provide the name of the input file");
		System.exit(0);
	   }
	  String mapFileName = args[0];*/

          FindConnection connection= new FindConnection("map7.txt");
          ArrayStack<MapCell> stack=new ArrayStack<MapCell>();
          boolean destinationReached=false;
          MapCell companyLocation=connection.cityMap.getStart();
          stack.push(companyLocation);
          companyLocation.markInStack();
          while(!stack.isEmpty() && !destinationReached){
              MapCell currentCell=stack.peek();
              if(currentCell.isCustomer()){
                  System.out.println("The Path To The Destination Has Been Found As");
                  System.out.println(stack.toString());
                  break;
              }else{
                  MapCell next=connection.bestCell(currentCell);
                  if(next==null){
                      stack.pop();
                      currentCell.markOutStack();
                  }else{
                      stack.push(next);
                      next.markInStack();
                  }
              }
          }
          if(stack.isEmpty()){
              System.out.println("Could not find a path to the destination");
          }
        
    }
    private MapCell bestCell(MapCell cell){
        MapCell bestCell=null;
        for (int i=0;i<4;i++){
            if(cell.getNeighbour(i)!=null && !cell.getNeighbour(i).isMarked()){
            if(cell.getNeighbour(i).isCustomer()){
            bestCell=cell.getNeighbour(i);
            break;
           }     
            }
                      

        }
           if(bestCell==null ){
               for(int i=0;i<4;i++){
            if(cell.getNeighbour(i)!=null&& !cell.getNeighbour(i).isMarked()){
              if(cell.getNeighbour(i).isOmniSwitch()){
            bestCell= cell.getNeighbour(i);
            break;
           }    
            }

               }
           }
           
           
          if(bestCell==null){
           for(int i=0;i<4;i++){
            if(cell.getNeighbour(i)!=null&& !cell.getNeighbour(i).isMarked()){
           if(cell.getNeighbour(i).isVerticalSwitch() ||cell.getNeighbour(i).isHorizontalSwitch() ){
            bestCell= cell.getNeighbour(i);
            
            break;
           }   
            }

               }
           }
        
        
        return bestCell;
    }

}
