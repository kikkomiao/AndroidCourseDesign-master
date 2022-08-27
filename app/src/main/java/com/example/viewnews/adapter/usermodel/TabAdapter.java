package com.example.viewnews.adapter.usermodel;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.viewnews.R;
import com.example.viewnews.bean.NewsBean;

import java.util.List;

//自定义新闻列表的适配器
public class TabAdapter extends BaseAdapter {
    //  新闻内容列表
    private List<NewsBean.ResultBean.DataBean> list;

    private Context context;

    //设置正常加载图片的个数
    private int IMAGE_00 = -1;

    private int IMAGE_01 = 0;

    private int IMAGE_02 = 1;

    private int IMAGE_03 = 2;


    //    private int VIEW_COUNT = 4;
// newsfragment TabAdapter adapter = new TabAdapter(getActivity(), contentItems);
    public TabAdapter(Context context, List<NewsBean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //得到不同item的总数
//    @Override
//    public int getViewTypeCount() {
//        System.out.println("VIEWCOUNT"+VIEW_COUNT);
//        return VIEW_COUNT;
//    }

    //    得到当前新闻子项item的类型
    @Override
    public int getItemViewType(int position) {
        System.out.println("位置" + position);
        int result = -6;
        if ((!list.get(position).getThumbnail_pic_s().equals("")) &&
                list.get(position).getThumbnail_pic_s02() != null &&
                list.get(position).getThumbnail_pic_s03() != null) {
            System.out.println("333333333333333333333333333333333~~~~~~~");
            result = IMAGE_03;
        } else if ((!list.get(position).getThumbnail_pic_s().equals("")) &&
                list.get(position).getThumbnail_pic_s02() != null &&
                list.get(position).getThumbnail_pic_s03() == null) {
            System.out.println("22222222222222222222222222222~~~~~~~~~~~~~");
            result = IMAGE_02;
        } else if ((!list.get(position).getThumbnail_pic_s().equals("")) &&
                list.get(position).getThumbnail_pic_s02() == null &&
                list.get(position).getThumbnail_pic_s03() == null) {
            result = IMAGE_01;
            System.out.println("11111111111111111111111111111");
        } else {
            result = IMAGE_00;
            System.out.println("0000000000000000000000000000");
        }
        return result;
    }

    //    重写getview方法
//    position判断当前显示的项目在屏幕上的位置，然后通过position在定义的集合中取值显示在屏幕上
//    提升ListView的运行效率，参数convertView用于将之前加载好的布局进行缓存，以便以后可以重用：https://blog.csdn.net/xiao_ziqiang/article/details/50812471
//    父本 存放被加载出来的每一个项目视图
//    https://blog.csdn.net/CXinQuan/article/details/81584570
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == 0) {
//            ViewHolder通常出现在适配器里，为的是listview滚动的时候快速设置值，而不必每次都重新创建很多对象，从而提升性能
            ImageBig_ViewHolder holder;
//            System.out.println("imagebigconvertview"+convertView.getTag());
            convertView = View.inflate(context, R.layout.news_big, null);
            holder = new ImageBig_ViewHolder();
//                传入的 root 为空，则会直接将加载好的 xml 布局返回，而这种情况下返回的这个 View 没有参数，也没有父布局。
            holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
//          为了重用  但是有bug!!
            convertView.setTag(holder);
            //获取数据重新赋值
            holder.title.setText(list.get(position).getTitle());
            holder.author_name.setText(list.get(position).getAuthor_name());
//            图片加载框架 load(图片地址) into(根据id找到的组件)
            Glide.with(context)
                    .load(list.get(position).getThumbnail_pic_s())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.defaultbg)
                    .error(R.drawable.pic_hot)
                    .into(holder.image);
        } else {
            int type = getItemViewType(position);
            if (type == IMAGE_00) {
                Image00_ViewHolder holder;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.news_nopic, null);
                    holder = new Image00_ViewHolder();
                    //查找控件
                    holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
                    holder.title = (TextView) convertView.findViewById(R.id.title);
                    //将ViewHolder对象存储在View中
//                convertView.setTag(holder);
                } else {
//                    String jdg = convertView.getTag().toString().substring(37, 39);
//                    System.out.println("00jdg" + jdg);
//                    System.out.println("image00convertview" + convertView.getTag());
//                    if(!convertView.getTag().toString().substring(37, 39).equals("00")){
//                        convertView = View.inflate(context, R.layout.news_nopic, null);
//                        holder = new Image00_ViewHolder();
//                        //查找控件
//                        holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
//                        holder.title = (TextView) convertView.findViewById(R.id.title);
//                    }else {
//                        holder = (Image00_ViewHolder) convertView.getTag();
//                    }
                    if (convertView.getTag() != null) {
                        System.out.println("image00convertview" + convertView.getTag());
                        String jdg = convertView.getTag().toString().substring(37, 39);
                        System.out.println("00jdg" + jdg);
                        if (!convertView.getTag().toString().substring(37, 39).equals("00")) {
                            convertView = View.inflate(context, R.layout.item_layout01, null);
                            holder = new Image00_ViewHolder();
                            //查找控件
                            holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
                            holder.title = (TextView) convertView.findViewById(R.id.title);
//                            holder.image = (ImageView) convertView.findViewById(R.id.image);
                            convertView.setTag(holder);
                        } else {
                            holder = (Image00_ViewHolder) convertView.getTag();
                        }
                    } else {
                        convertView = View.inflate(context, R.layout.item_layout01, null);
                        holder = new Image00_ViewHolder();
                        //查找控件
                        holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
                        holder.title = (TextView) convertView.findViewById(R.id.title);
//                        holder.image = (ImageView) convertView.findViewById(R.id.image);
                        convertView.setTag(holder);
                    }
                }
                //获取数据重新赋值
                holder.title.setText(list.get(position).getTitle());
                holder.author_name.setText(list.get(position).getAuthor_name());
            }
            if (type == IMAGE_01) {
                Image01_ViewHolder holder;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.item_layout01, null);
                    holder = new Image01_ViewHolder();
                    //查找控件
                    holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
                    holder.title = (TextView) convertView.findViewById(R.id.title);
                    holder.image = (ImageView) convertView.findViewById(R.id.image);
                    convertView.setTag(holder);
                } else {
                    if (convertView.getTag() != null) {
                        System.out.println("image01convertview" + convertView.getTag());
                        String jdg = convertView.getTag().toString().substring(37, 39);
                        System.out.println("01jdg" + jdg);
                        if (!convertView.getTag().toString().substring(37, 39).equals("01")) {
                            convertView = View.inflate(context, R.layout.item_layout01, null);
                            holder = new Image01_ViewHolder();
                            //查找控件
                            holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
                            holder.title = (TextView) convertView.findViewById(R.id.title);
                            holder.image = (ImageView) convertView.findViewById(R.id.image);
                            convertView.setTag(holder);
                        } else {
                            holder = (Image01_ViewHolder) convertView.getTag();
                        }
                    } else {
                        convertView = View.inflate(context, R.layout.item_layout01, null);
                        holder = new Image01_ViewHolder();
                        //查找控件
                        holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
                        holder.title = (TextView) convertView.findViewById(R.id.title);
                        holder.image = (ImageView) convertView.findViewById(R.id.image);
                        convertView.setTag(holder);
                    }
                }
                //获取数据重新赋值
                holder.title.setText(list.get(position).getTitle());
                holder.author_name.setText(list.get(position).getAuthor_name());
                /**
                 * DiskCacheStrategy.NONE： 表示不缓存任何内容。
                 */
                Glide.with(context)
                        .load(list.get(position).getThumbnail_pic_s())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.defaultbg)
                        .error(R.drawable.defaultbg)
                        .into(holder.image);


            } else if (type == IMAGE_02) {
                Image02_ViewHolder holder;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.item_layout02, null);
                    holder = new Image02_ViewHolder();
                    //查找控件
                    holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
                    holder.image001 = (ImageView) convertView.findViewById(R.id.image001);
                    holder.image002 = (ImageView) convertView.findViewById(R.id.image002);
                    holder.title = (TextView) convertView.findViewById(R.id.title);
                    //将ViewHolder对象存储在View中
