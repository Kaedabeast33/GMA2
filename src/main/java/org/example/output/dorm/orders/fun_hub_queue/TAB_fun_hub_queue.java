package org.example.output.dorm.orders.fun_hub_queue;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.fun_hub_queue.columns.COL_id;
import org.example.output.dorm.orders.fun_hub_queue.columns.COL_fun_id;
import org.example.output.dorm.orders.fun_hub_queue.columns.COL_product_type;
import org.example.output.dorm.orders.fun_hub_queue.columns.COL_mo_ref_id;
import org.example.output.dorm.orders.fun_hub_queue.columns.COL_carrier_system;
import org.example.output.dorm.orders.fun_hub_queue.columns.COL_employee_id;
import org.example.output.dorm.orders.fun_hub_queue.columns.COL_manual_order;
import org.example.output.dorm.orders.fun_hub_queue.columns.COL_sync_status;
import org.example.output.dorm.orders.fun_hub_queue.columns.COL_mobile_number;
import org.example.output.dorm.orders.fun_hub_queue.columns.COL_db_insert_date;
import org.example.output.dorm.orders.fun_hub_queue.columns.COL_db_update_date;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_fun_hub_queue extends TableTemplate {

    public TAB_fun_hub_queue() {
        super(
            "fun_hub_queue"
,
            "Table storing fun hub queue data"
,
            new String[]{"fun", "hub", "queue"},
            "tab206a69fb-9131-4697-9429-a91aacb9e2b1"
,
            "dorm"
,
            "orders"

        );
    }




 KDBContext context = KDBContext.KDB_CONTEXT;
    private final ColumnTemplate COL_id = new COL_id();

    public ColumnTemplate getCOL_id() {
        return COL_id;
    }





    private final ColumnTemplate COL_fun_id = new COL_fun_id();

    public ColumnTemplate getCOL_fun_id() {
        return COL_fun_id;
    }





    private final ColumnTemplate COL_product_type = new COL_product_type();

    public ColumnTemplate getCOL_product_type() {
        return COL_product_type;
    }





    private final ColumnTemplate COL_mo_ref_id = new COL_mo_ref_id();

    public ColumnTemplate getCOL_mo_ref_id() {
        return COL_mo_ref_id;
    }





    private final ColumnTemplate COL_carrier_system = new COL_carrier_system();

    public ColumnTemplate getCOL_carrier_system() {
        return COL_carrier_system;
    }





    private final ColumnTemplate COL_employee_id = new COL_employee_id();

    public ColumnTemplate getCOL_employee_id() {
        return COL_employee_id;
    }





    private final ColumnTemplate COL_manual_order = new COL_manual_order();

    public ColumnTemplate getCOL_manual_order() {
        return COL_manual_order;
    }





    private final ColumnTemplate COL_sync_status = new COL_sync_status();

    public ColumnTemplate getCOL_sync_status() {
        return COL_sync_status;
    }





    private final ColumnTemplate COL_mobile_number = new COL_mobile_number();

    public ColumnTemplate getCOL_mobile_number() {
        return COL_mobile_number;
    }





    private final ColumnTemplate COL_db_insert_date = new COL_db_insert_date();

    public ColumnTemplate getCOL_db_insert_date() {
        return COL_db_insert_date;
    }





    private final ColumnTemplate COL_db_update_date = new COL_db_update_date();

    public ColumnTemplate getCOL_db_update_date() {
        return COL_db_update_date;
    }





    @Override
    public List<String> getColumns(){
       List<String> list = context.getColumns(this.getGmaName(),this.getMaName(),this.getName());
       System.out.println(list);
       return list;
    }
     
    @Override
    public List<String> getColumnsByGroupName(String groupName){
        List<String> list = context.getColumnsByGroupName(this.getGmaName(),this.getMaName(),this.getName(),groupName);
        System.out.println(list);
        return list;
    }
     
    @Override
    public List<String> getUniqueIdentifierColumns(){
        List<String> list = context.getUniqueIdentifierColumns(this.getGmaName(),this.getMaName(),this.getName());
        System.out.println(list);
        return list;
    }
     
    @Override
    public List<String> getUniqueIdentifierColumnsByGroupName(String groupName){
        List<String> list = context.getUniqueIdentifierColumnsByGroupName(this.getGmaName(),this.getMaName(),this.getName(),groupName);
        System.out.println(list);
        return list;
        }
     
    @Override
    public String replaceCharacters(String value){
       if(value==null){;
           return null;
       }
         return value.replace("'","''");
    }
    @Override
    public String getTableName(){
        return this.getName();
}
@Override
public String getValues()  {
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_id().getEntityValue().getValue().toString(),
          getCOL_fun_id().getEntityValue().getValue().toString(),
          getCOL_product_type().getEntityValue().getValue().toString(),
          getCOL_mo_ref_id().getEntityValue().getValue().toString(),
          getCOL_carrier_system().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_order().getEntityValue().getValue().toString(),
          getCOL_sync_status().getEntityValue().getValue().toString(),
          getCOL_mobile_number().getEntityValue().getValue().toString(),
          getCOL_db_insert_date().getEntityValue().getValue().toString(),
          getCOL_db_update_date().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_id().getEntityValue().getValue().toString(),
          getCOL_fun_id().getEntityValue().getValue().toString(),
          getCOL_product_type().getEntityValue().getValue().toString(),
          getCOL_mo_ref_id().getEntityValue().getValue().toString(),
          getCOL_carrier_system().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_order().getEntityValue().getValue().toString(),
          getCOL_sync_status().getEntityValue().getValue().toString(),
          getCOL_mobile_number().getEntityValue().getValue().toString(),
          getCOL_db_insert_date().getEntityValue().getValue().toString(),
          getCOL_db_update_date().getEntityValue().getValue().toString()
                );
            }

}