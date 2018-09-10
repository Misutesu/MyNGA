package com.misutesu.project.mynga.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.view.View;

import com.misutesu.project.lib_base.base.recycler.BaseAdapter;
import com.misutesu.project.lib_base.base.recycler.BaseHolder;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.entity.Post;
import com.misutesu.project.mynga.entity.PostListTitle;
import com.misutesu.project.mynga.utils.TimeUtils;

import butterknife.BindView;

public class PostAdapter extends BaseAdapter {

    private final int TITLE = 0;
    private final int POST = R.layout.item_post;

    public interface OnPostClickListener {
        void onPostClick(Post post);
    }

    private OnPostClickListener onPostClickListener;

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof Post) {
            return POST;
        }
        return TITLE;
    }

    @Override
    protected int bindXML(int viewType) {
        return viewType;
    }

    @Override
    protected BaseHolder getHolder(View view, int viewType) {
        if (viewType == TITLE) {
            return new TitleHolder(view);
        }
        return new PostHolder(view);
    }

    public void setOnPostClickListener(OnPostClickListener onPostClickListener) {
        this.onPostClickListener = onPostClickListener;
    }

    public class TitleHolder extends BaseHolder<PostListTitle> {

        TitleHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void setData(PostListTitle data, int position) {

        }
    }

    public class PostHolder extends BaseHolder<Post> implements View.OnClickListener {

        @BindView(R.id.cv_post)
        CardView cvPost;
        @BindView(R.id.tv_title)
        AppCompatTextView tvTitle;
        @BindView(R.id.tv_user)
        AppCompatTextView tvUser;
        @BindView(R.id.tv_time)
        AppCompatTextView tvTime;
        @BindView(R.id.tv_reply_num)
        AppCompatTextView tvReplyNum;

        PostHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void setData(Post data, int position) {
            cvPost.setOnClickListener(this);
            cvPost.setTag(data);
            tvTitle.setText(data.getSubject());
            tvUser.setText(data.getAuthor());
            tvTime.setText(TimeUtils.getDistanceTime(data.getPostdate() * 1000));
            tvReplyNum.setText(String.valueOf(data.getReplies()));
        }

        @Override
        public void onClick(View v) {
            if (onPostClickListener != null) {
                onPostClickListener.onPostClick((Post) cvPost.getTag());
            }
        }
    }
}
