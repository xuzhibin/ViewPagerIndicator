package com.shizhefei.indicator.tabmain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.indicator.R;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter;

public class TabMainActivity extends FragmentActivity {
	private IndicatorViewPager indicatorViewPager;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_tabmain);
		ViewPager viewPager = (ViewPager) findViewById(R.id.tabmain_viewPager);
		Indicator indicator = (Indicator) findViewById(R.id.tabmain_indicator);
		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		// 禁止viewpager的滑动事件
		viewPager.setCanScroll(false);
		// 设置viewpager保留界面不重新加载的页面数量
		viewPager.setOffscreenPageLimit(4);
		// 默认是1,，自动预加载左右两边的界面。设置viewpager预加载数为0。只加载加载当前界面。
		viewPager.setPrepareNumber(0);
	}

	private class MyAdapter extends IndicatorFragmentPagerAdapter {
		private String[] tabNames = { "主页", "消息", "发现", "我" };
		private int[] tabIcons = { R.drawable.maintab_1_selector, R.drawable.maintab_2_selector, R.drawable.maintab_3_selector,
				R.drawable.maintab_4_selector };
		private LayoutInflater inflater;

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
			inflater = LayoutInflater.from(getApplicationContext());
		}

		@Override
		public int getCount() {
			return tabNames.length;
		}

		@Override
		public View getViewForTab(int position, View convertView, ViewGroup container) {
			if (convertView == null) {
				convertView = (TextView) inflater.inflate(R.layout.tab_main, container, false);
			}
			TextView textView = (TextView) convertView;
			textView.setText(tabNames[position]);
			textView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[position], 0, 0);
			return textView;
		}

		@Override
		public Fragment getFragmentForPage(int position) {
			MainFragment mainFragment = new MainFragment();
			Bundle bundle = new Bundle();
			bundle.putString(MainFragment.INTENT_STRING_TABNAME, tabNames[position]);
			bundle.putInt(MainFragment.INTENT_INT_INDEX, position);
			mainFragment.setArguments(bundle);
			return mainFragment;
		}
	}
}
