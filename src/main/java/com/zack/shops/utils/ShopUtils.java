package com.zack.shops.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ShopUtils {

	public static <Shop> List<List<Shop>> getPages(Collection<Shop> c, Integer pageSize) {
	    if (c == null)
	        return Collections.emptyList();
	    List<Shop> list = new ArrayList<>(c);
	    if (pageSize == null || pageSize <= 0 || pageSize > list.size())
	        pageSize = list.size();
	    int numPages = (int) Math.ceil((double)list.size() / (double)pageSize);
	    List<List<Shop>> pages = new ArrayList<List<Shop>>(numPages);
	    for (int pageNum = 0; pageNum < numPages;)
	        pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
	    return pages;
	}
}
