package ar.edu.itba.example.architecture.data.local.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "Room", indices = {@Index("id")}, primaryKeys = {"id"})
public class LocalRoom {

    @NonNull
    @ColumnInfo(name = "id")
    public String id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "size")
    public String size;
    @ColumnInfo(name = "color")
    public String color;

    public LocalRoom(String id, String name, String size, String color) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.color = color;
    }
}