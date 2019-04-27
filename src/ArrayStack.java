
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class ArrayStack<T> implements ArrayStackADT<T> {
    private T[] ArrayStack;
    private int top;
    private int initialSize;
    public ArrayStack(){
       this.top=-1;
       this.initialSize=20;
       this.ArrayStack=(T[]) new Object[this.initialSize];
    }
    public ArrayStack(int initialCapacity){
        this.top=-1;
        this.initialSize=initialCapacity;
    }
       @Override
    public void push(T dataItem){
        if(isFull()){
            if(this.initialSize<100){
           // Incerease size
                this.initialSize+=2;
            T[] tempArray=(T[])new Object[this.size()];
            //Transfer to temporays stack
            for(int i=0;i<this.size();i++){
                tempArray[i]=this.ArrayStack[i];
            }
            //Empty Stack
            this.ArrayStack=null;
            //Resize our Stack
            this.ArrayStack=(T[]) new Object[this.initialSize];
            //Now refill it
            for(int j=0;j<tempArray.length;j++){
                this.ArrayStack[j]=tempArray[j];
            }
            tempArray=null;
            }else{
                 this.initialSize+=50;
                            T[] tempArray=(T[])new Object[this.size()];
            //Transfer to temporays stack
            for(int i=0;i<this.size();i++){
                tempArray[i]=this.ArrayStack[i];
            }
            //Empty Stack
            this.ArrayStack=null;
            //Resize our Stack
            this.ArrayStack=(T[]) new Object[this.initialSize];
            //Now refill it
            for(int j=0;j<tempArray.length;j++){
                this.ArrayStack[j]=tempArray[j];
            }
            tempArray=null;
            }
           this.ArrayStack[++top] = dataItem;
           
        }
        
    }
    private boolean isFull() {
		if (this.size()==initialSize){
			return false;}
                else{return true;}
	}

    @Override
    public T pop() throws EmptyStackException {
        if(this.isEmpty()){
            //return null;
            throw new EmptyStackException("Stack Empty");
        }
        else{
            
            T popped=this.ArrayStack[this.top];
            this.ArrayStack[top]=null;
            this.top--;
           // System.out.println("Size :"+this.size()+" Max: "+this.initialSize);
            if(this.size()<=Math.floor(this.initialSize/3)){
              
                        int newMax=(int)Math.floor(this.initialSize/2);
                          this.initialSize=newMax;
               // System.err.println(this.initialSize);
                T[] tempArray=(T[]) new Object[this.initialSize];
                //Transfer ontent to temporary array
                for(int i=0;i<this.size();i++){
                    tempArray[i]=this.ArrayStack[i];
                }
                //Destroy the ArrayStack
                this.ArrayStack=null;
                //Create the arrayStack with new Maximum
                this.ArrayStack=(T[]) new Object[this.initialSize];
                //Move content back again
                for(int j=0;j<tempArray.length;j++){
                    this.ArrayStack[j]=tempArray[j];
                }
            }
            return popped;
        }
    }

    @Override
    public T peek() throws EmptyStackException {
        if(this.isEmpty())
            throw new EmptyStackException("stack empty");
        return this.ArrayStack[this.top];
      
        
    }

    @Override
    public boolean isEmpty() {
       if(this.top==-1){
           return true;
       }else{
           return false;
       }
    }

    @Override
    public int size() {
       return this.top+1;
    }
    @Override
    public String toString(){
     
           
		StringBuilder sb = new StringBuilder("Stack: ");
		for(int i=0; i<this.size(); i++){
			sb.append(this.ArrayStack[i] + ", ");
		}
 
		sb.setLength(sb.length()-2);
		return sb.toString();	
  
    }
    public int length(){
        return this.initialSize;
    }
}
