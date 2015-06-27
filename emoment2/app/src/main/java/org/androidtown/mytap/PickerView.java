package org.androidtown.mytap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class PickerView extends RelativeLayout {

    private ListView listView = null;
    ListView_item listView_item;
    private View selector = null;
    private PickerListAdapter adapter = null;
    private Context context;
    private OnSelectListener mSelectListener;

    private boolean setListView = false;
    private int itemsToShow = 5, middleCell, cellHeight, firstVisibleItem = 0;
    private int selectorColor = Color.parseColor("#336b2b66");

    private ArrayList<Bitmap> items;

    public PickerView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public PickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initView();
    }

    public PickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.picker_view, this, true);

        listView = (ListView) findViewById(R.id.listview);
        selector = findViewById(R.id.chooser);
        selector.setBackgroundColor(selectorColor);

    }
    public void setList(ArrayList<Bitmap> items) {
        this.items=items;
        adapter = new PickerListAdapter(items);
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }




    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        // we set the listView here, because we need to calculate the cells size, only
        // after listView already has height
        if (!setListView && adapter != null) {
            setListView = true;

            int height = listView.getHeight();
            cellHeight = height / itemsToShow;

            middleCell = itemsToShow / 2;


            // set the selector rect on the middle cell, by the top margin

            if (selector.getLayoutParams() instanceof MarginLayoutParams) {
                MarginLayoutParams p = (MarginLayoutParams) selector.getLayoutParams();
                p.height = cellHeight;
                p.setMargins(0, cellHeight * middleCell, 0, 0);
                selector.requestLayout();
            }


            // scroll listview to the middle.
            listView.setSelection(adapter.getCount() / 2);
            firstVisibleItem = adapter.getCount() / 2;

            listView.setOnScrollListener(new AbsListView.OnScrollListener() {

                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                    if (scrollState == SCROLL_STATE_IDLE) {

                        View child = view.getChildAt(0);    // first visible child

                        if (child != null) {
                            firstVisibleItem = listView.getFirstVisiblePosition();
                            Rect r = new Rect(0, 0, child.getWidth(), child.getHeight());     // set this initially, as required by the docs
                            double height = child.getHeight() * 1.0;

                            view.getChildVisibleRect(child, r, null);
                            if (Math.abs(r.height()) < (int) height / 2) {
                                // show next child
                                firstVisibleItem++;
                                listView.setSelection(firstVisibleItem);
                            } else {
                                // show this child
                                listView.setSelection(firstVisibleItem);
                            }
                        }
                    }

                }

                @Override
                public void onScroll(AbsListView view, int firstVisible, int visibleItemCount, int totalItemCount) {

                }
            });
        }

    }
    public void setOnSelectListener(OnSelectListener listener)
    {
        mSelectListener= listener;
    }
    private void performSelect(int position)
    {
        if(mSelectListener!=null)
            mSelectListener.onSelect(items.get(position));
    }
    public interface OnSelectListener
    {
        void onSelect(Bitmap bitmap);
    }

    public class PickerListAdapter extends BaseAdapter{
        @Override
        public Object getItem(int position) {
            return null;
        }

        public PickerListAdapter(ArrayList<Bitmap> sList) {
            items = sList;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public int getCount() {
            return items.size();
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if(convertView==null)
            {
                listView_item = new ListView_item(context);

            }
            else
            {
            listView_item=(ListView_item)convertView;

            }

            performSelect(position);
            listView_item.setImage(items.get(position));

            return listView_item;
        }
    }
}
