package exceptions;

public class EmptyListException extends Exception{
    
   // The code `public EmptyListException(String msg){ super(msg); }` is a constructor for the
   // `EmptyListException` class.
    public EmptyListException(String msg){
        super(msg);
    }
    // The code `public EmptyListException(){ }` is an empty constructor for the `EmptyListException`
    // class. This constructor does not take any parameters and does not contain any code within its
    // body. It is used to create an instance of the `EmptyListException` class without passing any
    // arguments.
    public EmptyListException(){
        
    }
}
