package com.wuzhupc.widget.imagezoom.easing;

public class Cubic
{
	public static float easeOut(float elapsedMs, float durationMs)
	{
		float progress = elapsedMs / durationMs - 1.0f;

		return progress * progress * progress + 1;
	}
}
