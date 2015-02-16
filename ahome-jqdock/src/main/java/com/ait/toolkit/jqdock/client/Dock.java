/*
 Copyright (c) 2014 Ahomé Innovation Technologies. All rights reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.ait.toolkit.jqdock.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ait.toolkit.core.client.JQueryUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * Implements a Mac OS like dock element for the web desktop. This is GWT for the awesome jqDock component located at : http://www.wizzud.com/jqdock/
 */
public class Dock extends Composite implements IsWidget {

	private static final String DOCK_CSS_URL = GWT.getModuleBaseURL() + "dock/jqDockStyle.css";
	private static final String DOCK_JQUERY_URL = GWT.getModuleBaseURL() + "dock/jquery.js";
	private static final String DOCK_JS_URL = GWT.getModuleBaseURL() + "dock/jqdock.min.js";
	private static final String TWEENMAX_JS_URL = GWT.getModuleBaseURL() + "dock/TweenMax.min.js";
	private static final String TWEEN_MAX_SCRIPT_ELEMENT_ID = "ahome-tweenmax-id";
	private static final String JQ_SCRIPT_ELEMENT_ID = "ahome-jq-id";
	private static final String JDOCK_STYLE_ELEMENT_ID = "ahome-jqdockstyle-id";
	private static final String JDOCK_SCRIPT_ELEMENT_ID = "ahome-jqdockscript-id";
	// protected int defaultMargin = 7;

	protected static final String ID = "id";
	protected static final String DOCK = "dock";
	protected FlowPanel dockElement;
	protected List<Image> icons;
	protected DockConfig dockConfig;
	protected double absoluteBottomPosition = 3;
	private static final int Z_INDEX = 20000;

	private static JavaScriptObject tweenmaxScriptElement;
	private static JavaScriptObject jqueryScriptElement;
	private static JavaScriptObject jqDockScriptElement;
	private static JavaScriptObject jqDockStyleElement;

	static {
		load();
	}

	public Dock() {
		dockElement = new FlowPanel();
		this.initWidget(dockElement);
		dockElement.getElement().setAttribute(ID, DOCK);
		dockElement.getElement().getStyle().setPosition(Position.ABSOLUTE);
		dockElement.getElement().getStyle().setBottom(absoluteBottomPosition, Unit.PX);
		dockElement.getElement().getStyle().setLeft(0, Unit.PX);
		dockElement.getElement().getStyle().setRight(0, Unit.PX);
		dockElement.getElement().getStyle().setDisplay(Display.NONE);
		dockElement.getElement().getStyle().setZIndex(Z_INDEX);

		icons = new ArrayList<Image>();
		dockConfig = new DockConfig();
	}

	public List<Image> getIcons() {
		return this.icons;
	}

	public Style getStyle() {
		return dockElement.getElement().getStyle();
	}

	public void setLeft(double left) {
		dockElement.getElement().getStyle().setLeft(left, Unit.PX);
	}

	public void setBottom(double bottom) {
		dockElement.getElement().getStyle().setLeft(bottom, Unit.PX);
	}

	public void setRight(double right) {
		dockElement.getElement().getStyle().setRight(right, Unit.PX);
	}

	public void setTop(double top) {
		dockElement.getElement().getStyle().setTop(top, Unit.PX);
	}

	public DockConfig getConfig() {
		return this.dockConfig;
	}

	public Dock(DockConfig config) {
		this();
		this.dockConfig = config;
	}

	public void addIcon(Image image) {
		icons.add(image);
		dockElement.add(image);
	}

	public void addIcon(ImageResource imageResource) {
		Image img = new Image(imageResource);
		addIcon(new Image(img.getUrl()));
	}

	public void addIcon(Image image, ClickHandler handler) {
		image.addClickHandler(handler);
		icons.add(image);
		dockElement.add(image);
	}

	public void addIcon(ImageResource imageResource, ClickHandler handler) {
		Image img = new Image(imageResource);
		addIcon(new Image(img.getUrl()), handler);
	}

	public void addIcons(List<Image> icons) {
		for (Image icon : icons) {
			addIcon(icon);
		}
	}

	public void addIcons(Image... icons) {
		addIcons(Arrays.asList(icons));
	}

	public void addIcon(Image image, String title) {
		image.getElement().setAttribute("title", title);
		image.getElement().setAttribute("alt", "");
		addIcon(image);
	}

	public void addIcon(ImageResource imageResource, String title) {
		Image img = new Image(imageResource);
		addIcon(new Image(img.getUrl()), title);
	}

	public void addIcon(Image image, String title, ClickHandler handler) {
		image.getElement().setAttribute("title", title);
		image.getElement().setAttribute("alt", "");
		addIcon(image, handler);
	}

	public void addIcon(ImageResource imageResource, String title, ClickHandler handler) {
		Image img = new Image(imageResource);
		addIcon(new Image(img.getUrl()), title, handler);
	}

	public void addIcon(Image image, String title, String alt) {
		image.getElement().setAttribute("title", title);
		image.getElement().setAttribute("alt", alt);
		addIcon(image);
	}

	public void addIcon(ImageResource imageResource, String title, String alt) {
		Image img = new Image(imageResource);
		addIcon(new Image(img.getUrl()), title, alt);
	}

	public void addIcon(Image image, String title, String alt, ClickHandler handler) {
		image.getElement().setAttribute("title", title);
		image.getElement().setAttribute("alt", alt);
		addIcon(image, handler);
	}

	public void addIcon(ImageResource imageResource, String title, String alt, ClickHandler handler) {
		Image img = new Image(imageResource);
		addIcon(new Image(img.getUrl()), title, alt, handler);
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				layoutDock(dockConfig.getJsObj());
			}
		});
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	private native void layoutDock(JavaScriptObject options)/*-{
		var id = '#' + @com.ait.toolkit.jqdock.client.Dock::DOCK;
		$wnd.jQuery(id).jqDock(options);
	}-*/;

	public native void nudge()/*-{
		var id = '#' + @com.ait.toolkit.jqdock.client.Dock::DOCK;
		$wnd.jQuery(id).jqDock('nudge');
	}-*/;

	public native void idle()/*-{
		var id = '#' + @com.ait.toolkit.jqdock.client.Dock::DOCK;
		$wnd.jQuery(id).jqDock('idle');
	}-*/;

	public void update() {
		layoutDock(dockConfig.getJsObj());
	}

	private static void load() {
		JQueryUtil.inject();
		DockResources resources = GWT.create(DockResources.class);
		StyleInjector.inject(resources.css().getText());
		ScriptInjector.fromString(resources.jqDockJs().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
	}
}
