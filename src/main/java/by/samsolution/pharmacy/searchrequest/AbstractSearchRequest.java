package by.samsolution.pharmacy.searchrequest;

public class AbstractSearchRequest<T extends ISearchFieldEnum> implements SearchRequest<T> {
    private Integer from;
    private Integer size;
    private T sortField;
    private Boolean direction;

    public void setFrom(Integer from) {
        this.from = from;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getSize() {
        return size;
    }

    @Override
    public void setSortField(T sortField) {
        this.sortField = sortField;
    }

    @Override
    public void setDirection(Boolean direction) {
        this.direction = direction;
    }

    @Override
    public T getSortField() {
        return sortField;
    }

    @Override
    public Boolean getDirection() {
        return direction;
    }
}
