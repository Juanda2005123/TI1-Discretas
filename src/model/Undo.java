package model;

public class Undo<T> {
    private T data;
    private DataType dataType;

    public Undo(T data, DataType dataType){

        this.data = data;
        this.dataType = dataType;

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
}
