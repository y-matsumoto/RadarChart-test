package com.example.radarchart;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class RadarChart extends View {

	// Chart作成フラグ
	private boolean _isCreate = false;
	
	private float[] _createArrayChart;
	
	private static final int CHART_MAX_VALUE = 100;
	
	// 表示比率（倍率）
	private float _ratio = 1;

	public RadarChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * Chart作成
	 */
	public void createChart(final float[] array) throws IllegalArgumentException {

		for (float a : array) {
			if (a > CHART_MAX_VALUE)
				throw new IllegalArgumentException();
		}

		_createArrayChart = array;
	}
	
	public void setRatio(float ratio){
		_ratio = ratio;
	}

	/**
	 * Chart表示
	 */
	public void show() {
		_isCreate = true;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {

		if (!_isCreate)
			return;

		Coordinate centerPoint = new Coordinate(100 * _ratio, 100 * _ratio);

		Paint paint = new Paint();
		paint.setAntiAlias(true);

		// バックグラウンド背景
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.GRAY);
		canvas.drawCircle(100 * _ratio, 100 * _ratio, 100 * _ratio, paint);

		List<Coordinate> coordinates = ChartUtils
				.getRadarChartCoordinate(_createArrayChart,_ratio);

		Path path = new Path();

		float originInitPositionX = 100 * _ratio;
		float originInitPositionY = 100 * _ratio;

		paint.setStyle(Paint.Style.FILL);
		for (Coordinate c : coordinates) {
			if (coordinates.get(0).equals(c))
				path.moveTo(c.getX() + originInitPositionX, c.getY()
						+ originInitPositionY);
			else
				path.lineTo(c.getX() + originInitPositionX, c.getY()
						+ originInitPositionY);
		}
		path.lineTo(coordinates.get(0).getX() + originInitPositionX,
				coordinates.get(0).getY() + originInitPositionY);

		paint.setColor(Color.RED);
		canvas.drawPath(path, paint);

		// 中心店の確認用の線
		// paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(1);
		paint.setStyle(Paint.Style.STROKE);
		List<Coordinate> coordinatesMax = ChartUtils
				.getMaxRadarChartCoordinate(8, 100,_ratio);

		for (Coordinate c : coordinatesMax) {
			canvas.drawLine(centerPoint.getX(), centerPoint.getY(), c.getX()
					+ originInitPositionX, c.getY() + originInitPositionY,
					paint);
		}

	}

}
