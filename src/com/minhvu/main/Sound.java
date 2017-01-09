package com.minhvu.main;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound
{
	public static final AudioClip BOUNCE = Applet.newAudioClip(Sound.class.getResource("bounce.wav"));
	public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("gameover.wav"));
	public static final AudioClip BACKGROUND = Applet.newAudioClip(Sound.class.getResource("background.wav"));
}