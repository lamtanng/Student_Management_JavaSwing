/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ultils;

/**
 *
 * @author Trường Giang
 */
public final class Regex {
    public static final String PHONE = "^[0-9]\\d{9,10}$";
    public static final String EMAIL = "^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
    public static final String TEXT = "^[\\p{L}\\s]{2,50}$";
}
