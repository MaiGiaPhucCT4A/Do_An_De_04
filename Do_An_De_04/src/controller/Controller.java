/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.SanPham;

/**
 *
 * @author LaptopDT
 */
public interface Controller {

    void WriteFileJson_SanPham(List<SanPham> list, String filename);

    List<SanPham> ReadFileJson_SanPham(String filename);
    
    void WriteFileCSV_SanPham(List<SanPham> list, String filename);

    List<SanPham> ReadFileCSV_SanPham(String filename);

    <T> List<T> sortByName(List<T> list);

    <T> List<T> sortByQuantity(List<T> list);// theo số lượng

    <T> List<T> searchByName(List<T> list, String key);
    // String key: nếu ta truyền cái tên thì nó sẽ trả giá trị key
}
