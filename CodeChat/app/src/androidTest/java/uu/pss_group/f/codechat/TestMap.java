package uu.pss_group.f.codechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TestMap extends HashMap<String, Object> {
    private String id;
    private ArrayList<String> list;

    public TestMap(){}

    public TestMap(String id, ArrayList<String> list){
        this.id = id;
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.put("id",id);
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
        this.put("list", list);
    }

    static TestMap create(){
        TestMap map = new TestMap();

        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");


        String id = UUID.randomUUID().toString().substring(0, 7);
        map.put("id", id);
        map.put("list", list);

        return map;
    }
}