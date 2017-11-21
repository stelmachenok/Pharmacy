package by.samsolution.pharmacy.searchrequest;

public class AbstractSearchRequest<T extends ISearchFieldEnum> implements SearchRequest<T> {
    private int from;
    private int size;
    private T sortField;
    private boolean direction;

    public void setFrom(int from) {
        this.from = from;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getFrom() {
        return from;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void setSortField(T sortField) {
        this.sortField = sortField;
    }

    @Override
    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    @Override
    public T getSortField() {
        return sortField;
    }

    @Override
    public boolean getDirection() {
        return direction;
    }
}
