package in.elanic.elanicanandab.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import in.elanic.elanicanandab.R;
import in.elanic.elanicanandab.model.QuestionModel;
import in.elanic.elanicanandab.view.interfaces.RecyclerItemClickListener;

/**
 * Created by Anand A <anandabktda@gmail.com/>
 * Its for question list item handling
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.EmployeeViewHolder> {

    private List<QuestionModel.ItemsBean> dataList;
    private RecyclerItemClickListener recyclerItemClickListener;
    private Context context;

    public QuestionAdapter(Context context, List<QuestionModel.ItemsBean> dataList, RecyclerItemClickListener recyclerItemClickListener) {
        this.context = context;
        this.dataList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }


    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtQuestionTitle.setText(dataList.get(position).getTitle());
        holder.txtQuestionBrief.setText(dataList.get(position).getTitle());
        holder.txtQuestionFilePath.setText(dataList.get(position).getOwner().getDisplayName());
        Glide.with(context).load(dataList.get(position).getOwner().getProfileImage()).into(holder.ivUserImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(dataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView txtQuestionTitle, txtQuestionBrief, txtQuestionFilePath;
        ImageView ivUserImage;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            txtQuestionTitle = itemView.findViewById(R.id.tv_question_title);
            ivUserImage = itemView.findViewById(R.id.iv_user_image);
            txtQuestionBrief = itemView.findViewById(R.id.tv_question_detail);
            txtQuestionFilePath = itemView.findViewById(R.id.tv_username);

        }
    }
}