package ar.edu.itba.example.architecture;

import android.app.Application;

import androidx.room.Room;

import ar.edu.itba.example.architecture.data.AppExecutors;
import ar.edu.itba.example.architecture.data.RoomRepository;
import ar.edu.itba.example.architecture.data.local.MyDatabase;
import ar.edu.itba.example.architecture.data.remote.ApiClient;
import ar.edu.itba.example.architecture.data.remote.room.ApiRoomService;

public class MyApplication extends Application {

    public static String DATABASE_NAME = "my-db";

    AppExecutors appExecutors;
    RoomRepository roomRepository;

    public RoomRepository getRoomRepository() {
        return roomRepository;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appExecutors = new AppExecutors();

        ApiRoomService roomService = ApiClient.create(ApiRoomService.class);

        MyDatabase database = Room.databaseBuilder(this, MyDatabase.class, DATABASE_NAME).build();

        roomRepository = new RoomRepository(appExecutors, roomService, database);
    }
}
