package by.samsolution.pharmacy.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class Storage<T>{
    private List<T> itemList;

    public Storage(){
        itemList = new ArrayList<>();
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }

    public List<T> getItemList() {
        return itemList;
    }
}
