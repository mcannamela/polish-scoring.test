package com.ultimatepolish.polishscorebook.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import android.util.Log;

public class MockDB {

	public void backupDB() {
		File sFile = getDatabaseFile();
		File dFile = getBackupFile();
		try {
			copyFile(sFile, dFile);
		} catch (IOException e) {
			Log.e("MockDB", "Failed to restore database.", e);
		}
	}

	public void restoreDB() {
		File sFile = getBackupFile();
		File dFile = getDatabaseFile();
		try {
			copyFile(sFile, dFile);
		} catch (IOException e) {
			Log.e("MockDB", "Failed to restore database.", e);
		}
	}

	File getDatabaseFile() {
		// String dbPath = getHelper().getReadableDatabase().getPath();
		// File internalDB = new File(dbPath);
		File internalDB = null;
		return internalDB;
	}

	File getBackupFile() {
		File backupFile = new File("backupDB.db");
		return backupFile;
	}

	private void copyFile(File sourceFile, File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			long count = 0;
			long size = source.size();
			while ((count += destination.transferFrom(source, count, size
					- count)) < size)
				;
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}
}
