package org.example.ClassOutputCreator.templates;

import java.util.ArrayList;
import java.util.List;

public class UpsertTemplate {
    String update;
    String insert;
    String delete;

    public UpsertTemplate(String update, String insert, String delete) {
        this.update = update;
        this.insert = insert;
        this.delete = delete;
    }

    public UpsertTemplate() {
    }

    public List<String> getUpsert(){
        List<String> list = new ArrayList<>();
        if(update!=null){
            list.add(update);
        }
        if(insert!=null){
            list.add(insert);
        }
        if(delete!=null){
            list.add(delete);
        }
        return list;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getInsert() {
        return insert;
    }

    public void setInsert(String insert) {
        this.insert = insert;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }


}
