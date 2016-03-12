package com.example.chenjutsu.calendar1;

/**
 * Created by chenjutsu on 16-3-11.
 */
import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;
public class ExampleDaoGenerator {
    public static void main(String[]args)throws Exception{
        Schema schema = new Schema(1,"com.example.chenjutsu.calendar1");
        createDB(schema);
        new DaoGenerator().generateAll(schema,"./app/src/main/java/");
    }

    public static void createDB(Schema schema){
        Entity event = schema.addEntity("EVENT");
        event.addIdProperty();
        event.addIntProperty("year");
        event.addIntProperty("month");
        event.addIntProperty("day");
        event.addStringProperty("event");
        event.addStringProperty("date");
        event.addBooleanProperty("display");


    }

}
