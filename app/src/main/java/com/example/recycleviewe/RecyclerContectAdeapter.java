package com.example.recycleviewe;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerContectAdeapter extends RecyclerView.Adapter<RecyclerContectAdeapter.ViewHolder> {
    Context context;
    ArrayList<ContactModel> arrContect;

    RecyclerContectAdeapter(Context context, ArrayList<ContactModel> arrContect) {
        this.context = context;
        this.arrContect = arrContect;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contect_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(arrContect.get(position).img);
        holder.txtname.setText(arrContect.get(position).name);
        holder.txtNumber.setText(arrContect.get(position).number);
        setAnimation(holder.itemView,position);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_dialog);
                EditText edtname = dialog.findViewById(R.id.edt_name);
                EditText edtnumber = dialog.findViewById(R.id.edt_number);
                Button btnAction = dialog.findViewById(R.id.btnAction);
                TextView textview = dialog.findViewById(R.id.text_Title);
                textview.setText("Upadate Contect");
                btnAction.setText("Update");
                edtname.setText(arrContect.get(position).name);
                edtnumber.setText(arrContect.get(position).number);
                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", number = "";
                        name = edtname.getText().toString();
                        number = edtnumber.getText().toString();
                        arrContect.set(position, new ContactModel(R.drawable.img, name, number));
                        notifyItemChanged(position);
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder  bulider = new AlertDialog.Builder(context).setTitle("Delete Contact")
                        .setMessage("Are you Sure went to delete ")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrContect.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                bulider.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrContect.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txtNumber;
        ImageView imageView;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.text_name);
            txtNumber = itemView.findViewById(R.id.text_number);
            imageView = itemView.findViewById(R.id.img_con);
            linearLayout = itemView.findViewById(R.id.linear);
        }
    }
    private void setAnimation(View viewToAnimate,int position){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.a);
    }
}
