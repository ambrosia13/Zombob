package io.github.ambrosia.zombob;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Random;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

public class Main {
	public static void main(String[] args) {
		try {
			new Game().run();
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
}