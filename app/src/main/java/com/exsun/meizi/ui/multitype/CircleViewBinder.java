package com.exsun.meizi.ui.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.bmob.MyUser;
import com.exsun.meizi.entity.bmob.TalkMoodEntity;
import com.exsun.meizi.entity.eventbus.EventBusObject;
import com.exsun.meizi.tool.ImageLoaderUtils;
import com.exsun.meizi.tool.Toasts;
import com.exsun.meizi.tool.utils;
import com.exsun.meizi.widget.like_dianzan.FavortListAdapter;
import com.exsun.meizi.widget.like_dianzan.FavortListView;
import com.exsun.meizi.widget.like_dianzan.spannable.ISpanClick;
import com.yuyh.library.AppUtils;
import com.yuyh.library.utils.DimenUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/22
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class CircleViewBinder extends ItemViewBinder<TalkMoodEntity, CircleViewBinder.CircleViewHolder>
{

    public RefreshListener listener;

    public interface RefreshListener
    {
        void refresh();
    }

    public void setRefreshListener(RefreshListener refreshListener)
    {
        this.listener = refreshListener;
    }

    @NonNull
    @Override
    protected CircleViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent)
    {
        View view = inflater.inflate(R.layout.item_community, parent, false);
        return new CircleViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull CircleViewHolder holder, @NonNull TalkMoodEntity item)
    {
        holder.setItem(item);
    }

    class CircleViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.head_portrait)
        ImageView headPortrait;
        @Bind(R.id.category_author)
        TextView categoryAuthor;
        @Bind(R.id.category_date)
        TextView categoryDate;
        @Bind(R.id.category_desc)
        TextView categoryDesc;
        @Bind(R.id.location)
        TextView location;
        @Bind(R.id.cardview)
        CardView cardview;
        @Bind(R.id.likeBtn)
        TextView likeBtn;
        @Bind(R.id.commentBtn)
        TextView commentBtn;
        @Bind(R.id.digCommentBody)
        LinearLayout digCommentBody;
        @Bind(R.id.favortListTv)
        FavortListView favortListView;

        private FavortListAdapter adapter;
        private List<MyUser> userList;
        private boolean isZan = false;

        public CircleViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        TalkMoodEntity mItem;
        List<MyUser> users = new ArrayList<>();

        void setItem(final TalkMoodEntity item)
        {
            mItem = item;
            ImageLoaderUtils.display(itemView.getContext(), headPortrait, R.mipmap.ic_launcher);
            categoryAuthor.setText(item.getNickName());
            categoryDate.setText("发表于 " + item.getPublishedTime());
            categoryDesc.setText(item.getContent());
            location.setText(item.getLocation());
            BmobRelation likes = item.getLikes();
            adapter = new FavortListAdapter(favortListView.getContext(), users);
            favortListView.setAdapter(adapter);
            favortListView.setSpanClickListener(new ISpanClick()
            {
                @Override
                public void onClick(int position)
                {
                    if (userList != null && !userList.isEmpty())
                    {
                        MyUser myUser = userList.get(position);
                        Toast.makeText(AppUtils.getAppContext(), "跳转至" + myUser.getNickName() + "个人页面",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
            digCommentBody.setVisibility(View.GONE);
            if (likes != null)
            {
                likeBtn.setEnabled(false);
                loadFavoratData();
            } else
            {
                favortListView.setVisibility(View.GONE);
                likeBtn.setEnabled(true);
            }
            utils.setTextDrawable(likeBtn, likeBtn.getContext(), R.mipmap.zan, utils.LEFT, DimenUtils.dpToPxInt(5));
        }

        private void loadFavoratData()
        {
            BmobQuery<MyUser> query = new BmobQuery<>();
            query.addWhereRelatedTo("likes", new BmobPointer(mItem));
            query.findObjects(new FindListener<MyUser>()
            {
                @Override
                public void done(final List<MyUser> list, BmobException e)
                {
                    if (e == null)
                    {
                        userList = list;
                        if (userList.isEmpty())
                        {
                            favortListView.setVisibility(View.GONE);
                        } else
                        {
                            digCommentBody.setVisibility(View.VISIBLE);
                            favortListView.setVisibility(View.VISIBLE);
                        }
                        MyUser currentUser = BmobUser.getCurrentUser(MyUser.class);
                        int size = userList.size();
                        for (int i = 0; i < size; i++)
                        {
                            if (currentUser.getUsername().equals(userList.get(i).getUsername()))
                            {
                                utils.setTextDrawable(likeBtn, likeBtn.getContext(), R.mipmap.zaned, utils.LEFT, DimenUtils.dpToPxInt(5));
                                isZan = true;
                            }
                        }
                        likeBtn.setEnabled(true);
                        adapter.setDatas(list);
                    } else
                    {
                        Log.e("", "获取点赞数据失败");
                    }
                }
            });
        }

        @OnClick({R.id.likeBtn, R.id.commentBtn, R.id.cardview})
        public void onViewClicked(View view)
        {

            switch (view.getId())
            {
                case R.id.likeBtn:
                    updateData();
                    break;
                case R.id.commentBtn:
                    Toasts.showSingleShort("评论");
                    EventBus.getDefault().post(new EventBusObject.OpenEditObject());
                    break;
                case R.id.cardview:
                    Toasts.showSingleShort("点击动态跳转");
                    break;
                default:
                    break;
            }
        }

        /**
         * 更新数据
         */
        private void updateData()
        {
            BmobRelation relation = new BmobRelation();
            MyUser user = BmobUser.getCurrentUser(MyUser.class);
            if (isZan)
            {
                //取消赞
                relation.remove(user);
            } else
            {
                relation.add(user);
            }
            mItem.setLikes(relation);
            mItem.update(new UpdateListener()
            {
                @Override
                public void done(BmobException e)
                {
                    if (e == null)
                    {
                        isZan = !isZan;
                        listener.refresh();
                    } else
                    {
                        Log.e("", "点赞失败或取消点赞失败");
                    }
                }
            });
        }

    }


}
