package com.digytal.control.infra.sql;

public class Page {
    int pageIndex;
    int pageSize;
    int totalPageItems;
    int totalItems;
    int[] pages;
    protected Page(){}

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getTotalPageItems() {
        return totalPageItems;
    }

    public int[] getPages() {
        return pages;
    }
}