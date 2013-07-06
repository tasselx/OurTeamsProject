package com.xiaoma.frame.uiwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xiaoma.frame.R;

public class PullToRefreshListView extends ListView implements OnScrollListener {

	private final static String TAG = "PullToRefreshListView";

	private final static int PULL_To_REFRESH = 0;
	private final static int RELEASE_To_REFRESH = 1;
	private final static int REFRESHING = 2;
	private final static int DONE = 3;

	private LayoutInflater inflater;

	private LinearLayout headView;
	private TextView tipsTextview;
	private TextView lastUpdatedTextView;
	private ImageView arrowImageView;
	private ProgressBar progressBar;
	// 鐢ㄦ潵璁剧疆绠ご鍥炬爣鍔ㄧ敾鏁堟灉
	private RotateAnimation animation;
	private RotateAnimation reverseAnimation;

	// 鐢ㄤ簬淇濊瘉startY鐨勫�鍦ㄤ竴涓畬鏁寸殑touch浜嬩欢涓彧琚褰曚竴娆�
	private boolean isRecored;

	private int headContentWidth;
	private int headContentHeight;
	private int headContentOriginalTopPadding;

	private int startY;
	private int firstItemIndex;
	private int currentScrollState;

	private int state;

	private boolean isBack;

	public OnRefreshListener refreshListener;

