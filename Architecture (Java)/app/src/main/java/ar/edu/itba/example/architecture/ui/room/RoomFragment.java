package ar.edu.itba.example.architecture.ui.room;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import ar.edu.itba.example.architecture.MyApplication;
import ar.edu.itba.example.architecture.R;
import ar.edu.itba.example.architecture.data.Resource;
import ar.edu.itba.example.architecture.data.RoomRepository;
import ar.edu.itba.example.architecture.databinding.FragmentEditRoomBinding;
import ar.edu.itba.example.architecture.model.Room;
import ar.edu.itba.example.architecture.ui.MainActivity;
import ar.edu.itba.example.architecture.ui.RepositoryViewModelFactory;

public class RoomFragment extends Fragment {

    private static final String BUNDLE_ROOM_ID = "ar.edu.itba.example.architecture.ROOM_ID";

    private MainActivity activity;
    private FragmentEditRoomBinding binding;
    private RoomViewModel viewModel;
    private MenuItem editMenuItem, deleteMenuItem;

    public static RoomFragment create(String roomId) {
        RoomFragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putString(BUNDLE_ROOM_ID, roomId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        binding = FragmentEditRoomBinding.inflate(getLayoutInflater());

        MyApplication application = (MyApplication) getActivity().getApplication();
        activity = (MainActivity)getActivity();

        RepositoryViewModelFactory viewModelFactory = new RepositoryViewModelFactory(RoomRepository.class, application.getRoomRepository());
        viewModel = new ViewModelProvider(this, viewModelFactory).get(RoomViewModel.class);

        if (getArguments() != null) {
            toggleEditMode(false);
            binding.confirm.setText(R.string.confirm);

            viewModel.setRoomId(getArguments().getString(BUNDLE_ROOM_ID));
            viewModel.getRoom().observe(getViewLifecycleOwner(), resource -> {
                switch (resource.status) {
                    case LOADING:
                        activity.showProgressBar();
                        binding.confirm.setEnabled(false);
                        break;
                    case SUCCESS:
                        activity.hideProgressBar();
                        binding.name.setText(resource.data.getName());
                        binding.size.setText(resource.data.getSize());
                        binding.confirm.setEnabled(true);
                        break;
                    case ERROR:
                        activity.hideProgressBar();
                        binding.confirm.setEnabled(true);
                        Toast.makeText(activity, resource.error.getDescription(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            });
        }

        binding.confirm.setOnClickListener(v -> {
            if (getArguments() == null) {
                Room room = new Room(binding.name.getText().toString(), binding.size.getText().toString(), getRandomColor());
                viewModel.addRoom(room).observe(getViewLifecycleOwner(), this::handleEditResponse);
            }
            else {
                Room room = viewModel.getRoom().getValue().data;
                room.setName(binding.name.getText().toString());
                room.setSize(binding.size.getText().toString());
                viewModel.modifyRoom(room).observe(getViewLifecycleOwner(), this::handleEditResponse);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.view_room_menu, menu);
        editMenuItem = menu.findItem(R.id.menu_edit);
        deleteMenuItem = menu.findItem(R.id.menu_delete);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        boolean visible = (getArguments() != null) && !binding.name.isEnabled();
        menu.findItem(R.id.menu_edit).setVisible(visible);
        menu.findItem(R.id.menu_delete).setVisible(visible);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                editMenuItem.setEnabled(false);
                toggleEditMode(true);
                return true;
            case R.id.menu_delete:
                deleteMenuItem.setEnabled(false);
                deleteRoom();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void handleEditResponse(Resource<Room> resource) {

        switch (resource.status) {
            case LOADING:
                activity.showProgressBar();
                binding.confirm.setEnabled(false);
                break;
            case SUCCESS:
                activity.hideProgressBar();
                if (getArguments() != null) {
                    editMenuItem.setEnabled(true);
                    toggleEditMode(false);
                } else {
                    binding.name.getText().clear();
                    binding.size.getText().clear();
                    binding.confirm.setEnabled(true);
                }
                activity.popBackStack();
                Toast.makeText(activity, R.string.operation_success, Toast.LENGTH_SHORT).show();
                break;
            case ERROR:
                activity.hideProgressBar();
                binding.confirm.setEnabled(true);
                Toast.makeText(activity, resource.error.getDescription(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void toggleEditMode(boolean mode) {
        binding.name.setEnabled(mode);
        binding.size.setEnabled(mode);
        binding.confirm.setVisibility(mode ? View.VISIBLE : View.GONE);
    }

    private void deleteRoom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.app_name);
        String message = getString(R.string.confirm_delete, binding.name.getText());
        builder.setMessage(message);
        builder.setPositiveButton(R.string.yes, (dialog, id) -> {
            Room room = viewModel.getRoom().getValue().data;
            // Removed getRoom() observer to avoid null value update notification after delete.
            viewModel.getRoom().removeObservers(getViewLifecycleOwner());
            viewModel.deleteRoom(room).observe(getViewLifecycleOwner(), resource -> {
                switch (resource.status) {
                    case LOADING:
                        activity.showProgressBar();
                        break;
                    case SUCCESS:
                        activity.hideProgressBar();
                        activity.popBackStack();
                        Toast.makeText(activity, R.string.operation_success, Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR:
                        activity.hideProgressBar();
                        Toast.makeText(activity, resource.error.getDescription(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            });
            dialog.dismiss();
        });
        builder.setNegativeButton(R.string.no, (dialog, id) -> dialog.dismiss());
        AlertDialog alert = builder.create();
        alert.show();
    }

    private String getRandomColor() {
        String color = Integer.toHexString((int)(Math.random() * 0x16777215)).substring(2);
        return '#' + String.format("%1$6s", color).replace(' ', '0').toUpperCase();
    }
}