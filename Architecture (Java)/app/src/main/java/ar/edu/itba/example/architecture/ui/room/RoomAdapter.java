package ar.edu.itba.example.architecture.ui.room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ar.edu.itba.example.architecture.R;
import ar.edu.itba.example.architecture.model.Room;
import ar.edu.itba.example.architecture.ui.MainActivity;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private final List<Room> data;

    public RoomAdapter(List<Room> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RoomAdapter.RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.room_item, parent, false);
        return new RoomAdapter.RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.RoomViewHolder holder, int position) {
        Room room = data.get(position);
        holder.bindTo(room);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private String id;
        private final LinearLayout layout;
        private final TextView nameTextView;
        private final TextView sizeTextView;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = (LinearLayout) itemView;
            nameTextView = (TextView) itemView.findViewById(R.id.name);
            sizeTextView = (TextView) itemView.findViewById(R.id.size);
            itemView.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        public void bindTo(Room room) {
            id = room.getId();
            layout.setBackgroundColor(Color.parseColor(room.getColor()));
            nameTextView.setText(room.getName());
            sizeTextView.setText("[" + room.getSize() + "]");
        }

        @Override
        public void onClick(View v) {
            Context context = nameTextView.getContext();
            if (context instanceof MainActivity) {
                MainActivity activity = (MainActivity) context;
                activity.replaceFragment(RoomFragment.create(id), true);
            }
        }
    }
}
