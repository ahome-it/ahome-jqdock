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

import com.ait.toolkit.core.client.Function;
import com.ait.toolkit.core.client.JsObject;
import com.ait.toolkit.core.client.JsoHelper;

/**
 * Configuration options for the {@link Dock}
 */
public class DockConfig extends JsObject {

	public DockConfig() {
		jsObj = JsoHelper.createObject();
	}

	public void setAlign(String value) {
		JsoHelper.setAttribute(jsObj, "align", value);
	}

	public void setSize(int value) {
		JsoHelper.setAttribute(jsObj, "size", value);
	}

	public void setSizeMax(int value) {
		JsoHelper.setAttribute(jsObj, "sizeMax", value);
	}

	public void setDistance(int value) {
		JsoHelper.setAttribute(jsObj, "distance", value);
	}

	public void setCoefficient(double value) {
		JsoHelper.setAttribute(jsObj, "coefficient", value);
	}

	public void setDuration(int value) {
		JsoHelper.setAttribute(jsObj, "duration", value);
	}

	public void setInactivitiy(int value) {
		JsoHelper.setAttribute(jsObj, "inactivitiy", value);
	}

	public void setFadeIn(int value) {
		JsoHelper.setAttribute(jsObj, "fadeIn", value);
	}

	public void setStep(int value) {
		JsoHelper.setAttribute(jsObj, "step", value);
	}

	public void setLabels(boolean value) {
		JsoHelper.setAttribute(jsObj, "labels", value);
	}

	public void setLabels(String value) {
		JsoHelper.setAttribute(jsObj, "labels", value);
	}

	public void setIdle(int value) {
		JsoHelper.setAttribute(jsObj, "idle", value);
	}

	public native void setOnSleep(Function fn)/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.onSleep = function() {
			var dock = $wnd.jQuery('.jqDock', this); //this is the actual dock
			fn.@com.ait.toolkit.core.client.Function::execute()();
			$wnd.jQuery('#dock').one('mousemove', function() {
				var id = '#' + @com.ait.toolkit.jqdock.client.Dock::DOCK;
				$wnd.jQuery(id).jqDock('nudge');
			});

			return true;
		};
	}-*/;

	public native void setOnSleep(DockEventHandler handler)/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.onSleep = function() {
			var dock = $wnd.jQuery('.jqDock', this); //this is the actual dock
			$wnd.jQuery('#dock').one('mousemove', function() {
				var id = '#' + @com.ait.toolkit.jqdock.client.Dock::DOCK;
				$wnd.jQuery(id).jqDock('nudge');
			});
			var toReturn = handler.@com.ait.toolkit.jqdock.client.DockEventHandler::onEvent()();
			return true;
		};
	}-*/;

	public native void setOnWake(Function fn)/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.onWake = function() {
			fn.@com.ait.toolkit.core.client.Function::execute()();
			return true;
		};
	}-*/;

}