//                convertView.setTag(holder);
                } else {
                    if (convertView.getTag() != null) {
                        String jdg = convertView.getTag().toString().substring(37, 39);
                        System.out.println("02jdg" + jdg);
                        System.out.println("image02convertview" + convertView.getTag());
                        if (!convertView.getTag().toString().substring(37, 39).equals("02")) {
                            convertView = View.inflate(context, R.layout.item_layout02, null);
                            holder = new Image02_ViewHolder();
                            //查找控件
                            holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
                            holder.image001 = (ImageView) convertView.findViewById(R.id.image001);
                            holder.image002 = (ImageView) convertView.findViewById(R.id.image002);
                            holder.title = (TextView) convertView.findViewById(R.id.title);
                        } else {
                            holder = (Image02_ViewHolder) convertView.getTag();
                        }
                    } else {
                        convertView = View.inflate(context, R.layout.item_layout02, null);
                        holder = new Image02_ViewHolder();
                        //查找控件
                        holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
                        holder.image001 = (ImageView) convertView.findViewById(R.id.image001);
                        holder.image002 = (ImageView) convertView.findViewById(R.id.image002);
                        holder.title = (TextView) convertView.findViewById(R.id.title);
                    }
                }
                //获取数据重新赋值
                holder.title.setText(list.get(position).getTitle());
                holder.author_name.setText(list.get(position).getAuthor_name());
                Glide.with(context)
                        .load(list.get(position).getThumbnail_pic_s())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.defaultbg)
                        .error(R.drawable.defaultbg)
                        .into(holder.image001);

                Glide.with(context)
                        .load(list.get(position).getThumbnail_pic_s02())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.defaultbg)
                        .error(R.drawable.defaultbg)
                        .into(holder.image002);

            } else if (type == IMAGE_03 && position != 0) {
                Image03_ViewHolder holder;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.item_layout03, null);
                    holder = new Image03_ViewHolder();
                    //查找控件
                    holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
                    holder.image01 = (ImageView) convertView.findViewById(R.id.image01);
                    holder.image02 = (ImageView) convertView.findViewById(R.id.image02);
                    holder.image03 = (ImageView) convertView.findViewById(R.id.image03);
                    holder.title = (TextView) convertView.findViewById(R.id.title);
                    convertView.setTag(holder);
                } else {
                    if (convertView.getTag() != null) {
                        String jdg = convertView.getTag().toString().substring(37, 39);
                        System.out.println("03jdg" + jdg);
                        System.out.println("image03convertview" + convertView.getTag());
                        if (!convertView.getTag().toString().substring(37, 39).equals("03")) {
                            convertView = View.inflate(context, R.layout.item_layout03, null);
                            holder = new Image03_ViewHolder();
                            //查找控件
                            holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
                            holder.image01 = (ImageView) convertView.findViewById(R.id.image01);
                            holder.image02 = (ImageView) convertView.findViewById(R.id.image02);
                            holder.image03 = (ImageView) convertView.findViewById(R.id.image03);
                            holder.title = (TextView) convertView.findViewById(R.id.title);
                            convertView.setTag(holder);
                        } else {
                            holder = (Image03_ViewHolder) convertView.getTag();
                        }
                    } else {
                        convertView = View.inflate(context, R.layout.item_layout03, null);
                        holder = new Image03_ViewHolder();
                        //查找控件
                        holder.author_name = (TextView) convertView.findViewById(R.id.author_name);
                        holder.image01 = (ImageView) convertView.findViewById(R.id.image01);
                        holder.image02 = (ImageView) convertView.findViewById(R.id.image02);
                        holder.image03 = (ImageView) convertView.findViewById(R.id.image03);
                        holder.title = (TextView) convertView.findViewById(R.id.title);
                        convertView.setTag(holder);
                    }
                }
                //获取数据重新赋值
                holder.title.setText(list.get(position).getTitle());
                holder.author_name.setText(list.get(position).getAuthor_name());
                Glide.with(context)
                        .load(list.get(position).getThumbnail_pic_s())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.defaultbg)
                        .error(R.drawable.defaultbg)
                        .into(holder.image01);

                Glide.with(context)
                        .load(list.get(position).getThumbnail_pic_s02())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.defaultbg)
                        .error(R.drawable.defaultbg)
                        .into(holder.image02);

                Glide.with(context)
                        .load(list.get(position).getThumbnail_pic_s03())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.defaultbg)
                        .error(R.drawable.defaultbg)
                        .into(holder.image03);

            }
        }
        return convertView;
    }

    //新增3个内部类
    class Image00_ViewHolder {
        TextView title, author_name;
//        ImageView image;
    }

    class Image01_ViewHolder {
        TextView title, author_name;
        ImageView image;
    }

    class Image02_ViewHolder {
        TextView title, author_name;
        ImageView image001, image002;
    }

    class Image03_ViewHolder {
        TextView title, author_name;
        ImageView image01, image02, image03;
    }

    class ImageBig_ViewHolder {
        TextView title, author_name;
        ImageView image;
    }
}