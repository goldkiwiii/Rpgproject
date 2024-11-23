import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private List<Item> list;
    public ItemList(){
        this.list = new ArrayList<Item>();
    }


    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }
}
