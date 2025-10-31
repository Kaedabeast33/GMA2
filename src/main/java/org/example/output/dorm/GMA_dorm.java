package org.example.output.dorm;


import org.example.gma.templates.GMATemplate;
import org.example.gma.templates.MATemplate;

import org.example.output.dorm.orders.MA_orders;

import org.example.ContextInstance.KDBContext;

public class GMA_dorm extends GMATemplate {

    public GMA_dorm() {
        super(
        "dorm"
,
        "main data lake"
,
        new String[]{"", ""},
        "gmaf04a470e-b252-4473-bc28-8bc28ab1667a"
,
        "",
        ""
             );
    }


 KDBContext context = KDBContext.KDB_CONTEXT;

    private final MA_orders MA_orders = new MA_orders();
    public MA_orders getMA_orders() {
        return MA_orders;
    }


}