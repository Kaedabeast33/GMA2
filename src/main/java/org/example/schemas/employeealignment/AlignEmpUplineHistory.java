package org.example.schemas.employeealignment;





import org.example.Annotations.*;
import org.example.CommonValues.TriggerType;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@KdbTable(name = "align_emp_upline_history", description = "tracks employee upline history from airtable", tags = {"airtable", "employee alignment", "upline history"}, type = "")
public class AlignEmpUplineHistory {

    @KdbPrimaryKey
    @KdbColumn(name="db_id")
    String db_id;

    @KdbIndex(indexGroups = {"idx_upline_team_dates"},order = {1})
    @KdbColumn(name = "team_id")
    String team_id;

    @KdbIndex(indexGroups = {"idx_upline_team_upline"},order = {2})
    @KdbColumn(name = "team_id_upline")
    String team_id_upline;

    @KdbIndex(indexGroups = {"idx_upline_team_upline"},order = {1})
    @KdbColumn(name = "team_id_owner")
    String team_id_owner;

    @KdbIndex(indexGroups = {"idx_upline_team_dates"},order = {2})
    @KdbColumn(name = "start_date")
    LocalDateTime start_date;

    @KdbIndex(indexGroups = {"idx_upline_team_dates"},order = {3})
    @KdbColumn(name="end_date")
    LocalDateTime end_date;

    @KdbColumn(name="is_active")
    Boolean is_active;

    @KdbTrigger(name = "prevent_overlap", description = "", triggerType = TriggerType.BEFORE_INSERT, triggerSet = {})
    public static String prevent_overlap(){
        return """
                BEGIN
                    DECLARE overlap_count INT DEFAULT 0;
                    Declare recursive_count int default 0;
                    DECLARE error_msg TEXT;
                   \s
                    SELECT count(*) into recursive_count
                    from align_emp_upline_history
                    where new.team_id = new.team_id_upline;
                
                    SELECT COUNT(*) INTO overlap_count
                    FROM align_emp_upline_history
                    WHERE team_id = NEW.team_id
                      AND NEW.start_date < end_date
                      AND NEW.end_date > start_date;
                     \s
                	if recursive_count > 0 then\s
                		set error_msg = concat('team_id upline aligning to itself count: ',recursive_count);
                		SIGNAL SQLSTATE '45000'
                        SET MESSAGE_TEXT = error_msg;
                    END IF;	
                
                    IF overlap_count > 0 THEN
                        SET error_msg = CONCAT('Overlapping records found: ', overlap_count);
                
                        SIGNAL SQLSTATE '45000'
                        SET MESSAGE_TEXT = error_msg;
                    END IF;
                END""";
    }






//    @Override
//    public String getValues(String arg) throws ParseException {
//        return "";
//    }
//
//    @Override
//    public String getValues() throws ParseException {
//        return String.format("('%s','%s','%s','%s','%s','%s','%s')\n,",
//                UUID.randomUUID(), getTeam_id(), getTeam_id_upline(), getTeam_id_owner(), getStart_date(), getEnd_date(), getIs_active()) ;
//
//
//    }
//
//
//    @Override
//    public List<String> getColumns() {
//        return List.of("db_id",
//                "team_id",
//                "team_id_upline",
//                "team_id_owner",
//                "start_date",
//                "end_date",
//                "is_active");
//    }
//
//
//    @Override
//    public String replaceCharacters(String value) {
//        return "";
//    }
//
//    @Override
//    public String getTableName() {
//        return "align_emp_upline_history";
//    }
//
//
//
//    @Override
//    public List<String> getUniqueValues() {
//        return List.of();
//    }
//
//    @Override
//    public List<String> getIndexes() {
//        return List.of();
//    }
//
//    @Override
//    public String getIdName() {
//        return "";
//    }


}