	public PullToRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public PullToRefreshListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		// 璁剧疆婊戝姩鏁堟灉
		animation = new RotateAnimation(0, -180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		animation.setInterpolator(new LinearInterpolator());
		animation.setDuration(100);
		animation.setFillAfter(true);

		reverseAnimation = new RotateAnimation(-180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		reverseAnimation.setInterpolator(new LinearInterpolator());
		reverseAnimation.setDuration(100);
		reverseAnimation.setFillAfter(true);

		inflater = LayoutInflater.from(context);
		headView = (LinearLayout) inflater.inflate(
				R.layout.pull_to_refresh_head, null);

		arrowImageView = (ImageView) headView
				.findViewById(R.id.head_arrowImageView);
		arrowImageView.setMinimumWidth(50);
		arrowImageView.setMinimumHeight(50);
		progressBar = (ProgressBar) headView
				.findViewById(R.id.head_progressBar);
		tipsTextview = (TextView) headView.findViewById(R.id.head_tipsTextView);
		lastUpdatedTextView = (TextView) headView
				.findViewById(R.id.head_lastUpdatedTextView);

		headContentOriginalTopPadding = headView.getPaddingTop();

		measureView(headView);
		headContentHeight = headView.getMeasuredHeight();
		headContentWidth = headView.getMeasuredWidth();

		headView.setPadding(headView.getPaddingLeft(), -1 * headContentHeight,
				headView.getPaddingRight(), headView.getPaddingBottom());
		headView.invalidate();

		// System.out.println("鍒濆楂樺害锛�+headContentHeight);
		// System.out.println("鍒濆TopPad锛�+headContentOriginalTopPadding);

		addHeaderView(headView);
		setOnScrollListener(this);
	}

	public void onScroll(AbsListView view, int firstVisiableItem,
			int visibleItemCount, int totalItemCount) {
		firstItemIndex = firstVisiableItem;
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
		currentScrollState = scrollState;
	}

	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (firstItemIndex == 0 && !isRecored) {
				startY = (int) event.getY();
				isRecored = true;
			}
			break;

		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:

			if (state != REFRESHING) {
				if (state == DONE) {
				} else if (state == PULL_To_REFRESH) {
					state = DONE;
					changeHeaderViewByState();
				} else if (state == RELEASE_To_REFRESH) {
					state = REFRESHING;
					changeHeaderViewByState();
					onRefresh();
				}
			}

			isRecored = false;
			isBack = false;

			break;

		case MotionEvent.ACTION_MOVE:
			int tempY = (int) event.getY();
			// System.out.println("褰撳墠-婊戝姩-ACTION_MOVE Y锛�+tempY);
			if (!isRecored && firstItemIndex == 0) {
				// System.out.println("褰撳墠-婊戝姩-璁板綍鎷栨嫿鏃剁殑浣嶇疆 Y锛�+tempY);
				isRecored = true;
				startY = tempY;
			}
			if (state != REFRESHING && isRecored) {
				// 鍙互鏉惧紑鍒锋柊浜�
				if (state == RELEASE_To_REFRESH) {
					// 寰�笂鎺紝鎺ㄥ埌灞忓箷瓒冲鎺╃洊head鐨勭▼搴︼紝浣嗚繕娌℃湁鍏ㄩ儴鎺╃洊
					if ((tempY - startY < headContentHeight + 20)
							&& (tempY - startY) > 0) {
						state = PULL_To_REFRESH;
						changeHeaderViewByState();
						// System.out.println("褰撳墠-婊戝姩-ACTION_MOVE锛歊ELEASE_To_REFRESH--銆婸ULL_To_REFRESH-鐢辨澗寮�埛鏂扮姸鎬佽浆鍙樺埌涓嬫媺鍒锋柊鐘舵�");
					}
					// 涓�笅瀛愭帹鍒伴《
					else if (tempY - startY <= 0) {
						state = DONE;
						changeHeaderViewByState();
						// System.out.println("褰撳墠-婊戝姩-ACTION_MOVE锛歊ELEASE_To_REFRESH--銆婦ONE-鐢辨澗寮�埛鏂扮姸鎬佽浆鍙樺埌done鐘舵�");
					}
					// 寰�笅鎷夛紝鎴栬�杩樻病鏈変笂鎺ㄥ埌灞忓箷椤堕儴鎺╃洊head
					else {
						// 涓嶇敤杩涜鐗瑰埆鐨勬搷浣滐紝鍙敤鏇存柊paddingTop鐨勫�灏辫浜�
					}
				}
				// 杩樻病鏈夊埌杈炬樉绀烘澗寮�埛鏂扮殑鏃跺�,DONE鎴栬�鏄疨ULL_To_REFRESH鐘舵�
				else if (state == PULL_To_REFRESH) {
					// 涓嬫媺鍒板彲浠ヨ繘鍏ELEASE_TO_REFRESH鐨勭姸鎬�
					if (tempY - startY >= headContentHeight + 20
							&& currentScrollState == SCROLL_STATE_TOUCH_SCROLL) {
						state = RELEASE_To_REFRESH;
						isBack = true;
						changeHeaderViewByState();
						// System.out.println("褰撳墠-婊戝姩-PULL_To_REFRESH--銆婻ELEASE_To_REFRESH-鐢眃one鎴栬�涓嬫媺鍒锋柊鐘舵�杞彉鍒版澗寮�埛鏂�);
					}
					// 涓婃帹鍒伴《浜�
					else if (tempY - startY <= 0) {
						state = DONE;
						changeHeaderViewByState();
						// System.out.println("褰撳墠-婊戝姩-PULL_To_REFRESH--銆婦ONE-鐢盌one鎴栬�涓嬫媺鍒锋柊鐘舵�杞彉鍒癲one鐘舵�");
					}
				}
				// done鐘舵�涓�
				else if (state == DONE) {
					if (tempY - startY > 0) {
						state = PULL_To_REFRESH;
						changeHeaderViewByState();
						// System.out.println("褰撳墠-婊戝姩-DONE--銆婸ULL_To_REFRESH-鐢眃one鐘舵�杞彉鍒颁笅鎷夊埛鏂扮姸鎬�);
					}
				}

				// 鏇存柊headView鐨剆ize
				if (state == PULL_To_REFRESH) {
					int topPadding = (int) ((-1 * headContentHeight + (tempY - startY)));
					headView.setPadding(headView.getPaddingLeft(), topPadding,
							headView.getPaddingRight(),
							headView.getPaddingBottom());
					headView.invalidate();
					// System.out.println("褰撳墠-涓嬫媺鍒锋柊PULL_To_REFRESH-TopPad锛�+topPadding);
				}

				// 鏇存柊headView鐨刾addingTop
				if (state == RELEASE_To_REFRESH) {
					int topPadding = (int) ((tempY - startY - headContentHeight));
					headView.setPadding(headView.getPaddingLeft(), topPadding,
							headView.getPaddingRight(),
							headView.getPaddingBottom());
					headView.invalidate();
					// System.out.println("褰撳墠-閲婃斁鍒锋柊RELEASE_To_REFRESH-TopPad锛�+topPadding);
				}
			}
			break;
		}
		return super.onTouchEvent(event);
	}

	// 褰撶姸鎬佹敼鍙樻椂鍊欙紝璋冪敤璇ユ柟娉曪紝浠ユ洿鏂扮晫闈�
	private void changeHeaderViewByState() {
		switch (state) {
		case RELEASE_To_REFRESH:

			arrowImageView.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.GONE);
			tipsTextview.setVisibility(View.VISIBLE);
			lastUpdatedTextView.setVisibility(View.VISIBLE);

			arrowImageView.clearAnimation();
			arrowImageView.startAnimation(animation);

			tipsTextview.setText(R.string.pull_to_refresh_release_label);

			break;
		case PULL_To_REFRESH:

			progressBar.setVisibility(View.GONE);
			tipsTextview.setVisibility(View.VISIBLE);
			lastUpdatedTextView.setVisibility(View.VISIBLE);
			arrowImageView.clearAnimation();
			arrowImageView.setVisibility(View.VISIBLE);
			if (isBack) {
				isBack = false;
				arrowImageView.clearAnimation();
				arrowImageView.startAnimation(reverseAnimation);
			}
			tipsTextview.setText(R.string.pull_to_refresh_pull_label);

			break;

		case REFRESHING:
			headView.setPadding(headView.getPaddingLeft(),
					headContentOriginalTopPadding, headView.getPaddingRight(),
					headView.getPaddingBottom());
			headView.invalidate();

			progressBar.setVisibility(View.VISIBLE);
			arrowImageView.clearAnimation();
			arrowImageView.setVisibility(View.GONE);
			tipsTextview.setText(R.string.pull_to_refresh_refreshing_label);
			lastUpdatedTextView.setVisibility(View.GONE);
			break;
		case DONE:
			headView.setPadding(headView.getPaddingLeft(), -1
					* headContentHeight, headView.getPaddingRight(),
					headView.getPaddingBottom());
			headView.invalidate();

			progressBar.setVisibility(View.GONE);
			arrowImageView.clearAnimation();
			arrowImageView.setImageResource(R.drawable.ic_pulltorefresh_arrow);

			tipsTextview.setText(R.string.pull_to_refresh_pull_label);
			lastUpdatedTextView.setVisibility(View.VISIBLE);
			break;
		}
	}

	public void clickRefresh() {
		setSelection(0);
		state = REFRESHING;
		changeHeaderViewByState();
		onRefresh();
	}

	public void setOnRefreshListener(OnRefreshListener refreshListener) {
		this.refreshListener = refreshListener;
	}

	public interface OnRefreshListener {
		public void onRefresh();
	}

	public void onRefreshComplete(String update) {
		lastUpdatedTextView.setText(update);
		onRefreshComplete();
	}

	public void onRefreshComplete() {
		state = DONE;
		changeHeaderViewByState();
	}

	private void onRefresh() {
		if (refreshListener != null) {
			refreshListener.onRefresh();
		}
	}

	private void measureView(View child) {
		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}

}
