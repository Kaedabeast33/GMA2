package org.example.output.dorm.orders.raw_addon_vexus;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.raw_addon_vexus.columns.COL_db_id;
import org.example.output.dorm.orders.raw_addon_vexus.columns.COL_account_number;
import org.example.output.dorm.orders.raw_addon_vexus.columns.COL_addon;
import org.example.output.dorm.orders.raw_addon_vexus.columns.COL_boost;
import org.example.output.dorm.orders.raw_addon_vexus.columns.COL_date_db_changed;
import org.example.output.dorm.orders.raw_addon_vexus.columns.COL_mobile_number;
import org.example.output.dorm.orders.raw_addon_vexus.columns.COL_order_id;
import org.example.output.dorm.orders.raw_addon_vexus.columns.COL_order_number;
import org.example.output.dorm.orders.raw_addon_vexus.columns.COL_carrier_system;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_raw_addon_vexus extends TableTemplate {

    public TAB_raw_addon_vexus() {
        super(
            "raw_addon_vexus"
,
            "Table storing raw Vexus addon records"
,
            new String[]{"orders", "addon", "vexus"},
            "tab7bdabb91-4860-459c-a24b-3ec2dfff530e"
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





    private final ColumnTemplate COL_addon = new COL_addon();

    public ColumnTemplate getCOL_addon() {
        return COL_addon;
    }





    private final ColumnTemplate COL_boost = new COL_boost();

    public ColumnTemplate getCOL_boost() {
        return COL_boost;
    }





    private final ColumnTemplate COL_date_db_changed = new COL_date_db_changed();

    public ColumnTemplate getCOL_date_db_changed() {
        return COL_date_db_changed;
    }





    private final ColumnTemplate COL_mobile_number = new COL_mobile_number();

    public ColumnTemplate getCOL_mobile_number() {
        return COL_mobile_number;
    }





    private final ColumnTemplate COL_order_id = new COL_order_id();

    public ColumnTemplate getCOL_order_id() {
        return COL_order_id;
    }





    private final ColumnTemplate COL_order_number = new COL_order_number();

    public ColumnTemplate getCOL_order_number() {
        return COL_order_number;
    }





    private final ColumnTemplate COL_carrier_system = new COL_carrier_system();

    public ColumnTemplate getCOL_carrier_system() {
        return COL_carrier_system;
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
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_addon().getEntityValue().getValue().toString(),
          getCOL_boost().getEntityValue().getValue().toString(),
          getCOL_date_db_changed().getEntityValue().getValue().toString(),
          getCOL_mobile_number().getEntityValue().getValue().toString(),
          getCOL_order_id().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_carrier_system().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_addon().getEntityValue().getValue().toString(),
          getCOL_boost().getEntityValue().getValue().toString(),
          getCOL_date_db_changed().getEntityValue().getValue().toString(),
          getCOL_mobile_number().getEntityValue().getValue().toString(),
          getCOL_order_id().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_carrier_system().getEntityValue().getValue().toString()
                );
            }

}