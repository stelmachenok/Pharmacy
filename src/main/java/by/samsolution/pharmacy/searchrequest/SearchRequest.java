package by.samsolution.pharmacy.searchrequest;

public interface SearchRequest<T extends ISearchFieldEnum> {
    void setFrom(int from);

    void setSize(int size);

    void setSortField(T sortField);

    void setDirection(boolean direction);

    int getFrom();

    int getSize();

    T getSortField();

    boolean getDirection();
}
