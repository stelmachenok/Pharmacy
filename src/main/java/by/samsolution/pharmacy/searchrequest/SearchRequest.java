package by.samsolution.pharmacy.searchrequest;

public interface SearchRequest<T extends ISearchFieldEnum> {
    void setFrom(Integer from);

    void setSize(Integer size);

    void setSortField(T sortField);

    void setDirection(Boolean direction);

    Integer getFrom();

    Integer getSize();

    T getSortField();

    Boolean getDirection();
}
