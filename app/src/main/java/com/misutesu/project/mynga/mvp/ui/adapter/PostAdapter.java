package com.misutesu.project.mynga.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.misutesu.project.lib_base.base.recycler.BaseAdapter;
import com.misutesu.project.lib_base.base.recycler.BaseHolder;
import com.misutesu.project.lib_base.utils.ViewUtils;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.entity.Post;
import com.misutesu.project.mynga.entity.PostListTitle;
import com.misutesu.project.mynga.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;

public class PostAdapter extends BaseAdapter {

    private final int TITLE = R.layout.item_post_list_header;
    private final int POST = R.layout.item_post;
    private final int MORE = R.layout.item_post_list_more;

    public interface OnPostClickListener {
        void onMenuClick(PostListTitle title, int position);

        void onPostClick(Post post);

        void onRetryLoadMoreClick();
    }

    private OnPostClickListener onPostClickListener;

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof PostListTitle) {
            return TITLE;
        } else if (mList.get(position) instanceof Post) {
            return POST;
        }
        return MORE;
    }

    @Override
    protected BaseHolder getHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TITLE) {
            return new TitleHolder(getView(viewGroup, viewType));
        } else if (viewType == POST) {
            return new PostHolder(getView(viewGroup, viewType));
        }
        return new MoreHolder(getView(viewGroup, viewType));
    }

    public void setOnPostClickListener(OnPostClickListener onPostClickListener) {
        this.onPostClickListener = onPostClickListener;
    }

    public void setLoadMoreStatus(int status) {
        ((Footer) mList.get(mList.size() - 1)).setStatus(status);
        notifyItemChanged(mList.size() - 1);
    }

    @Override
    public void setData(List list) {
        list.add(new Footer());
        super.setData(list);
    }

    @Override
    public void addData(List list) {
        if (list != null) {
            int oldSize = mList.size();
            mList.addAll(mList.size() - 1, list);
            notifyItemRangeInserted(oldSize - 1, list.size());
        }
    }

    public class TitleHolder extends BaseHolder<PostListTitle> implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

        @BindView(R.id.iv_menu)
        AppCompatImageView ivMenu;

        private PopupMenu popupMenu;

        TitleHolder(@NonNull View itemView) {
            super(itemView);
            ViewUtils.setBorderlessBg(ivMenu);
            ivMenu.setOnClickListener(this);

            popupMenu = new PopupMenu(itemView.getContext(), ivMenu);
            Menu menu = popupMenu.getMenu();
            menu.add(0, PostListTitle.TOP, 0, R.string.plate_top);
            menu.add(0, PostListTitle.HOT, 0, R.string.plate_hot);
            menu.add(0, PostListTitle.ESSENCE, 0, R.string.plate_essence);
            menu.add(0, PostListTitle.HEADER, 0, R.string.plate_header);
            menu.add(0, PostListTitle.CHILD, 0, R.string.plate_child);
            popupMenu.setOnMenuItemClickListener(this);
        }

        @Override
        public void setData(PostListTitle data, int position) {
            ivMenu.setTag(data);

            Menu menu = popupMenu.getMenu();

            PostListTitle.PlateHeader header = data.getPlateHeader();
            menu.getItem(PostListTitle.HEADER).setVisible(!(header == null || header.isEmpty()));
            if (!(header == null || header.isEmpty())) {
                menu.getItem(PostListTitle.HEADER).setTitle(header.getTitle());
            }

            menu.getItem(PostListTitle.CHILD).setVisible(!data.getChildPlates().isEmpty());
        }

        @Override
        public void onClick(View v) {
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (onPostClickListener != null) {
                onPostClickListener.onMenuClick((PostListTitle) ivMenu.getTag(), menuItem.getItemId());
            }
            return true;
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

    class Footer {
        private int status;

        void setStatus(int status) {
            this.status = status;
        }
    }

    public class MoreHolder extends BaseHolder<Footer> implements View.OnClickListener {

        @BindView(R.id.progress_bar)
        ProgressBar progressBar;
        @BindView(R.id.btn_retry)
        MaterialButton btnRetry;

        MoreHolder(@NonNull View itemView) {
            super(itemView);
            btnRetry.setOnClickListener(this);
        }

        @Override
        public void setData(Footer data, int position) {
            itemView.setTag(data);
            switch (data.status) {
                case 0:
                    progressBar.setVisibility(View.VISIBLE);
                    btnRetry.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    progressBar.setVisibility(View.INVISIBLE);
                    btnRetry.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    progressBar.setVisibility(View.INVISIBLE);
                    btnRetry.setVisibility(View.INVISIBLE);
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            if (onPostClickListener != null) {
                onPostClickListener.onRetryLoadMoreClick();
            }
        }
    }
}
