/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemaponto.controller;

import br.com.sistemaponto.view.ViewMenu;

/**
 *
 * @author Lenovo
 */
public class ControllerMenu {
    private ViewMenu viewMenu;
    
    public ControllerMenu(ViewMenu viewMenu){
        this.viewMenu = viewMenu;
        viewMenu.apresentarTela();
    }
}
