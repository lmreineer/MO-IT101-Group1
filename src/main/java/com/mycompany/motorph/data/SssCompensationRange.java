/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.data;

/**
 *
 * @author lance
 */
public class SssCompensationRange {

    private String range;
    private double contribution;

    public SssCompensationRange(String range, double contribution) {
        this.range = range;
        this.contribution = contribution;
    }

    public String getRange() {
        return range;
    }

    public double getContribution() {
        return contribution;
    }
}
