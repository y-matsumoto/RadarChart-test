package com.example.radarchart;

import java.util.ArrayList;
import java.util.List;

public class ChartUtils {

	public ChartUtils() {
	}

	public static List<Coordinate> getRadarChartCoordinate(final float[] points,final float ratio) {

		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		final int angle = 360 / points.length;
		int origin = 90;

		for (float point : points) {
			point *= ratio;
			double radian = Math.toRadians(origin);
			coordinates.add(new Coordinate((float) Math.cos(radian) * point,
					(float) Math.sin(radian) * point));
			origin += angle;
		}

		return coordinates;
	}

	public static List<Coordinate> getMaxRadarChartCoordinate(
			final int pointsLength, final float maxValue,final float ratio) {

		float[] temp = new float[pointsLength];

		for (int i = 0; i < pointsLength; i++) {
			temp[i] = maxValue;
		}

		return getRadarChartCoordinate(temp,ratio);
	}

}
