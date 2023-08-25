/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anderk222.jhotest.util;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author linuxlite
 * @param <T> the type of the list content
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pagination <T> {
    
    private int page;    
    private int limit;
    private List<T> data;
    private int totalPages;
    private long totalItems;
    
    public  Pagination (int page, int limit, int totalPages, long totalItems ){
        
        this.page = page;
        this.limit =limit;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }
    
        
    public  Pagination (int page, int limit ){
        
        this.page = page;
        this.limit =limit;
        
    }
    
    
}
