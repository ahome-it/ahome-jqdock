package com.ait.toolkit.jqdock.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface DockResources extends ClientBundle {
	@Source("jqDockStyle.css")
	TextResource css();

	@Source("jqdock.min.js")
	TextResource jqDockJs();
}
