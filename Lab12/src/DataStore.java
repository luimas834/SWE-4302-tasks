import java.util.*;

public class DataStore <T>{
    public List<T> items;

    public DataStore(){
        this.items=new ArrayList<>();
    }
    
    public void addItem(T item){
        items.add(item);
    }

    public List<T> getItem(){
        return items;
    }
    
}
