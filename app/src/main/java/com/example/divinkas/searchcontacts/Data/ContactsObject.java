package com.example.divinkas.searchcontacts.Data;

import java.util.List;

public class ContactsObject {
    public Page page;
    public List<Item> items;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
