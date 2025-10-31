package org.example.schemas.commissions;

import com.google.gson.Gson;
import org.example.Annotations.*;
import org.example.CommonValues.TriggerType;
import org.example.CommonValues.ValueTypes;
import org.springframework.stereotype.Component;

@KdbTable(
        description = "tracks overwritten values for accounts, orders, and mobile numbers across systems",
        name = "overwrite_values",
        tags = {"overwrite", "account", "order", "mobile"},
        type = ""
)
@Component
public class OverwriteValues {

    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "mobile_number")
    private String mobileNumber;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "mo_db_id",unique = true)
    private String moDbId;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "mp_db_id",unique = true)
    private String mpDbId;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "mc_db_id",unique = true)
    private String mcDbId;

    @KdbColumn(name = "carrier_system")
    private String carrierSystem;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbKey(groupName = {"key_1"})
    @KdbColumn(name = "ma_db_id",unique = true)
    private String maDbId;

    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbTrigger(name = "update_reconciliation_ids_raw_orders_after_insert", description = "", triggerType = TriggerType.AFTER_INSERT, triggerSet = {})
    public static String update_reconciliation_ids_raw_orders_after_insert() {
        return """
                BEGIN
                    -- Update master_raw_orders if mo_db_id is provided
                    IF NEW.mo_db_id IS NOT NULL THEN
                        UPDATE orders.master_raw_orders r
                        JOIN commissions.overwrite_values ov ON r.db_id = ov.mo_db_id
                		LEFT JOIN employee_alignment.align_emp_logins l
                			on l.username = r.ref_login_username_id and\s
                			l.system_column = r.carrier_system
                
                		LEFT JOIN employee_alignment.align_emp_seller_history sh
                			on sh.employee_id = coalesce(l.employee_id,r.employee_id)
                			and r.order_date between sh.start_date and sh.end_date
                		LEFT JOIN employee_alignment.align_emp_team t
                			on sh.team_id = t.team_id
                		LEFT JOIN airtable.align_product_classification_current pc
                			ON case when t.business_unit = 'd2d' then 1 else 2 end = pc.program_id
                			AND r.package_name = pc.package_name
                			AND case when r.carrier_system in ('dtv','dsi') then 'saraplus' when r.carrier_system like 'lumos' then 'POPS' else r.carrier_system end = pc.system_type
                			AND pc.purchase_type = CASE
                				WHEN r.purchase_type IS NULL OR r.purchase_type  = '' THEN 'NEW'
                				ELSE UPPER(r.purchase_type)\s
                			end
                        SET
                            r.mo_reconciliation_ref_id = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                    COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                    COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, '')),
                                    NULLIF(pc.product_type, '')
                                )
                                WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN CONCAT(
                                    COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                    NULLIF(pc.product_type, '')
                                )
                                ELSE CONCAT(
                                    COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, '')),
                                    NULLIF(pc.product_type, '')
                                )
                            END,
                            r.mo_reconciliation_ref_id_2 = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                    COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                    COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, ''))
                                )
                                WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                            END,
                            r.mo_reconciliation_ref_id_3 = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                                ELSE COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                            END,
                            r.mo_reconciliation_ref_id_4 = NULLIF(r.customer_name, ''),
                            r.mo_general_ref_id = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                            END
                        WHERE r.db_id = NEW.mo_db_id;
                    END IF;
                   \s
                    IF NEW.ma_db_id IS NOT NULL THEN
                        UPDATE orders.master_raw_addons r
                        JOIN commissions.overwrite_values ov ON r.db_id = ov.ma_db_id
                       \s
                        SET
                            r.mo_reconciliation_ref_id = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                    COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                    COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, '')),
                                    'addon'
                                )
                                WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN CONCAT(
                                    COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                    'addon'
                                )
                                ELSE CONCAT(
                                    COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, '')),
                                    'addon'
                                )
                            END,
                            r.mo_reconciliation_ref_id_2 = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                    COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                    COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, ''))
                                )
                                WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                            END,
                            r.mo_reconciliation_ref_id_3 = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                                ELSE COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                            END,
                            r.mo_reconciliation_ref_id_4 = NULLIF(r.customer_name, ''),
                            r.mo_general_ref_id = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                            END
                        WHERE r.db_id = NEW.mo_db_id;
                    END IF;
                
                    IF NEW.mp_db_id IS NOT NULL THEN
                        UPDATE commissions.raw_master_pay_file r
                        JOIN commissions.overwrite_values ov ON r.db_id = ov.mp_db_id
                        SET
                            r.mo_reconciliation_ref_id = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                    COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                    COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, '')),
                                    NULLIF(r.product_type, '')
                                )
                                WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN CONCAT(
                                    COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                    NULLIF(r.product_type, '')
                                )
                                ELSE CONCAT(
                                    COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, '')),
                                    NULLIF(r.product_type, '')
                                )
                            END,
                            r.mo_reconciliation_ref_id_2 = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                    COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                    COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, ''))
                                )
                                WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                            END,
                            r.mo_reconciliation_ref_id_3 = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                                ELSE COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                            END,
                            r.mo_reconciliation_ref_id_4 = NULLIF(r.customer_name, ''),
                            r.mo_general_ref_id = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                            END
                        WHERE r.db_id = NEW.mp_db_id;
                    END IF;
                
                    IF NEW.mc_db_id IS NOT NULL THEN
                        UPDATE commissions.payroll r
                        JOIN commissions.overwrite_values ov ON r.db_id = ov.mc_db_id
                        SET
                            r.mo_reconciliation_ref_id = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                    COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                    COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, '')),
                                    NULLIF(r.product_type, '')
                                )
                                WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN CONCAT(
                                    COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                    NULLIF(r.product_type, '')
                                )
                                ELSE CONCAT(
                                    COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, '')),
                                    NULLIF(r.product_type, '')
                                )
                            END,
                            r.mo_reconciliation_ref_id_2 = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                    COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                    COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, ''))
                                )
                                WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                            END,
                            r.mo_reconciliation_ref_id_3 = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                                ELSE COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                            END,
                            r.mo_reconciliation_ref_id_4 = NULLIF(r.customer_name, ''),
                            r.mo_general_ref_id = CASE
                                WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                            END
                        WHERE r.db_id = NEW.mc_db_id;
                    END IF;
                END""";
    }

        @KdbTrigger(name = "update_reconciliation_ids_raw_orders_after_update", description = "", triggerType = TriggerType.AFTER_UPDATE, triggerSet = {})
    public static String update_reconciliation_ids_raw_orders_after_update(){
        return """
                BEGIN
                       -- Nullify reconciliation fields if DB IDs changed
                       IF OLD.mo_db_id != NEW.mo_db_id THEN
                           UPDATE orders.master_raw_orders r
                           SET\s
                               r.mo_reconciliation_ref_id = NULL,
                               r.mo_reconciliation_ref_id_2 = NULL,
                               r.mo_reconciliation_ref_id_3 = NULL,
                               r.mo_reconciliation_ref_id_4 = NULL
                           WHERE r.db_id = OLD.mo_db_id;
                       END IF;
                
                       IF OLD.mp_db_id != NEW.mp_db_id THEN
                           UPDATE commissions.raw_master_pay_file r
                           SET\s
                               r.mo_reconciliation_ref_id = NULL,
                               r.mo_reconciliation_ref_id_2 = NULL,
                               r.mo_reconciliation_ref_id_3 = NULL,
                               r.mo_reconciliation_ref_id_4 = NULL
                           WHERE r.db_id = OLD.mp_db_id;
                       END IF;
                
                       IF OLD.mc_db_id != NEW.mc_db_id THEN
                           UPDATE commissions.payroll r
                           SET\s
                               r.mo_reconciliation_ref_id = NULL,
                               r.mo_reconciliation_ref_id_2 = NULL,
                               r.mo_reconciliation_ref_id_3 = NULL,
                               r.mo_reconciliation_ref_id_4 = NULL
                           WHERE r.db_id = OLD.mc_db_id;
                       END IF;
                      \s
                       IF OLD.ma_db_id != NEW.ma_db_id THEN
                           UPDATE orders.master_raw_addons r
                           SET\s
                               r.mo_reconciliation_ref_id = NULL,
                               r.mo_reconciliation_ref_id_2 = NULL,
                               r.mo_reconciliation_ref_id_3 = NULL,
                               r.mo_reconciliation_ref_id_4 = NULL
                           WHERE r.db_id = OLD.ma_db_id;
                       END IF;
                
                       -- Rebuild reconciliation references
                       IF NEW.mo_db_id IS NOT NULL THEN
                           UPDATE orders.master_raw_orders r
                           JOIN commissions.overwrite_values ov ON r.db_id = ov.mo_db_id
                   		LEFT JOIN employee_alignment.align_emp_logins l
                   			on l.username = r.ref_login_username_id and\s
                   			l.system_column = r.carrier_system
                
                   		LEFT JOIN employee_alignment.align_emp_seller_history sh
                   			on sh.employee_id = coalesce(l.employee_id,r.employee_id)
                   			and r.order_date between sh.start_date and sh.end_date
                   		LEFT JOIN employee_alignment.align_emp_team t
                   			on sh.team_id = t.team_id
                   		LEFT JOIN airtable.align_product_classification_current pc
                   			ON case when t.business_unit = 'd2d' then 1 else 2 end = pc.program_id
                   			AND r.package_name = pc.package_name
                   			AND case when r.carrier_system in ('dtv','dsi') then 'saraplus' when r.carrier_system like 'lumos' then 'POPS' else r.carrier_system end = pc.system_type
                   			AND pc.purchase_type = CASE
                   				WHEN r.purchase_type IS NULL OR r.purchase_type  = '' THEN 'NEW'
                   				ELSE UPPER(r.purchase_type)\s
                   			end
                           SET
                               r.mo_reconciliation_ref_id = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                       COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                       COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, '')),
                                       NULLIF(pc.product_type, '')
                                   )
                                   WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN CONCAT(
                                       COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                       NULLIF(pc.product_type, '')
                                   )
                                   ELSE CONCAT(
                                       COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, '')),
                                       NULLIF(pc.product_type, '')
                                   )
                               END,
                               r.mo_reconciliation_ref_id_2 = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                       COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                       COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, ''))
                                   )
                                   WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                   ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                               END,
                               r.mo_reconciliation_ref_id_3 = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                                   ELSE COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                               END,
                               r.mo_reconciliation_ref_id_4 = NULLIF(r.customer_name, ''),
                               r.mo_general_ref_id = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                   ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                               END
                           WHERE r.db_id = NEW.mo_db_id;
                       END IF;
                      \s
                       IF NEW.ma_db_id IS NOT NULL THEN
                           UPDATE orders.master_raw_addons r
                           JOIN commissions.overwrite_values ov ON r.db_id = ov.ma_db_id
                          \s
                           SET
                               r.mo_reconciliation_ref_id = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                       COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                       COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, '')),
                                       'addon'
                                   )
                                   WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN CONCAT(
                                       COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                       'addon'
                                   )
                                   ELSE CONCAT(
                                       COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, '')),
                                       'addon'
                                   )
                               END,
                               r.mo_reconciliation_ref_id_2 = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                       COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                       COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, ''))
                                   )
                                   WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                   ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                               END,
                               r.mo_reconciliation_ref_id_3 = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                                   ELSE COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                               END,
                               r.mo_reconciliation_ref_id_4 = NULLIF(r.customer_name, ''),
                               r.mo_general_ref_id = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                   ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                               END
                           WHERE r.db_id = NEW.mo_db_id;
                       END IF;
                
                       IF NEW.mp_db_id IS NOT NULL THEN
                           UPDATE commissions.raw_master_pay_file r
                           JOIN commissions.overwrite_values ov ON r.db_id = ov.mp_db_id
                           SET
                               r.mo_reconciliation_ref_id = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                       COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                       COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, '')),
                                       NULLIF(r.product_type, '')
                                   )
                                   WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN CONCAT(
                                       COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                       NULLIF(r.product_type, '')
                                   )
                                   ELSE CONCAT(
                                       COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, '')),
                                       NULLIF(r.product_type, '')
                                   )
                               END,
                               r.mo_reconciliation_ref_id_2 = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                       COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                       COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, ''))
                                   )
                                   WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                   ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                               END,
                               r.mo_reconciliation_ref_id_3 = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                                   ELSE COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                               END,
                               r.mo_reconciliation_ref_id_4 = NULLIF(r.customer_name, ''),
                               r.mo_general_ref_id = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                   ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                               END
                           WHERE r.db_id = NEW.mp_db_id;
                       END IF;
                
                       IF NEW.mc_db_id IS NOT NULL THEN
                           UPDATE commissions.payroll r
                           JOIN commissions.overwrite_values ov ON r.db_id = ov.mc_db_id
                           SET
                               r.mo_reconciliation_ref_id = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                       COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                       COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, '')),
                                       NULLIF(r.product_type, '')
                                   )
                                   WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN CONCAT(
                                       COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                       NULLIF(r.product_type, '')
                                   )
                                   ELSE CONCAT(
                                       COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, '')),
                                       NULLIF(r.product_type, '')
                                   )
                               END,
                               r.mo_reconciliation_ref_id_2 = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(
                                       COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, '')),
                                       COALESCE(NULLIF(ov.mobile_number, ''), NULLIF(r.mobile_number, ''))
                                   )
                                   WHEN r.carrier_system IN ('dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                   ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                               END,
                               r.mo_reconciliation_ref_id_3 = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                                   ELSE COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                               END,
                               r.mo_reconciliation_ref_id_4 = NULLIF(r.customer_name, ''),
                               r.mo_general_ref_id = CASE
                                   WHEN r.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp video', 'vexus') THEN COALESCE(NULLIF(ov.account_number, ''), NULLIF(r.account_number, ''))
                                   ELSE COALESCE(NULLIF(ov.order_number, ''), NULLIF(r.order_number, ''))
                               END
                           WHERE r.db_id = NEW.mc_db_id;
                       END IF;
                   END""";
    }
    @KdbTrigger(name = "update_reconciliation_ids_raw_orders_after_delete", description = "", triggerType = TriggerType.AFTER_DELETE, triggerSet = {})
    public static String update_reconciliation_ids_raw_orders_after_delete(){
        return """
                BEGIN
                          -- Nullify reconciliation fields if DB IDs changed
                
                          UPDATE orders.master_raw_orders r
                          SET\s
                              r.mo_reconciliation_ref_id = NULL,
                              r.mo_reconciliation_ref_id_2 = NULL,
                              r.mo_reconciliation_ref_id_3 = NULL,
                              r.mo_reconciliation_ref_id_4 = NULL,
                              r.mo_general_ref_id = NULL
                          WHERE r.db_id = OLD.mo_db_id;
                         \s
                          UPDATE orders.master_raw_addons r
                          SET\s
                              r.mo_reconciliation_ref_id = NULL,
                              r.mo_reconciliation_ref_id_2 = NULL,
                              r.mo_reconciliation_ref_id_3 = NULL,
                              r.mo_reconciliation_ref_id_4 = NULL,
                              r.mo_general_ref_id = NULL
                          WHERE r.db_id = OLD.mo_db_id;
                
                          UPDATE commissions.raw_master_pay_file r
                          SET\s
                              r.mo_reconciliation_ref_id = NULL,
                              r.mo_reconciliation_ref_id_2 = NULL,
                              r.mo_reconciliation_ref_id_3 = NULL,
                              r.mo_reconciliation_ref_id_4 = NULL,
                              r.mo_general_ref_id = NULL
                          WHERE r.db_id = OLD.mp_db_id;
                
                          UPDATE commissions.payroll r
                          SET\s
                              r.mo_reconciliation_ref_id = NULL,
                              r.mo_reconciliation_ref_id_2 = NULL,
                              r.mo_reconciliation_ref_id_3 = NULL,
                              r.mo_reconciliation_ref_id_4 = NULL,
                              r.mo_general_ref_id = NULL
                          WHERE r.db_id = OLD.mc_db_id;
                      END""";
    }
}
