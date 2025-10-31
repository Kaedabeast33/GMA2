package org.example.output.dorm.orders.master_raw_addons;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.master_raw_addons.columns.COL_db_id;
import org.example.output.dorm.orders.master_raw_addons.columns.COL_account_number;
import org.example.output.dorm.orders.master_raw_addons.columns.COL_order_number;
import org.example.output.dorm.orders.master_raw_addons.columns.COL_mobile_number;
import org.example.output.dorm.orders.master_raw_addons.columns.COL_addon;
import org.example.output.dorm.orders.master_raw_addons.columns.COL_order_id;
import org.example.output.dorm.orders.master_raw_addons.columns.COL_carrier_system;
import org.example.output.dorm.orders.master_raw_addons.columns.COL_mo_reconciliation_ref_id;
import org.example.output.dorm.orders.master_raw_addons.columns.COL_mo_reconciliation_ref_id_2;
import org.example.output.dorm.orders.master_raw_addons.columns.COL_mo_reconciliation_ref_id_3;
import org.example.output.dorm.orders.master_raw_addons.columns.COL_mo_reconciliation_ref_id_4;
import org.example.output.dorm.orders.master_raw_addons.columns.COL_mo_general_ref_id;
import org.example.output.dorm.orders.master_raw_addons.columns.COL_date_db_changed;
import org.example.output.dorm.orders.master_raw_addons.columns.COL_order_method;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_master_raw_addons extends TableTemplate {

    public TAB_master_raw_addons() {
        super(
            "master_raw_addons"
,
            "Table storing master raw addons"
,
            new String[]{"addons", "master", "tracking"},
            "tabca2259d7-f780-45dc-b188-9c929adc0bfc"
,
            "dorm"
,
            "orders"

        );
    }




 KDBContext context = KDBContext.KDB_CONTEXT;
    private final ColumnTemplate COL_db_id = new COL_db_id();

    public ColumnTemplate getCOL_db_id() {
        return COL_db_id;
    }





    private final ColumnTemplate COL_account_number = new COL_account_number();

    public ColumnTemplate getCOL_account_number() {
        return COL_account_number;
    }





    private final ColumnTemplate COL_order_number = new COL_order_number();

    public ColumnTemplate getCOL_order_number() {
        return COL_order_number;
    }





    private final ColumnTemplate COL_mobile_number = new COL_mobile_number();

    public ColumnTemplate getCOL_mobile_number() {
        return COL_mobile_number;
    }





    private final ColumnTemplate COL_addon = new COL_addon();

    public ColumnTemplate getCOL_addon() {
        return COL_addon;
    }





    private final ColumnTemplate COL_order_id = new COL_order_id();

    public ColumnTemplate getCOL_order_id() {
        return COL_order_id;
    }





    private final ColumnTemplate COL_carrier_system = new COL_carrier_system();

    public ColumnTemplate getCOL_carrier_system() {
        return COL_carrier_system;
    }





    private final ColumnTemplate COL_mo_reconciliation_ref_id = new COL_mo_reconciliation_ref_id();

    public ColumnTemplate getCOL_mo_reconciliation_ref_id() {
        return COL_mo_reconciliation_ref_id;
    }





    private final ColumnTemplate COL_mo_reconciliation_ref_id_2 = new COL_mo_reconciliation_ref_id_2();

    public ColumnTemplate getCOL_mo_reconciliation_ref_id_2() {
        return COL_mo_reconciliation_ref_id_2;
    }





    private final ColumnTemplate COL_mo_reconciliation_ref_id_3 = new COL_mo_reconciliation_ref_id_3();

    public ColumnTemplate getCOL_mo_reconciliation_ref_id_3() {
        return COL_mo_reconciliation_ref_id_3;
    }





    private final ColumnTemplate COL_mo_reconciliation_ref_id_4 = new COL_mo_reconciliation_ref_id_4();

    public ColumnTemplate getCOL_mo_reconciliation_ref_id_4() {
        return COL_mo_reconciliation_ref_id_4;
    }





    private final ColumnTemplate COL_mo_general_ref_id = new COL_mo_general_ref_id();

    public ColumnTemplate getCOL_mo_general_ref_id() {
        return COL_mo_general_ref_id;
    }





    private final ColumnTemplate COL_date_db_changed = new COL_date_db_changed();

    public ColumnTemplate getCOL_date_db_changed() {
        return COL_date_db_changed;
    }





    private final ColumnTemplate COL_order_method = new COL_order_method();

    public ColumnTemplate getCOL_order_method() {
        return COL_order_method;
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
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_mobile_number().getEntityValue().getValue().toString(),
          getCOL_addon().getEntityValue().getValue().toString(),
          getCOL_order_id().getEntityValue().getValue().toString(),
          getCOL_carrier_system().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_2().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_3().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_4().getEntityValue().getValue().toString(),
          getCOL_mo_general_ref_id().getEntityValue().getValue().toString(),
          getCOL_date_db_changed().getEntityValue().getValue().toString(),
          getCOL_order_method().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_mobile_number().getEntityValue().getValue().toString(),
          getCOL_addon().getEntityValue().getValue().toString(),
          getCOL_order_id().getEntityValue().getValue().toString(),
          getCOL_carrier_system().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_2().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_3().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_4().getEntityValue().getValue().toString(),
          getCOL_mo_general_ref_id().getEntityValue().getValue().toString(),
          getCOL_date_db_changed().getEntityValue().getValue().toString(),
          getCOL_order_method().getEntityValue().getValue().toString()
                );
            }

}