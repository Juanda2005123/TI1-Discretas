package model;

public class Undo<T> {
    private T data;
    private DataType dataType;

    // The `public Undo(T data, DataType dataType)` is a constructor for the `Undo` class. It takes two
    // parameters: `data` of type `T` and `dataType` of type `DataType`.
    public Undo(T data, DataType dataType){

        this.data = data;
        this.dataType = dataType;

    }

    /**
     * The function returns the value of the data variable.
     * 
     * @return The method is returning the value of the variable "data".
     */
    public T getData() {
        return data;
    }

   /**
    * The function sets the value of the data variable.
    * 
    * @param data The parameter "data" is of type T, which means it can be any type. It is used to set
    * the value of the "data" variable in the class.
    */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * The function returns the data type.
     * 
     * @return The method is returning the value of the variable "dataType" of type DataType.
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     * The function sets the data type of an object.
     * 
     * @param dataType The dataType parameter is of type DataType.
     */
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
}
