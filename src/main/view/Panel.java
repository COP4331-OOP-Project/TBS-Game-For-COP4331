package view;

import java.awt.Graphics;

public abstract class Panel {
	private static final int TICKS_PER_IMAGE = 5;
	private static final int IMAGES_TO_LOOP = 4;
	private int animationImage = 0;
	private int animationCount = 0;

	public void updateAnimationCount() {
		animationCount++;
		if (animationCount > TICKS_PER_IMAGE) {
			animationCount = 0;
			updateAnimation();
		}
	}

	private void updateAnimation() {
		if (animationImage < IMAGES_TO_LOOP - 1) {
			animationImage++;
		} else {
			animationImage = 0;
		}
	}

	public int getAnimationImage() {
		return animationImage;
	}

	public int getAnimationCount() {
		return animationCount;
	}
}