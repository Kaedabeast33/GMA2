package org.example.output.dorm.orders.raw_metronet;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.raw_metronet.columns.COL_db_id;
import org.example.output.dorm.orders.raw_metronet.columns.COL_agent;
import org.example.output.dorm.orders.raw_metronet.columns.COL_company;
import org.example.output.dorm.orders.raw_metronet.columns.COL_install_date_internet;
import org.example.output.dorm.orders.raw_metronet.columns.COL_internet_plan;
import org.example.output.dorm.orders.raw_metronet.columns.COL_lcp;
import org.example.output.dorm.orders.raw_metronet.columns.COL_order_date_internet;
import org.example.output.dorm.orders.raw_metronet.columns.COL_order_date_phone;
import org.example.output.dorm.orders.raw_metronet.columns.COL_report_month;
import org.example.output.dorm.orders.raw_metronet.columns.COL_state;
import org.example.output.dorm.orders.raw_metronet.columns.COL_sub_id;
import org.example.output.dorm.orders.raw_metronet.columns.COL_sub_name;
import org.example.output.dorm.orders.raw_metronet.columns.COL_date_db_changed;
import org.example.output.dorm.orders.raw_metronet.columns.COL_metronet_status;
import org.example.output.dorm.orders.raw_metronet.columns.COL_employee_id;
import org.example.output.dorm.orders.raw_metronet.columns.COL_manual_employee_id;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_raw_metronet extends TableTemplate {

    public TAB_raw_metronet() {
        super(
            "raw_metronet"
,
            "Table storing raw Metronet order records"
,
            new String[]{"orders", "metronet", "raw"},
            "tabd9b1fe11-671c-4b34-b83a-66e54e415aea"
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





    private final ColumnTemplate COL_agent = new COL_agent();

    public ColumnTemplate getCOL_agent() {
        return COL_agent;
    }





    private final ColumnTemplate COL_company = new COL_company();

    public ColumnTemplate getCOL_company() {
        return COL_company;
    }





    private final ColumnTemplate COL_install_date_internet = new COL_install_date_internet();

    public ColumnTemplate getCOL_install_date_internet() {
        return COL_install_date_internet;
    }





    private final ColumnTemplate COL_internet_plan = new COL_internet_plan();

    public ColumnTemplate getCOL_internet_plan() {
        return COL_internet_plan;
    }





    private final ColumnTemplate COL_lcp = new COL_lcp();

    public ColumnTemplate getCOL_lcp() {
        return COL_lcp;
    }





    private final ColumnTemplate COL_order_date_internet = new COL_order_date_internet();

    public ColumnTemplate getCOL_order_date_internet() {
        return COL_order_date_internet;
    }





    private final ColumnTemplate COL_order_date_phone = new COL_order_date_phone();

    public ColumnTemplate getCOL_order_date_phone() {
        return COL_order_date_phone;
    }





    private final ColumnTemplate COL_report_month = new COL_report_month();

    public ColumnTemplate getCOL_report_month() {
        return COL_report_month;
    }





    private final ColumnTemplate COL_state = new COL_state();

    public ColumnTemplate getCOL_state() {
        return COL_state;
    }





    private final ColumnTemplate COL_sub_id = new COL_sub_id();

    public ColumnTemplate getCOL_sub_id() {
        return COL_sub_id;
    }





    private final ColumnTemplate COL_sub_name = new COL_sub_name();

    public ColumnTemplate getCOL_sub_name() {
        return COL_sub_name;
    }





    private final ColumnTemplate COL_date_db_changed = new COL_date_db_changed();

    public ColumnTemplate getCOL_date_db_changed() {
        return COL_date_db_changed;
    }





    private final ColumnTemplate COL_metronet_status = new COL_metronet_status();

    public ColumnTemplate getCOL_metronet_status() {
        return COL_metronet_status;
    }





    private final ColumnTemplate COL_employee_id = new COL_employee_id();

    public ColumnTemplate getCOL_employee_id() {
        return COL_employee_id;
    }





    private final ColumnTemplate COL_manual_employee_id = new COL_manual_employee_id();

    public ColumnTemplate getCOL_manual_employee_id() {
        return COL_manual_employee_id;
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
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_agent().getEntityValue().getValue().toString(),
          getCOL_company().getEntityValue().getValue().toString(),
          getCOL_install_date_internet().getEntityValue().getValue().toString(),
          getCOL_internet_plan().getEntityValue().getValue().toString(),
          getCOL_lcp().getEntityValue().getValue().toString(),
          getCOL_order_date_internet().getEntityValue().getValue().toString(),
          getCOL_order_date_phone().getEntityValue().getValue().toString(),
          getCOL_report_month().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_sub_id().getEntityValue().getValue().toString(),
          getCOL_sub_name().getEntityValue().getValue().toString(),
          getCOL_date_db_changed().getEntityValue().getValue().toString(),
          getCOL_metronet_status().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_agent().getEntityValue().getValue().toString(),
          getCOL_company().getEntityValue().getValue().toString(),
          getCOL_install_date_internet().getEntityValue().getValue().toString(),
          getCOL_internet_plan().getEntityValue().getValue().toString(),
          getCOL_lcp().getEntityValue().getValue().toString(),
          getCOL_order_date_internet().getEntityValue().getValue().toString(),
          getCOL_order_date_phone().getEntityValue().getValue().toString(),
          getCOL_report_month().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_sub_id().getEntityValue().getValue().toString(),
          getCOL_sub_name().getEntityValue().getValue().toString(),
          getCOL_date_db_changed().getEntityValue().getValue().toString(),
          getCOL_metronet_status().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
                );
            }

}