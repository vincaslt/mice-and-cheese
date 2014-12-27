package com.vincas.miceandcheese.utils;

import com.vincas.miceandcheese.LaunchGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResourceManager {
	//private static final String TILESET_LOCATION = "resources/maps";

	private static HashMap<String, Image> images = new HashMap<String, Image>();
	private static LoadingList loadingList;
	private static int progress;

	public static void init() {
		loadingList = LoadingList.get();
		// TODO implement smarter resource loading
		loadingList.add(new DeferredImage("cheese", "resources/cheese.png"));
		loadingList.add(new DeferredImage("cursor", "resources/cursor.png"));
		loadingList.add(new DeferredImage("mouse_sheet", "resources/mouse_sheet.png"));
	}

	public static void loadNextResource() {
		if (LoadingList.get().getRemainingResources() > 0) {
			DeferredResource nextResource = LoadingList.get().getNext();
			try {
				nextResource.load();
			} catch (IOException e) {
				Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		if (LoadingList.get().getTotalResources() > 0)
			progress = (100 * (LoadingList.get().getTotalResources() - LoadingList
				.get().getRemainingResources())) / LoadingList.get().getTotalResources();
	}

	public static boolean isLoadComplete() {
		return LoadingList.get().getRemainingResources() == 0;
	}

	public static int getProgress() {
		return progress;
	}

	public static Image getImage(String name) {
		return images.get(name);
	}

	private static class DeferredImage implements DeferredResource {
		private String name;
		private String path;

		public DeferredImage(String name, String path) {
			this.name = name;
			this.path = path;
		}

		public void load() throws IOException {
			try {
				Image image = new Image(path);
				ResourceManager.images.put(name, image);
			} catch (SlickException e) {
				Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, e);
			}
		}

		public String getDescription() {
			return "Deferred Image";
		}
	}
}
