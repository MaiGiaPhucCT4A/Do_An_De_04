/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author LaptopDT
 */
public class Pair<SanPham, Integer> { // 1SP có integer chiếc

    private SanPham key;
    private Integer value;

    public Pair() {
    }

    public Pair(SanPham key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public SanPham getKey() {
        return key;
    }

    public void setKey(SanPham key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
