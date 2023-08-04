package com.example.challenges.adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.challenges.R;
import com.example.challenges.fragment.ThiLaiFragment;
import com.example.challenges.model.DangKyHocLai;

import java.util.List;

public class DangKyHocLaiAdapter extends RecyclerView.Adapter<DangKyHocLaiAdapter.DangKyHocLaiViewHolder>{

    private List<DangKyHocLai> dangKyHocLais;
    private Context context;

    public DangKyHocLaiAdapter(Context context, List<DangKyHocLai> dangKyHocLais) {
        this.context = context;
        this.dangKyHocLais = dangKyHocLais;
    }

    private DangKyHocLaiAdapter.OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(Context context, DangKyHocLaiAdapter.OnItemClickListener listener){
        this.onItemClickListener = listener;
        this.context = context;
    }
    public interface OnItemClickListener{
        void onItemClick(DangKyHocLai dangkyhoclai);
    }
    //-----
    public void updateData(List<DangKyHocLai> newData){
        dangKyHocLais.clear();
        dangKyHocLais.addAll(newData);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DangKyHocLaiAdapter.DangKyHocLaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dang_ky_hoc_lai, parent, false);

        Button btnSignInHocLai = view.findViewById(R.id.btnSignInHocLai);
        btnSignInHocLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThiLaiFragment thiLaiFragment = new ThiLaiFragment();
                FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(android.R.id.content, thiLaiFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return new DangKyHocLaiAdapter.DangKyHocLaiViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DangKyHocLaiAdapter.DangKyHocLaiViewHolder holder, int position) {
        DangKyHocLai dangKyHocLai = dangKyHocLais.get(position);
        holder.tvCourse.setText(dangKyHocLai.getTen_mon());
        holder.tvPrice.setText(dangKyHocLai.getTien());
        holder.tvTrangThai.setText(dangKyHocLai.getTrang_thai());

        Integer isActive = dangKyHocLai.isIs_active();

        if (isActive == 1) {
            holder.btnSignInHocLai.setText("Đăng ký");
            holder.btnSignInHocLai.setEnabled(true); // Enable the button
        } else {
            holder.btnSignInHocLai.setText("Đang xử lý");
            holder.btnSignInHocLai.setEnabled(false); // Disable the button
        }
    }


    @Override
    public int getItemCount() {
        return dangKyHocLais.size();
    }


    public class DangKyHocLaiViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCourse, tvPrice, tvTrangThai;
        private Button btnSignInHocLai;

        public DangKyHocLaiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourse = itemView.findViewById(R.id.tvCourse);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            btnSignInHocLai = itemView.findViewById(R.id.btnSignInHocLai);
        }
    }

}
