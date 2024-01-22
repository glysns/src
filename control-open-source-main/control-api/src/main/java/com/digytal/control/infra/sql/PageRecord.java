package com.digytal.control.infra.sql;

import java.util.List;
import java.util.stream.IntStream;

public class PageRecord<E> extends Page {
    private List<E> records;
    private static PageRecord instance;
    private PageRecord(){}

    public static PageRecord of(List records,Integer pageIndex,Integer pageSize,Integer total){
        instance = new PageRecord();
        instance.totalItems = total;
        instance.pageIndex=pageIndex==null || pageIndex==0?1:pageIndex;
        pageSize = pageSize==null?StringSQL.DEFAULT_PAGE_SIZE:pageSize;
        pageSize = total<pageSize?total:pageSize;
        instance.pageSize=pageSize;
        instance.records = records;
        instance.totalPageItems = records==null?0:records.size();
        instance.pages = new int[Double.valueOf(Math.ceil((double) instance.totalItems/instance.pageSize)).intValue()];
        IntStream.range(0, instance.pages.length).forEach(index -> instance.pages[index]=index+1);
        return  instance;
    }
    public List getRecords() {
        return records;
    }
    public Page getPage(){
        Page page = new Page();
        page.pageIndex = instance.pageIndex;
        page.pageSize = instance.pageSize;
        page.totalItems = instance.totalItems;
        page.totalPageItems = instance.totalPageItems;
        page.pages = instance.pages;
        return page;
    }
}