package ar.edu.itba.example.architecture.data.remote.room;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ar.edu.itba.example.architecture.data.remote.room.RemoteRoomMeta;

public class RemoteRoom {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("meta")
    @Expose
    private RemoteRoomMeta meta;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RemoteRoomMeta getMeta() {
        return meta;
    }

    public void setMeta(RemoteRoomMeta meta) {
        this.meta = meta;
    }
}