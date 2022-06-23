package ar.edu.itba.example.architecture.ui.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.example.architecture.MyApplication;
import ar.edu.itba.example.architecture.data.RoomRepository;
import ar.edu.itba.example.architecture.databinding.FragmentListRoomBinding;
import ar.edu.itba.example.architecture.model.Room;
import ar.edu.itba.example.architecture.ui.MainActivity;
import ar.edu.itba.example.architecture.ui.RepositoryViewModelFactory;

public class RoomListFragment extends Fragment {

    FragmentListRoomBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MainActivity activity = (MainActivity)requireActivity();
        MyApplication application = (MyApplication)activity.getApplication();
        ViewModelProvider.Factory viewModelFactory = new RepositoryViewModelFactory<>(RoomRepository.class, application.getRoomRepository());
        RoomViewModel viewModel = new ViewModelProvider(this, viewModelFactory).get(RoomViewModel.class);

        binding = FragmentListRoomBinding.inflate(getLayoutInflater());

        List<Room> rooms = new ArrayList<>();
        RoomAdapter adapter = new RoomAdapter(rooms);
        viewModel.getRooms().observe(getViewLifecycleOwner(), resource -> {
            switch (resource.status) {
                case LOADING:
                    activity.showProgressBar();
                    break;
                case SUCCESS:
                    activity.hideProgressBar();
                    rooms.clear();
                    if (resource.data != null &&
                            resource.data.size() > 0) {
                        rooms.addAll(resource.data);
                        adapter.notifyDataSetChanged();
                        binding.list.setVisibility(View.VISIBLE);
                        binding.empty.setVisibility(View.GONE);
                    } else {
                        binding.list.setVisibility(View.GONE);
                        binding.empty.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        });

        binding.list.setHasFixedSize(true);
        binding.list.setLayoutManager(new LinearLayoutManager(activity));
        binding.list.setAdapter(adapter);

        return binding.getRoot();
    }
}
