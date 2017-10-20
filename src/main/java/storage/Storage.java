package storage;

import entity.Medicament;
import entity.MedicamentCategory;
import entity.Pharmacy;

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

    public List<T> getItemList() {
        return itemList;
    }
}
